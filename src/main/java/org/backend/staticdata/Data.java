package org.backend.staticdata;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Data {

    public static void pause(){
        System.out.print("[MESSAGE] Press enter to continue.\n");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }
    public enum AccountTypes {CHECKING, SAVINGS, LOAN, NEW_SECURITY};
    public enum TransactionTypes {TRANSACTION_FEE, ACCOUNT_CREATION_FEE, ACCOUNT_CLOSING_FEE, WITHDRAWAL_FEES, LOAN_TRANSFER,
        LOAN_PAYMENT, DEPOSIT, WITHDRAWAL, TRANSFER, BUY_STOCK, SELL_STOCK};
    public enum AccountStatus {ACTIVE, CLOSED, FROZEN};
    public enum CustomerStatus {ACTIVE, INACTIVE};
    public enum Currencies {USD, INR, EUR};
    public enum TransactionStatus {APPROVED, REJECTED};
    public enum LoanStatus {APPROVED, REJECTED, PENDING};
    public enum LoanInterestUnit {MONTHLY, YEARLY};

    public static final String[] CURRENCY_SYMBOLS = {"$", "₹", "€"};
    public static final DecimalFormat df = new DecimalFormat("0.00");

    public static double checkingAccountTransferTransactionFees = 0.1;

    public static double initialSavingsAccountBalance = 100;

    public static double initialCheckingAccountBalance = 1000;
    public static double bankInitialBalance = 1000000;
    public static double checkingAccountCreationFees = 10;
    public static double savingsAccountCreationFees = 10;
    public static double checkingAccountClosingFees = 10;
    public static double savingsAccountClosingFees = 10;
    public static double checkingAccountWithdrawalFees = 10;




}
