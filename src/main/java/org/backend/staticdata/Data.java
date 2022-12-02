package org.backend.staticdata;

import java.util.Scanner;

public class Data {

    public static void pause(){
        System.out.print("[MESSAGE] Press enter to continue.\n");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }
    enum AccountTypes {CHECKING, SAVING, LOAN, NEW_SECURITY};
    enum TransactionTypes {DEPOSIT, WITHDRAWAL, TRANSFER, BUY_STOCK, SELL_STOCK};
    enum AccountStatus {ACTIVE, CLOSED, FROZEN};
    enum CustomerStatus {ACTIVE, INACTIVE};
    enum Currencies {USD, INR, EUR};
    enum TransactionStatus {APPROVED, REJECTED};


}
