module BankAccountServices {
    
    
    enum BankAccountType { Standard, Premium }
    
    exception UnauthorizedError {}
    exception AccountAlreadyExistsError {}
    
    struct BankAccountState {
        float balance;
        BankAccountType type;
    }
    
    struct BankAccountDetails {
        string name;
        string surname;
        string pesel;
        string guid;
        float monthlyIncome;
    }
    
    struct LoanRequest {
        string targetCurrency;
        int months;
        float amount;
    }
    
    struct LoanProposal {
        float costLocal;
        float costCurrency;
        float currencyValue;
    }
    
    interface BankAccount {
        BankAccountState getState(string guid) throws UnauthorizedError;
        BankAccountDetails getDetails(string guid) throws UnauthorizedError;
    }
    
    interface PremiumBankAccount extends BankAccount {
        LoanProposal requestLoan(LoanRequest request, string guid) throws UnauthorizedError;
    }
    
    interface BankAccountFactory {
        BankAccount* createAccount(string name, string surname, string pesel, float monthlyIncome, out string guid) throws AccountAlreadyExistsError;
    }
}
