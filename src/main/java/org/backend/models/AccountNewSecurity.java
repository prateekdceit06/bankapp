package org.backend.models;

import org.backend.controllers.stock.BuyStock;
import org.backend.controllers.stock.SellStock;
import org.backend.controllers.user.StockTransaction;
import org.backend.staticdata.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountNewSecurity extends Account {

    List<StockTransaction> stockTransactions;

    //default constructor
    public AccountNewSecurity() {
    }
    public AccountNewSecurity(int customerId, String accountType, double accountBalance, int accountStatus) {
        super(customerId, accountType, accountBalance, accountStatus);
        this.stockTransactions = new ArrayList<>();
    }

    public AccountNewSecurity(int customerId, String accountNumber, String accountType, double accountBalance,
                              int accountStatus, LocalDateTime accountCreationDate, LocalDateTime accountLastUpdatedDate) {
        super(customerId, accountNumber, accountType, accountBalance, accountStatus, accountCreationDate, accountLastUpdatedDate);
        this.stockTransactions = new ArrayList<>();
    }

    public List<StockTransaction> getStockTransactions() {
        return stockTransactions;
    }

    public void setStockTransactions(List<StockTransaction> stockTransactions) {
        this.stockTransactions = stockTransactions;
    }

    public boolean buyStock(Stock stock, String accountNumber, int quantity, User loggedInUser){
        boolean success;
        BuyStock buyStock = new BuyStock();
        success = buyStock.buyStock(stock.getStockId(), loggedInUser.getId(), accountNumber, quantity,
                Data.StockStatus.BOUGHT.toString(), stock.getCurrentPrice(), loggedInUser);
        return success;
    }

    public boolean sellStock(Stock stock,  String accountNumber,int quantity, User loggedInUser, int fromTransactionId){
        boolean success;
        SellStock sellStock = new SellStock();
        success = sellStock.sellStock(stock.getStockId(), loggedInUser.getId(), accountNumber, quantity,
                Data.StockStatus.SOLD.toString(), stock.getCurrentPrice(), loggedInUser, fromTransactionId);
        return success;
    }

}
