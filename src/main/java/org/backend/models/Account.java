package org.backend.models;

import org.backend.controllers.account.CloseAccount;
import org.backend.controllers.account.CreateAccount;

import java.time.LocalDateTime;

public abstract class Account {

    private int customerId;
    private String accountNumber;
    private String accountType;
    private double accountBalance;
    private int accountStatus;
    private LocalDateTime accountCreationDate;
    private LocalDateTime accountLastUpdatedDate;

    public Account(int customerId, String accountType, double accountBalance, int accountStatus) {
        this.customerId = customerId;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountStatus = accountStatus;
    }

    public Account(int customerId, String accountNumber, String accountType, double accountBalance,
                   int accountStatus, LocalDateTime accountCreationDate, LocalDateTime accountLastUpdatedDate) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountStatus = accountStatus;
        this.accountCreationDate = accountCreationDate;
        this.accountLastUpdatedDate = accountLastUpdatedDate;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public LocalDateTime getAccountLastUpdatedDate() {
        return accountLastUpdatedDate;
    }

    public void setAccountLastUpdatedDate(LocalDateTime accountLastUpdatedDate) {
        this.accountLastUpdatedDate = accountLastUpdatedDate;
    }

    public String toString() {
        return "Account [customerId=" + customerId + ", " +
                "accountNumber=" + accountNumber + ", " +
                "accountType=" + accountType + ", " +
                "accountBalance=" + accountBalance + ", " +
                "accountStatus=" + accountStatus + ", " +
                "accountCreationDate=" + accountCreationDate + ", " +
                "accountLastUpdatedDate=" + accountLastUpdatedDate + "]";
    }

    public boolean createAccount(User loggedInUser) {
        boolean accountCreated = false;
        CreateAccount createAccount = new CreateAccount();
        accountCreated = createAccount.createAccount(this, loggedInUser);
        return accountCreated;
    }

    public boolean closeAccount(){
        boolean accountClosed = false;
        CloseAccount closeAccount = new CloseAccount();
        accountClosed = closeAccount.closeAccount(this);
        return accountClosed;
    }
}
