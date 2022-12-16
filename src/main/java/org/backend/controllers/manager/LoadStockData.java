package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.models.Stock;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Load all stocks from the database
 */
public class LoadStockData {
    public List<Stock> loadStockData(){
        List<Stock> stocks = new ArrayList<>();
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if(connection !=null)
        {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT stock_id, stock_name, " +
                        "current_price, tradable, price_update_date" +
                        " FROM stock");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int stockId = resultSet.getInt("stock_id");
                    String stockName = resultSet.getString("stock_name");
                    double stockPrice = resultSet.getDouble("current_price");
                    int tradable = resultSet.getInt("tradable");
                    LocalDateTime stockPriceUpdationDate = ConvertDate.convertStringToDate(resultSet.getString("price_update_date"));
                    Stock stock = new Stock(stockId, stockName, stockPrice, tradable, stockPriceUpdationDate);
                    stocks.add(stock);
                }
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e);
                }
            }
        }
        return stocks;
    }
}
