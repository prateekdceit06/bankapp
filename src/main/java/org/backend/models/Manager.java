package org.backend.models;


import org.backend.controllers.manager.*;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<User> users;
    private List<Customer> customers;
    private List<String> accountNumbers;
    private List<AccountSavings> savingsAccounts;
    private List<AccountChecking> checkingAccounts;
    private List<AccountLoan> loanAccounts;
    private List<Account> accounts;
    private List<Transaction> ledger;
    private List<Loan> loans;
    private List<ApprovedLoan> approvedLoans;
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
        this.ledger = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.approvedLoans = new ArrayList<>();
        this.loanAccounts = new ArrayList<>();
        initializeBank();
        loadAccounts();
        loadUserData();
        loadTransactions();
        loadLoans();
        loadApprovedLoansData();
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

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    private void loadAccounts() {
        savingsAccounts.clear();
        checkingAccounts.clear();
        loanAccounts.clear();
        accounts.clear();
        LoadAccounts loadAccounts = new LoadAccounts();
        accounts = loadAccounts.loadAccounts();
//        LoadCheckingAccounts loadCheckingAccounts = new LoadCheckingAccounts();
//        checkingAccounts = loadCheckingAccounts.loadCheckingAccounts();


        if (accounts != null && accounts.size() > 0) {
            for (Account account : accounts) {
                if(account instanceof AccountSavings) {
                    savingsAccounts.add((AccountSavings) account);
                } else if (account instanceof AccountChecking) {
                    checkingAccounts.add((AccountChecking) account);
                } else if (account instanceof AccountLoan) {
                    loanAccounts.add((AccountLoan) account);
                }
                accountNumbers.add(account.getAccountNumber());
            }
        }
    }


    private void loadUserData() {
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
                for (Account account : accounts) {
                    if (account.getCustomerId() == customer.getId()) {
                        customer.getAccounts().add(account);
                    }
                }
//                for (Account account : checkingAccounts) {
//                    if (account.getCustomerId() == customer.getId()) {
//                        customer.getAccounts().add(account);
//                    }
//                }
//                for (Account account : loanAccounts) {
//                    if (account.getCustomerId() == customer.getId()) {
//                        customer.getAccounts().add(account);
//                    }
//                }
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

    private void loadTransactions() {
        ledger.clear();
        LoadTransactions loadTransactions = new LoadTransactions();
        ledger = loadTransactions.loadTransactions();
        //add transactions to accounts
        for (Account account : accounts) {
            for (Transaction transaction : ledger) {
                if (transaction.getAccountNumber().equals(account.getAccountNumber())) {
                    if (account.getTransactions() != null) {
                        account.getTransactions().add(transaction);
                    }
                }
            }
        }
    }

    private void loadLoans() {
        loans.clear();
        LoadLoanData loadLoanData = new LoadLoanData();
        loans = loadLoanData.loadLoanData();
        // add loan to customer
        for (Customer customer : customers) {
            for (Loan loan : loans) {
                if (loan.getCustomerId() == customer.getId()) {
                    if (customer.getLoans() != null) {
                        customer.getLoans().add(loan);
                    }
                }
            }
        }
    }

    public void loadAllData() {
        loadAccounts();
        loadUserData();
        loadTransactions();
        loadLoans();
        loadApprovedLoansData();
    }

    private void loadApprovedLoansData() {
        approvedLoans.clear();
        LoadApprovedLoansData loadApprovedLoanData = new LoadApprovedLoansData();
        approvedLoans = loadApprovedLoanData.loadApprovedLoansData();
        //find the loan corresponding to an approvedLoan

        if (approvedLoans != null) {
            for (Customer customer : customers) {
                for (ApprovedLoan approvedLoan : approvedLoans) {
                    if (approvedLoan.getCustomerId() == customer.getId()) {
                        if (customer.getApprovedLoans() != null) {
                            customer.getApprovedLoans().add(approvedLoan);
                            if(customer.getLoans()!=null){
                                for(Loan loan : customer.getLoans()){
                                    if(loan.getLoanId() == approvedLoan.getLoanId()){
                                        approvedLoan.setLoan(loan);
                                        System.out.println(approvedLoan);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
