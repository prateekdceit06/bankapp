package org.backend.models;


import org.backend.controllers.manager.InitializeBank;
import org.backend.controllers.manager.LoadAccounts;
import org.backend.controllers.manager.LoadUserData;
import org.backend.staticdata.Data;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<User> users;
    private List<Customer> customers;
    private List<Account> savingsAccounts;
    private List<Account> checkingAccounts;


    public Manager() {
        this.users = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
        this.checkingAccounts = new ArrayList<>();
        initializeBank();
        loadAccounts();
        loadUserData();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Account> getCheckingAccounts() {
        return checkingAccounts;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Account> getSavingsAccounts() {
        return savingsAccounts;
    }


    public void loadAccounts() {
        savingsAccounts.clear();
        checkingAccounts.clear();
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
        initializeBank.initializeBank();
    }

}
