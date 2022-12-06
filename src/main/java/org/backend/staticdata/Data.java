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
    public enum UserTypes {ACTIVE_USER, INACTIVE_USER, ACTIVE_CUSTOMER, INACTIVE_CUSTOMER};
    public enum TransactionTypes {TRANSACTION_FEE, ACCOUNT_CREATION_FEE, ACCOUNT_CLOSING_FEE,
        DEPOSIT, WITHDRAWAL, TRANSFER, BUY_STOCK, SELL_STOCK};
    public enum AccountStatus {ACTIVE, CLOSED, FROZEN};
    public enum CustomerStatus {ACTIVE, INACTIVE};
    public enum Currencies {USD, INR, EUR};
    public enum TransactionStatus {APPROVED, REJECTED};

    public static final String[] CURRENCY_SYMBOLS = {"$", "₹", "€"};
    public static final DecimalFormat df = new DecimalFormat("0.00");

    public static double checkingAccountTransferTransactionFees = 0.1;

    public static double initialSavingsAccountBalance = 100;

    public static double initialCheckingAccountBalance = 1000;
    public static double bankInitialBalance = 1000000;
    public static double checkingAccountCreationFees = 10;
    public static double checkingAccountClosingFees = 10;




}
