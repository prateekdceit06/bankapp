package org.backend.models;

import org.backend.controllers.account.CloseAccount;
import org.backend.controllers.account.CreateAccount;
import org.backend.controllers.account.ViewAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private int customerId;
    private String accountNumber;
    private String accountType;
    private double accountBalance;
    private int accountStatus;
    private LocalDateTime accountCreationDate;
    private LocalDateTime accountLastUpdatedDate;
    private List<Transaction> transactions;

    //default constructor
    public Account() {
    }
    public Account(int customerId, String accountType, double accountBalance, int accountStatus) {
        this.customerId = customerId;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountStatus = accountStatus;
        this.transactions = new ArrayList<>();
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
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
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
                "accountLastUpdatedDate=" + accountLastUpdatedDate + "]\n";
    }

    public boolean createAccount(User loggedInUser, List<String> accountNumbers, String fromCurrency) {
        boolean accountCreated = false;
        CreateAccount createAccount = new CreateAccount();
        accountCreated = createAccount.createAccount(this, loggedInUser, accountNumbers, fromCurrency);
        return accountCreated;
    }

    public boolean closeAccount(){
        boolean accountClosed = false;
        CloseAccount closeAccount = new CloseAccount();
        accountClosed = closeAccount.closeAccount(this);
        return accountClosed;
    }

    public boolean viewAccount(User loggedInUser){
        boolean accountViewed = false;
        ViewAccount viewAccount = new ViewAccount();
        accountViewed = viewAccount.viewAccount(this.accountNumber, loggedInUser);
        return accountViewed;
    }

    public void viewTransactions(){
        if(transactions.size() == 0){
            System.out.println("No transactions found for this account.");
        } else {
            for(Transaction transaction : transactions){
                System.out.println(transaction);
            }
        }
    }

}
