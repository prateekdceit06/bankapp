package org.backend.controllers.user;

import java.time.LocalDateTime;

public class StockTransaction {
    private int stockTransactionId;
    private int stockId;
    private String accountNumber;
    private int customerId;
    private int quantity;
    private String status;
    private double buyPrice;
    private double sellPrice;
    private int sold_flag;
    private LocalDateTime transactionDate;

    public StockTransaction() {
    }

    public StockTransaction(int stockTransactionId, int stockId, String accountNumber, int customerId,
                            int quantity, String status, double buyPrice, double sellPrice,
                            int sold_flag, LocalDateTime transactionDate) {
        this.stockTransactionId = stockTransactionId;
        this.stockId = stockId;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.quantity = quantity;
        this.status = status;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.sold_flag = sold_flag;
        this.transactionDate = transactionDate;
    }

    public int getStockTransactionId() {
        return stockTransactionId;
    }

    public void setStockTransactionId(int stockTransactionId) {
        this.stockTransactionId = stockTransactionId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getSold_flag() {
        return sold_flag;
    }

    public void setSold_flag(int sold_flag) {
        this.sold_flag = sold_flag;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    //override toString() method
    @Override
    public String toString() {
        return "StockTransaction{" +
                "stockTransactionId=" + stockTransactionId +
                ", stockId=" + stockId +
                ", accountNumber='" + accountNumber + '\'' +
                ", customerId=" + customerId +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", sold_flag=" + sold_flag +
                ", transactionDate=" + transactionDate +
                '}';
    }

}
