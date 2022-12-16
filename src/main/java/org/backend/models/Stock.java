package org.backend.models;

import org.backend.controllers.stock.UpdateStock;

import java.time.LocalDateTime;

/**
 * A model for a stock.
 */
public class Stock {
    int stockId;
    String stockName;
    double currentPrice;
    int tradable;
    LocalDateTime stockPriceUpdationDate;

    public Stock(int stockId, String stockName, double stockPrice,
                 int tradable, LocalDateTime stockPriceUpdationDate) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.currentPrice = stockPrice;
        this.tradable = tradable;
        this.stockPriceUpdationDate = stockPriceUpdationDate;
    }

    public Stock(int stockId, double newStockPrice, int tradable) {
        this.stockId = stockId;
        this.currentPrice = newStockPrice;
        this.tradable = tradable;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getTradable() {
        return tradable;
    }

    public void setTradable(int tradable) {
        this.tradable = tradable;
    }

    public LocalDateTime getStockPriceUpdationDate() {
        return stockPriceUpdationDate;
    }

    public void setStockPriceUpdationDate(LocalDateTime stockPriceUpdationDate) {
        this.stockPriceUpdationDate = stockPriceUpdationDate;
    }

    // override toString() method
    @Override
    public String toString() {
        return "Stock [stockId=" + stockId + ", stockName=" + stockName + ", currentPrice=" + currentPrice
                + ", tradable=" + tradable + ", stockPriceUpdationDate="
                + stockPriceUpdationDate + "]";
    }

    public boolean updateStock(User loggedInUser, Stock updatedStock){
        boolean success = false;
        UpdateStock updateStock = new UpdateStock();
        success = updateStock.updateStock(loggedInUser, updatedStock);
        return success;
    }
}
