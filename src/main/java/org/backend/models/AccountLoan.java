package org.backend.models;

import org.backend.controllers.account.Deposit;
import org.backend.controllers.account.Transfer;
import org.backend.controllers.account.Withdraw;
import org.backend.controllers.loan.PayLoan;
import org.backend.staticdata.Data;

import java.time.LocalDateTime;

public class AccountLoan extends Account {

    public AccountLoan(int customerId, String accountType, double accountBalance, int accountStatus) {
        super(customerId, accountType, accountBalance, accountStatus);
    }

    public AccountLoan(int customerId, String accountNumber, String accountType, double accountBalance,
                       int accountStatus, LocalDateTime accountCreationDate, LocalDateTime accountLastUpdatedDate) {
        super(customerId, accountNumber, accountType, accountBalance, accountStatus, accountCreationDate, accountLastUpdatedDate);
    }

    public boolean transfer(double amount, String fromAccountNumber, String toAccountNumber, User loggedInUser) {
        boolean success = false;
        if (this.getAccountStatus() ==1) {
            Transfer transfer = new Transfer();
            success = transfer.transfer(amount, 0,fromAccountNumber,
                    Data.TransactionTypes.LOAN_TRANSFER.toString(), toAccountNumber, loggedInUser);
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

    public boolean payLoan(int loanId, double amount, String toAccountNumber, User loggedInUser, String fromCurrency) {
        boolean success =false;
        PayLoan payLoan = new PayLoan();
        if(amount >0){
            double amountDeposited = payLoan.payLoan(loanId, amount, loggedInUser);
            Deposit deposit = new Deposit();
            success = deposit.deposit(amountDeposited, 0, toAccountNumber,
                    Data.TransactionTypes.LOAN_PAYMENT.toString(), loggedInUser, fromCurrency);
        }
        return success;
    }

    public boolean payLoan(int loanId, double amount, Account fromAccount, String toAccountNumber, User loggedInUser) {
        boolean success =false;
        PayLoan payLoan = new PayLoan();
        if(amount >0){
            double amountDeposited = payLoan.payLoan(loanId, amount, loggedInUser);
            if(fromAccount instanceof AccountSavings){
                AccountSavings accountSavings = (AccountSavings) fromAccount;
                success = accountSavings.transfer(amountDeposited, fromAccount.getAccountNumber(), toAccountNumber, loggedInUser);
            } else if(fromAccount instanceof AccountChecking){
                AccountChecking accountChecking = (AccountChecking) fromAccount;
                success = accountChecking.transfer(amountDeposited, fromAccount.getAccountNumber(), toAccountNumber,
                        toAccountNumber, loggedInUser);
            }
        }
        return success;
    }


}
