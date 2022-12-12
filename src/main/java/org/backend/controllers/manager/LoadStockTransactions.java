package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.controllers.user.StockTransaction;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoadStockTransactions {
    public List<StockTransaction> loadStockTransactions(){
        List<StockTransaction> stockTransactions = new ArrayList<>();
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if(connection !=null)
        {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT stock_transaction_id, stock_id, " +
                        "account_no, customer_id, no_of_stock, status, " +
                        "buy_amount, sell_amount, sold_flag, transaction_date, from_transaction_id " +
                        " FROM customer_stock");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int stockTransactionId = resultSet.getInt("stock_transaction_id");
                    int stockId = resultSet.getInt("stock_id");
                    String accountNo = resultSet.getString("account_no");
                    int customerId = resultSet.getInt("customer_id");
                    int noOfStock = resultSet.getInt("no_of_stock");
                    String status = resultSet.getString("status");
                    double buyAmount = resultSet.getDouble("buy_amount");
                    double sellAmount = resultSet.getDouble("sell_amount");
                    int soldFlag = resultSet.getInt("sold_flag");
                    int fromTransactionId = resultSet.getInt("from_transaction_id");
                    LocalDateTime transactionDate = ConvertDate.convertStringToDate(resultSet.getString("transaction_date"));
                    StockTransaction stockTransaction = new StockTransaction(stockTransactionId, stockId, accountNo,
                            customerId, noOfStock, status, buyAmount, sellAmount, soldFlag, transactionDate, fromTransactionId);
                    stockTransactions.add(stockTransaction);
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
        return stockTransactions;
    }
}
