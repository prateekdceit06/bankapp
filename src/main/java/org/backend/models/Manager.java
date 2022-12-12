package org.backend.models;


import org.backend.controllers.account.Transfer;
import org.backend.controllers.manager.*;
import org.backend.controllers.user.StockTransaction;
import org.backend.staticdata.Data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private List<Stock> stocks;
    private List<Stock> buyableStocks;
    private List<StockTransaction> stockTransactions;
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
        this.stocks = new ArrayList<>();
        this.buyableStocks = new ArrayList<>();
        this.stockTransactions = new ArrayList<>();
        initializeBank();
        loadAccounts();
        loadUserData();
        loadTransactions();
        loadLoans();
        loadApprovedLoansData();
        loadStockData();
        loadStockTransactions();
        updateStocks();
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

    public List<AccountLoan> getLoanAccounts() {
        return loanAccounts;
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

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<Stock> getBuyableStocks() {
        return buyableStocks;
    }

    public void setBuyableStocks(List<Stock> buyableStocks) {
        this.buyableStocks = buyableStocks;
    }

    public List<StockTransaction> getStockTransactions() {
        return stockTransactions;
    }

    public void setStockTransactions(List<StockTransaction> stockTransactions) {
        this.stockTransactions = stockTransactions;
    }

    private void loadAccounts() {
        savingsAccounts.clear();
        checkingAccounts.clear();
        loanAccounts.clear();
        accounts.clear();
        LoadAccounts loadAccounts = new LoadAccounts();
        accounts = loadAccounts.loadAccounts();


        if (accounts != null && accounts.size() > 0) {
            for (Account account : accounts) {
                if (account instanceof AccountSavings) {
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
        loadStockData();
        loadStockTransactions();
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
                            if (customer.getLoans() != null) {
                                for (Loan loan : customer.getLoans()) {
                                    if (loan.getLoanId() == approvedLoan.getLoanId()) {
                                        approvedLoan.setLoan(loan);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private void loadStockData() {
        stocks.clear();
        buyableStocks.clear();
        LoadStockData loadStockData = new LoadStockData();
        stocks = loadStockData.loadStockData();
        for (Stock stock : stocks) {
            if (stock.getTradable() == 1) {
                buyableStocks.add(stock);
            }
        }

    }


    private void loadStockTransactions() {
        stockTransactions.clear();
        LoadStockTransactions loadStockTransactions = new LoadStockTransactions();
        stockTransactions = loadStockTransactions.loadStockTransactions();
        //add stock transactions to accounts
        for (Account account : accounts) {
            if (account instanceof AccountNewSecurity) {
                for (StockTransaction stockTransaction : stockTransactions) {
                    if (stockTransaction.getAccountNumber().equals(account.getAccountNumber())) {
                        if (((AccountNewSecurity) account).getStockTransactions() != null) {
                            ((AccountNewSecurity) account).getStockTransactions().add(stockTransaction);
                        }
                    }
                }
            }
        }

        HashMap<Integer, Integer> stockBought = new HashMap<>();
        HashMap<Integer, Integer> stockSold = new HashMap<>();

        for (Customer customer : customers) {
            stockSold.clear();
            stockBought.clear();
            for (Account account : customer.getAccounts()) {
                if (account instanceof AccountNewSecurity) {
                    for (StockTransaction stockTransaction : stockTransactions) {
                        if (stockTransaction.getSold_flag() == 0) {
                            if (stockBought.containsKey(stockTransaction.getStockId())) {
                                stockBought.put(stockTransaction.getStockId(), stockBought.get(stockTransaction.getStockId()) + stockTransaction.getQuantity());
                            } else {
                                stockBought.put(stockTransaction.getStockId(), stockTransaction.getQuantity());
                            }
                        } else {
                            if (stockSold.containsKey(stockTransaction.getStockId())) {
                                stockSold.put(stockTransaction.getStockId(), stockSold.get(stockTransaction.getStockId()) + stockTransaction.getQuantity());
                            } else {
                                stockSold.put(stockTransaction.getStockId(), stockTransaction.getQuantity());
                            }
                        }
                    }
                    for (Integer key : stockBought.keySet()) {
                        if (stockSold.containsKey(key)) {
                            customer.getStockCount().put(key, stockBought.get(key) - stockSold.get(key));
                        } else {
                            customer.getStockCount().put(key, stockBought.get(key));
                        }
                    }
                }
            }
        }
    }

    public boolean payInterest(double interestRate, User loggedInUser) {
        boolean success = true;
        for (Account account : accounts) {
            if (account instanceof AccountSavings) {
                if (account.getAccountStatus() == 1 && account.getCustomerId() != 1) {
                    if (account.getAccountBalance() > 0) {
                        GetInterestDuration getInterestDuration = new GetInterestDuration();
                        int numberOfMonths = getInterestDuration.getInterestDuration(loggedInUser);
                        double interest = account.getAccountBalance() * numberOfMonths * interestRate / 100;
                        boolean transferSuccess = false;
                        Transfer transfer = new Transfer();
                        transferSuccess = transfer.transfer(interest, 0, account.getAccountNumber(),
                                Data.TransactionTypes.INTEREST_PAYMENT.toString(),
                                bankAccountNumber, loggedInUser);
                        success = success && transferSuccess;
                    }
                }
            }
        }
        boolean result = false;
        if (success) {
            UpdateInterestPaidDate updateInterestPaidDate = new UpdateInterestPaidDate();
            result = updateInterestPaidDate.updateInterestPaidDate(loggedInUser);
        }
        return (success && result);
    }

    public boolean updateStocks() {
        boolean updateStockSuccess = false;
        Stocks stocks = new Stocks();
        updateStockSuccess = stocks.updateStocks();
        return updateStockSuccess;
    }
}
