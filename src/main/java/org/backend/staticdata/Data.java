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
    enum TransactionTypes {DEPOSIT, WITHDRAWAL, TRANSFER, BUY_STOCK, SELL_STOCK};
    enum AccountStatus {ACTIVE, CLOSED, FROZEN};
    enum CustomerStatus {ACTIVE, INACTIVE};
    enum Currencies {USD, INR, EUR};
    enum TransactionStatus {APPROVED, REJECTED};

    public static final DecimalFormat df = new DecimalFormat("0.00");


}
