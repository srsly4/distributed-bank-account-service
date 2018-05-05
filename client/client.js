const ice = require("ice").Ice;
const BankAccountServices = require("./bank_account").BankAccountServices;
const vorpal = require('vorpal')();

let iceInstance;
let bankAccountFactory;
let accountProxy = null;

const iceInit = async () => {
  iceInstance = ice.initialize();
  console.log('Ice intialized');
  const bankAccountFactoryProxy = iceInstance.stringToProxy('BankAccountFactory:tcp -p 10000 -h localhost')
                                             .ice_twoway()
                                             .ice_secure(false);
  console.log('Proxy configured');
  bankAccountFactory = await BankAccountServices.BankAccountFactoryPrx.checkedCast(bankAccountFactoryProxy);
  console.log('??????');
};

iceInit().then(() => {
  console.log('Initialization completed!');
  vorpal
    .command('create-account <name> <surname> <pesel> <monthlyIncome>', 'Creates a new bank account')
    .action(async (args) => {
      console.log('Creating account...');
      console.log(args);
      try {
        const [accountProxyRet, guidRet] = await bankAccountFactory.createAccount(
          args.name, args.surname, args.pesel, parseFloat(args.monthlyIncome) || 0.0);
        console.log(`Your GUID is '${guidRet}'`);
        accountProxy = accountProxyRet;
      } catch (e) {
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
    .delimiter('client$')
    .show();
}).catch(console.error);