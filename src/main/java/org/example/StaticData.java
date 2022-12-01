package org.example;

import java.util.Scanner;

public class StaticData {

    public static void pause(){
        System.out.print("[MESSAGE] Press enter to continue.\n");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }
    enum AccountTypes {Checking, Savings};
    enum TransactionTypes {Deposit, Withdrawal, Transfer};
    enum AccountStatus {Active, Closed, Frozen};
    enum CustomerStatus {Active, Inactive};
    enum Currencies {USD, INR, EUR};
    enum Characters {Paladin, Warrior, Sorcerer};


}
