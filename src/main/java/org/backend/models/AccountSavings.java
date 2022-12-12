package org.backend.models;

import org.backend.controllers.account.Deposit;
import org.backend.controllers.account.Transfer;
import org.backend.controllers.account.Withdraw;
import org.backend.staticdata.Data;

import java.time.LocalDateTime;

public class AccountSavings extends Account{
    //default constructor
    public AccountSavings() {
    }
    public AccountSavings(int customerId, String accountType, double accountBalance, int accountStatus) {
        super(customerId, accountType, accountBalance, accountStatus);
    }

    public AccountSavings(int customerId, String accountNumber, String accountType, double accountBalance,
                          int accountStatus, LocalDateTime accountCreationDate, LocalDateTime accountLastUpdatedDate) {
        super(customerId, accountNumber, accountType, accountBalance, accountStatus, accountCreationDate, accountLastUpdatedDate);
    }

    public boolean transfer(double amount, String fromAccountNumber, String toAccountNumber, User loggedInUser) {
        boolean success = false;
        if (this.getAccountBalance() >= amount && this.getAccountStatus() ==1) {
            Transfer transfer = new Transfer();
            success = transfer.transfer(amount, 0,fromAccountNumber, Data.TransactionTypes.TRANSFER.toString(), toAccountNumber, loggedInUser);
        }
        return success;
    }

    public boolean withdraw(double amount, String fromAccountNumber, User loggedInUser) {
        boolean success = false;
        if (this.getAccountBalance() >= amount && this.getAccountStatus() ==1) {
            Withdraw withdraw = new Withdraw();
            success = withdraw.withdraw(amount, 0,fromAccountNumber, Data.TransactionTypes.WITHDRAWAL.toString(), loggedInUser);
        }
        return success;
    }

    public boolean deposit(double amount, String fromAccountNumber, User loggedInUser, String fromCurrency) {
        boolean success = false;
        if (this.getAccountStatus() ==1) {
            Deposit deposit = new Deposit();
            success = deposit.deposit(amount, 0,fromAccountNumber,
                    Data.TransactionTypes.DEPOSIT.toString(), loggedInUser, fromCurrency);
        }
        return success;
    }

}
