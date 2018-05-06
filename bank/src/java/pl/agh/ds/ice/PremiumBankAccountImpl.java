package pl.agh.ds.ice;

import BankAccountServices.BankAccountState;
import BankAccountServices.BankAccountType;
import BankAccountServices.InvalidArgumentError;
import BankAccountServices.LoanProposal;
import BankAccountServices.LoanRequest;
import BankAccountServices.PremiumBankAccount;
import BankAccountServices.UnauthorizedError;
import com.zeroc.Ice.Current;
import org.joda.time.DateTime;
import org.joda.time.Days;
import pl.agh.ds.Main;

public class PremiumBankAccountImpl extends BankAccountImpl implements PremiumBankAccount {

    private static float PERCENTAGE = 0.08f;

    public PremiumBankAccountImpl(String guid, String name, String surname, String pesel, Float monthlyIncome) {
        super(guid, name, surname, pesel, monthlyIncome);
        this.state = new BankAccountState(0, BankAccountType.Premium);
    }

    @Override
    public LoanProposal requestLoan(LoanRequest request, String guid, Current current) throws UnauthorizedError, InvalidArgumentError {
        if (!guid.equals(this.details.guid)) {
            throw new UnauthorizedError();
        }

        DateTime startDate = new DateTime(request.startTime * 1000L);
        DateTime endDate = new DateTime(request.endTime * 1000L);
        if (startDate.isAfter(endDate) || startDate.isBeforeNow() || Days.daysBetween(startDate, endDate).getDays() < 30) {
            throw new InvalidArgumentError("Invalid date range");
        }

        if (request.amount <= 0) {
            throw new InvalidArgumentError("Invalid loan amount");
        }

        if (!Main.currencies.containsKey(request.targetCurrency)) {
            throw new InvalidArgumentError("Requested target currency doesn't exist in our system");
        }

        int days = Days.daysBetween(startDate, endDate).getDays();
        int months = (int)Math.ceil(days / 30.0f);

        float perMonth = request.amount / (float)months;
        float ratePerMonth = perMonth * PERCENTAGE;

        float rateCost = months * ratePerMonth;
        float totalCost = request.amount + rateCost;

        float currencyValue = Main.currencies.get(request.targetCurrency);
        float currencyAmount = request.amount/currencyValue;
        float currencyPerMonth = currencyAmount/(float)months;
        float currencyRatePerMonth = currencyPerMonth * PERCENTAGE;

        float currencyRateCost = months * currencyRatePerMonth;
        float currencyTotalCost = (currencyAmount + currencyRateCost)*currencyValue;

        return new LoanProposal(totalCost, currencyTotalCost, currencyValue);
    }
}
