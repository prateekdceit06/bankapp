package org.backend.models;

import org.backend.controllers.account.Deposit;
import org.backend.controllers.account.Transfer;
import org.backend.controllers.account.Withdraw;
import org.backend.staticdata.Data;

import java.time.LocalDateTime;

public class AccountChecking extends Account{
    public AccountChecking(int customerId, String accountType, double accountBalance, int accountStatus) {
        super(customerId, accountType, accountBalance, accountStatus);
    }

    public AccountChecking(int customerId, String accountNumber, String accountType, double accountBalance,
                           int accountStatus, LocalDateTime accountCreationDate, LocalDateTime accountLastUpdatedDate) {
        super(customerId, accountNumber, accountType, accountBalance, accountStatus, accountCreationDate, accountLastUpdatedDate);
    }

    public boolean transfer(double amount, String fromAccountNumber, String toAccountNumber,
                            String bankAccountNumber, User loggedInUser) {
        double transactionFee = amount*Data.checkingAccountTransferTransactionFees;
        amount = amount + transactionFee;
        boolean success = false;
        if (this.getAccountBalance() >= amount && this.getAccountStatus() ==1) {
            Transfer transfer = new Transfer();
            boolean transferAmountSuccess = transfer.transfer(amount, transactionFee,
                    fromAccountNumber, Data.TransactionTypes.TRANSFER.toString(), toAccountNumber, loggedInUser);
            boolean transferTransactionFeeSuccess = transfer.transfer(transactionFee,0,
                    fromAccountNumber, Data.TransactionTypes.TRANSACTION_FEE.toString(), bankAccountNumber , loggedInUser);
            success = transferTransactionFeeSuccess && transferAmountSuccess;
        }
        return success;
    }

    public boolean withdraw(double amount, String fromAccountNumber,
                            String bankAccountNumber, User loggedInUser) {
        double transactionFee = amount*Data.checkingAccountTransferTransactionFees;
        amount = amount + transactionFee;
        boolean success = false;
        if (this.getAccountBalance() >= amount && this.getAccountStatus() ==1) {
            Withdraw withdraw = new Withdraw();
            boolean withdrawAmountSuccess = withdraw.withdraw(amount, transactionFee,
                    fromAccountNumber, Data.TransactionTypes.WITHDRAWAL.toString(), loggedInUser);
            Transfer transfer = new Transfer();
            boolean transferTransactionFeeSuccess = transfer.transfer(transactionFee,0,
                    fromAccountNumber, Data.TransactionTypes.TRANSACTION_FEE.toString(), bankAccountNumber , loggedInUser);
            success = transferTransactionFeeSuccess && withdrawAmountSuccess;
        }
        return success;
    }

    public boolean deposit(double amount, String fromAccountNumber,
                            String bankAccountNumber, User loggedInUser) {
        double transactionFee = amount*Data.checkingAccountTransferTransactionFees;
        amount = amount + transactionFee;
        boolean success = false;
        if (this.getAccountStatus() ==1) {
            Deposit deposit = new Deposit();
            boolean withdrawAmountSuccess = deposit.deposit(amount, transactionFee,
                    fromAccountNumber, Data.TransactionTypes.DEPOSIT.toString(), loggedInUser);
            Transfer transfer = new Transfer();
            boolean transferTransactionFeeSuccess = transfer.transfer(transactionFee,0,
                    fromAccountNumber, Data.TransactionTypes.TRANSACTION_FEE.toString(), bankAccountNumber , loggedInUser);
            success = transferTransactionFeeSuccess && withdrawAmountSuccess;
        }
        return success;
    }
}
