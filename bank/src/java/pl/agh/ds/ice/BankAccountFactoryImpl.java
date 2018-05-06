package pl.agh.ds.ice;

import BankAccountServices.AccountAlreadyExistsError;
import BankAccountServices.BankAccount;
import BankAccountServices.BankAccountFactory;
import BankAccountServices.BankAccountPrx;
import com.zeroc.Ice.AlreadyRegisteredException;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Identity;

import java.util.UUID;

/**
 * Created by sirius on 05.05.18.
 */
public class BankAccountFactoryImpl implements BankAccountFactory {
    @Override
    public BankAccountFactory.CreateAccountResult createAccount(String name, String surname, String pesel, float monthlyIncome, Current current) throws AccountAlreadyExistsError {
        UUID uuid = UUID.randomUUID();
        BankAccount bankAccount;
        Identity identity;

        if (monthlyIncome < 15000.0) {
            bankAccount = new BankAccountImpl(uuid.toString(), name, surname, pesel, monthlyIncome);
            identity = new Identity(pesel, "standard");
        } else {
            bankAccount = new PremiumBankAccountImpl(uuid.toString(), name, surname, pesel, monthlyIncome);
            identity = new Identity(pesel, "premium");
        }
        try {
            BankAccountPrx proxy = BankAccountPrx.uncheckedCast(current.adapter.add(bankAccount, identity));
            return new CreateAccountResult(proxy, uuid.toString());
        } catch (AlreadyRegisteredException are) {
            throw new AccountAlreadyExistsError();
        }
    }

}
