const ice = require("ice").Ice;
const BankAccountServices = require("./bank_account").BankAccountServices;
const vorpal = require('vorpal')();
const moment = require('moment');

let iceInstance;
let bankAccountFactory = null;
let accountProxy = null;

const iceInit = async () => {
  iceInstance = ice.initialize();
  console.log('Ice intialized');
};

iceInit().then(() => {
  console.log('Initialization completed!');
  vorpal
    .command('create-account <name> <surname> <pesel> <monthlyIncome> [port]', 'Creates a new bank account')
    .action(async (args) => {
      console.log('Creating account...');
      try {
        const bankAccountFactoryProxy = iceInstance.stringToProxy(`BankAccountFactory:tcp -p ${args.port || 10000} -h localhost`)
                                                   .ice_twoway()
                                                   .ice_secure(false);
        console.log('Proxy configured');
        bankAccountFactory = await BankAccountServices.BankAccountFactoryPrx.checkedCast(bankAccountFactoryProxy);
        console.log('Proxy for BankAccountFactory obtained successfully');

        const [accountProxyRet, guidRet] = await bankAccountFactory.createAccount(
          args.name, args.surname, args.pesel, parseFloat(args.monthlyIncome) || 0.0);
        const identity = accountProxyRet.ice_getIdentity();
        console.log(`Your name and category are: ${identity.name} ${identity.category}\nYour GUID is '${guidRet}'`);
        accountProxy = accountProxyRet;
      } catch (e) {
        console.log('An error occurred during the account creation:');
        console.error(e);
      }
    });

  vorpal
    .command('state <guid>', 'Retrieves the bank account state')
    .action(async (args) => {
      if (!accountProxy) {
        console.error('No account proxy available');
        return;
      }
      try {
        const result = await accountProxy.getState(args.guid);
        console.log(result);
      } catch (e) {
        console.error(e);
      }
    });

  vorpal
    .command('request-loan <guid> <amount> <startDate> <endDate> <targetCurrency>')
    .action(async (args) => {
      try {
        const premiumAccountProxy = await BankAccountServices.PremiumBankAccountPrx.checkedCast(accountProxy);
        if (!premiumAccountProxy) {
          console.error('Your account is not a premium account!');
          return;
        }
        const result = await premiumAccountProxy.requestLoan(
          new BankAccountServices.LoanRequest(args.targetCurrency,
            moment(args.startDate).unix(),
            moment(args.endDate).unix(),
            args.amount,
          ), args.guid);
        console.log(result);
      } catch (e) {
        console.error('Error while requesting a loan:', e);
      }
    });

  vorpal
    .command('switch-account <pesel> <category> [port]')
    .action(async (args) => {
      try {
        const newAccount = await BankAccountServices.BankAccountPrx.checkedCast(
          iceInstance.stringToProxy(`${args.category}/${args.pesel}:tcp -p ${args.port || 10000} -h localhost`)
                     .ice_twoway()
                     .ice_secure(false));
        if (!newAccount) {
          console.error('Couldn\'t find such an account');
          return;
        }
        accountProxy = newAccount;
      } catch (e) {
        console.error('Couldn\'t find such an account:', e);
      }
    });

  vorpal
    .delimiter('client$')
    .show();
}).catch(console.error);