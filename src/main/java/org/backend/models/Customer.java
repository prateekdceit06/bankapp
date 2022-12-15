package org.backend.models;

import org.backend.controllers.stock.GetCurrentSellingPrice;
import org.backend.controllers.user.StockTransaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Customer extends User {

    private List<Account> accounts;
    private List<Loan> loans;

    private List<ApprovedLoan> approvedLoans;

    private HashMap<Integer, Integer> stockCount;

    private List<StockTransaction> stockTransactions;

    private List<StockTransaction> openPositions;
    private List<StockTransaction> closedPositions;

    //default constructor
    public Customer() {
        super();
        this.accounts = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.approvedLoans = new ArrayList<>();
        this.stockCount = new HashMap<>();
        this.stockTransactions = new ArrayList<>();
        this.openPositions = new ArrayList<>();
        this.closedPositions = new ArrayList<>();
    }

    public Customer(int id, String firstName, String lastName, String phone, String address,
                    String email, String userName, int isActive, int isAdmin, int isEmployee,
                    String token, LocalDateTime createdAt, LocalDateTime updatedAt, int hasCollateral,
                    int hasLoan, int isCustomer) {
        super(id, firstName, lastName, phone, address, email, userName, isActive,
                isAdmin, isEmployee, token, createdAt, updatedAt, hasCollateral, hasLoan, isCustomer);
        this.accounts = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.approvedLoans = new ArrayList<>();
        this.stockCount = new HashMap<>();
        this.stockTransactions = new ArrayList<>();
        this.openPositions = new ArrayList<>();
        this.closedPositions = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }


    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<ApprovedLoan> getApprovedLoans() {
        return approvedLoans;
    }

    public void setApprovedLoans(List<ApprovedLoan> approvedLoans) {
        this.approvedLoans = approvedLoans;
    }

    public HashMap<Integer, Integer> getStockCount() {
        return stockCount;
    }

    public void setStockCount(HashMap<Integer, Integer> stockCount) {
        this.stockCount = stockCount;
    }

    public List<StockTransaction> getStockTransactions() {
        return stockTransactions;
    }

    public void setStockTransactions(List<StockTransaction> stockTransactions) {
        this.stockTransactions = stockTransactions;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Customer{" +
                "accounts=" + accounts +
                '}';
    }

    public void getOpenPosition() {
        HashMap<Integer, Integer> sameTransaction = new HashMap<>();
        for (StockTransaction stockTransaction : stockTransactions) {
            if (sameTransaction.containsKey(stockTransaction.getFromTransactionId())) {
                sameTransaction.put(stockTransaction.getFromTransactionId(),
                        sameTransaction.get(stockTransaction.getFromTransactionId()) +
                                stockTransaction.getQuantity());
            } else {
                sameTransaction.put(stockTransaction.getFromTransactionId(), stockTransaction.getQuantity());
            }
        }

        for (StockTransaction stockTransaction : stockTransactions) {
            if (!sameTransaction.containsKey(stockTransaction.getStockTransactionId()) &&
                stockTransaction.getSold_flag() == 0) {
                openPositions.add(stockTransaction);
            }
            for (Integer key : sameTransaction.keySet()) {
                if (stockTransaction.getStockTransactionId() == key) {
                    if (stockTransaction.getQuantity() > sameTransaction.get(key)) {
                        StockTransaction tempStockTransaction = new StockTransaction(stockTransaction.getStockTransactionId(),
                                stockTransaction.getStockId(), stockTransaction.getAccountNumber(),
                                stockTransaction.getCustomerId(), stockTransaction.getQuantity() - sameTransaction.get(key),
                                stockTransaction.getStatus(), stockTransaction.getBuyPrice(),
                                stockTransaction.getSellPrice(), stockTransaction.getSold_flag(),
                                stockTransaction.getTransactionDate(), stockTransaction.getFromTransactionId());
                        openPositions.add(tempStockTransaction);
                    } else {
                        closedPositions.add(stockTransaction);
                    }
                }
            }
        }
    }


    public double calculateRealisedProfit() {
        double realisedProfit = 0;
        for(StockTransaction stockTransaction: stockTransactions){
            if(stockTransaction.getSold_flag()==1){
                realisedProfit += (stockTransaction.getSellPrice() -
                        getStockTransactionFromFromTransactionId(stockTransaction.getFromTransactionId()).getBuyPrice())
                        * stockTransaction.getQuantity();
            }
        }
        return realisedProfit;
    }

    public double calculateUnrealisedProfit() {
        double unrealisedProfit = 0;
        GetCurrentSellingPrice getCurrentSellingPrice = new GetCurrentSellingPrice();
        for(StockTransaction stockTransaction: openPositions){
            unrealisedProfit += (getCurrentSellingPrice.getCurrentSellingPrice(stockTransaction.getStockId()) -
                    stockTransaction.getBuyPrice()) * stockTransaction.getQuantity();
        }
        System.out.println("Unrealised Profit: " + unrealisedProfit);
        return unrealisedProfit;
    }

    // get stock transaction from fromTransactionId
    public StockTransaction getStockTransactionFromFromTransactionId(int fromTransactionId) {
        for (StockTransaction stockTransaction : stockTransactions) {
            if (stockTransaction.getStockTransactionId() == fromTransactionId) {
                return stockTransaction;
            }
        }
        return null;
    }
}




