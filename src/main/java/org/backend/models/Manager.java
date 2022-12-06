package org.backend.models;


import org.backend.controllers.manager.InitializeBank;
import org.backend.controllers.manager.LoadCheckingAccounts;
import org.backend.controllers.manager.LoadSavingsAccounts;
import org.backend.controllers.manager.LoadUserData;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<User> users;
    private List<Customer> customers;
    private List<String> accountNumbers;
    private List<AccountSavings> savingsAccounts;
    private List<AccountChecking> checkingAccounts;
    private List<Account> accounts;

    private List<Transaction> ledger;
    private User loggedInUser;
    private String bankAccountNumber;


    public Manager() {
        this.users = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
        this.checkingAccounts = new ArrayList<>();
        this.loggedInUser = null;
        this.accounts = new ArrayList<>();
        this.accountNumbers = new ArrayList<>();
        initializeBank();
        loadAccounts();
        loadUserData();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<AccountSavings> getSavingsAccounts() {
        return savingsAccounts;
    }

    public List<AccountChecking> getCheckingAccounts() {
        return checkingAccounts;
    }

    public List<User> getUsers() {
        return users;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public List<String> getAccountNumbers() {
        return accountNumbers;
    }

    public void setAccountNumbers(List<String> accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public void loadAccounts() {
        savingsAccounts.clear();
        checkingAccounts.clear();
        accounts.clear();
        LoadSavingsAccounts loadSavingsAccounts = new LoadSavingsAccounts();
        savingsAccounts = loadSavingsAccounts.loadSavingsAccounts();
        LoadCheckingAccounts loadCheckingAccounts = new LoadCheckingAccounts();
        checkingAccounts = loadCheckingAccounts.loadCheckingAccounts();

        if (savingsAccounts != null && savingsAccounts.size() > 0) {
            accounts.addAll(savingsAccounts);
        }
        if (checkingAccounts != null && checkingAccounts.size() > 0) {
            accounts.addAll(checkingAccounts);
        }
        if(accounts != null && accounts.size() > 0){
            for(Account account : accounts){
                accountNumbers.add(account.getAccountNumber());
            }
        }
    }



    public void loadUserData() {
        users.clear();
        customers.clear();
        LoadUserData loadUserData = new LoadUserData();
        users = loadUserData.loadUserData();
        if (users != null) {
            for (User user : users) {
                if (user.getIsCustomer() == 1) {
                    Customer customer = new Customer(user.getId(), user.getFirstName(), user.getLastName(),
                            user.getPhone(), user.getAddress(), user.getEmail(), user.getUserName(),
                            user.getIsActive(), user.getIsAdmin(), user.getIsEmployee(), user.getToken(),
                            user.getCreatedAt(), user.getUpdatedAt(), user.getHasCollateral(), user.getHasLoan(),
                            user.getIsCustomer());
                    customers.add(customer);
                }
            }
            for (Customer customer : customers) {
                for (Account account : savingsAccounts) {
                    if (account.getCustomerId() == customer.getId()) {
                        customer.getAccounts().add(account);
                    }
                }
                for (Account account : checkingAccounts) {
                    if (account.getCustomerId() == customer.getId()) {
                        customer.getAccounts().add(account);
                    }
                }
            }
        }
    }

    private void initializeBank() {
        InitializeBank initializeBank = new InitializeBank();
        bankAccountNumber = initializeBank.initializeBank();
    }

    //get logged in user by id
    public User getLoggedInUser(int id) {
        loggedInUser = null;
        for (User user : users) {
            if (user.getId() == id) {
                loggedInUser = user;
            }
        }
        return loggedInUser;
    }

    //get logged in user by username
    public User getLoggedInUser(String username) {
        loggedInUser = null;
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                loggedInUser = user;
            }
        }
        return loggedInUser;
    }

}
