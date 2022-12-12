package org.backend.staticdata;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Data {
    public enum AccountTypes {CHECKING, SAVINGS, LOAN, NEW_SECURITY};
    public enum TransactionTypes {TRANSACTION_FEE, ACCOUNT_CREATION_FEE, ACCOUNT_CLOSING_FEE, WITHDRAWAL_FEES, LOAN_TRANSFER,
        LOAN_PAYMENT, DEPOSIT, WITHDRAWAL, TRANSFER, BUY_STOCK, SELL_STOCK, INTEREST_PAYMENT};
    public enum AccountStatus {ACTIVE, CLOSED, FROZEN};
    public enum CustomerStatus {ACTIVE, INACTIVE};
    public enum Currencies {USD, INR, EUR};
    public enum TransactionStatus {APPROVED, REJECTED};
    public enum LoanStatus {APPROVED, REJECTED, PENDING};
    public enum LoanInterestUnit {MONTHLY, YEARLY};
    public enum StockStatus {BOUGHT, SOLD};

    public enum ReferencedVariables {
        interest_payment_date
    };

    public static final DecimalFormat df = new DecimalFormat("0.00");

    public static double checkingAccountTransferTransactionFees = 0.1;

    public static double initialSavingsAccountBalance = 100;

    public static double initialCheckingAccountBalance = 1000;
    public static double bankInitialBalance = 1000000;
    public static double checkingAccountCreationFees = 10;
    public static double savingsAccountCreationFees = 10;
    public static double accountClosingFees = 10;
    public static double checkingAccountWithdrawalFees = 0.1;
    public static double minimumSavingsBalanceForNewSecurityAccount = 2500;
    public static double minimumTransferBalanceForNewSecurities = 1000;





}
