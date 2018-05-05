package pl.agh.ds.ice;

import BankAccountServices.BankAccount;
import BankAccountServices.BankAccountDetails;
import BankAccountServices.BankAccountState;
import BankAccountServices.BankAccountType;
import BankAccountServices.UnauthorizedError;
import com.zeroc.Ice.Current;

public class BankAccountImpl implements BankAccount {
    private final BankAccountDetails details;
    private final BankAccountState state;

    public BankAccountImpl(String guid, String name, String surname, String pesel, Float monthlyIncome) {
        details = new BankAccountDetails(name, surname, pesel, guid, monthlyIncome);
        state = new BankAccountState(0.0f, BankAccountType.Standard);
    }

    @Override
    public BankAccountState getState(String guid, Current current) throws UnauthorizedError {
        if (!guid.equals(this.details.guid)) {
            throw new UnauthorizedError();
        }
        return this.state;
    }

    @Override
    public BankAccountDetails getDetails(String guid, Current current) throws UnauthorizedError {
        if (!guid.equals(this.details.guid)) {
            throw new UnauthorizedError();
        }
        return this.details;
    }
}
