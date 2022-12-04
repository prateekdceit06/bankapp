package org.backend.models;


import org.backend.controllers.manager.InitializeBank;
import org.backend.controllers.manager.LoadAccounts;
import org.backend.controllers.manager.LoadUserData;
import org.backend.staticdata.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manager {
    private List<User> activeUsers;
    private List<User> inactiveUsers;
    private List<Customer> activeCustomers;
    private List<Customer> inactiveCustomers;
    private List<Account> savingsAccounts;
    private List<Account> checkingAccounts;

    public Manager() {
        this.savingsAccounts = new ArrayList<>();
        this.checkingAccounts = new ArrayList<>();
        this.activeUsers = new ArrayList<>();
        this.inactiveUsers = new ArrayList<>();
        this.activeCustomers = new ArrayList<>();
        this.inactiveCustomers = new ArrayList<>();
        initializeBank();
        loadUserData();
        loadAccounts();
    }

    private void loadAccounts() {
        LoadAccounts loadAccounts = new LoadAccounts();
        List<Account> accounts = loadAccounts.loadAccounts();
        if (accounts != null && accounts.size() > 0) {
            for (Account account : accounts) {
                if (account.getAccountType().equals(Data.AccountTypes.SAVINGS.toString())) {
                    savingsAccounts.add(account);
                } else if (account.getAccountType().equals(Data.AccountTypes.CHECKING.toString())) {
                    checkingAccounts.add(account);
                }
            }
        }
    }

    public List<User> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(List<User> activeUsers) {
        this.activeUsers = activeUsers;
    }

    public List<User> getInactiveUsers() {
        return inactiveUsers;
    }

    public void setInactiveUsers(List<User> inactiveUsers) {
        this.inactiveUsers = inactiveUsers;
    }

    public List<Customer> getActiveCustomers() {
        return activeCustomers;
    }

    public void setActiveCustomers(List<Customer> activeCustomers) {
        this.activeCustomers = activeCustomers;
    }

    public List<Customer> getInactiveCustomers() {
        return inactiveCustomers;
    }

    public void setInactiveCustomers(List<Customer> inactiveCustomers) {
        this.inactiveCustomers = inactiveCustomers;
    }

    public List<Account> getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(List<Account> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public List<Account> getCheckingAccounts() {
        return checkingAccounts;
    }

    public void setCheckingAccounts(List<Account> checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    private void loadUserData() {

        LoadUserData loadUserData = new LoadUserData();
        HashMap<String, List<User>> userData = loadUserData.loadUserData();
        activeUsers = userData.get(Data.UserTypes.ACTIVE_USER.toString());
        inactiveUsers = userData.get(Data.UserTypes.INACTIVE_USER.toString());
        if (activeUsers != null) {
            for (User user : activeUsers) {
                if (user.getIsCustomer() == 1) {
                    Customer customer = new Customer(user.getId(), user.getFirstName(), user.getLastName(),
                            user.getPhone(), user.getAddress(), user.getEmail(), user.getUserName(),
                            user.getIsActive(), user.getIsAdmin(), user.getIsEmployee(), user.getToken(),
                            user.getCreatedAt(), user.getUpdatedAt(), user.getHasCollateral(), user.getHasLoan(),
                            user.getIsCustomer());
                    activeCustomers.add(customer);
                }
            }
        }
        if (inactiveUsers != null) {
            for (User user : inactiveUsers) {
                if (user.getIsCustomer() == 1) {
                    Customer customer = new Customer(user.getId(), user.getFirstName(), user.getLastName(),
                            user.getPhone(), user.getAddress(), user.getEmail(), user.getUserName(),
                            user.getIsActive(), user.getIsAdmin(), user.getIsEmployee(), user.getToken(),
                            user.getCreatedAt(), user.getUpdatedAt(), user.getHasCollateral(), user.getHasLoan(),
                            user.getIsCustomer());
                    inactiveCustomers.add(customer);
                }
            }
        }

    }

    private void initializeBank() {
        InitializeBank initializeBank = new InitializeBank();
        initializeBank.initializeBank();
    }

}
