package org.backend.controllers.stock;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.controllers.user.GetToken;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.backend.staticdata.Data.df;

/**
 * BuyStock class to let select users buy stocks
 */

public class BuyStock {
    public boolean buyStock(int stockId, int customerId, String accountNumber, int quantity, String stockStatus,
                            double stockPrice, User loggedInUser){
        Connect c = new Connect();
        Connection connection = c.createConnection();
        boolean success = false;
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        if(loggedInUser.getToken().equals(token) && loggedInUser.getIsActive()==1){
            if(connection!=null){
                try {
                    String query = "INSERT INTO customer_stock (stock_id, account_no, customer_id, no_of_stock, " +
                            "status, buy_amount, transaction_date) " +
                            "VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, stockId);
                    pstmt.setString(2, accountNumber);
                    pstmt.setInt(3, customerId);
                    pstmt.setInt(4, quantity);
                    pstmt.setString(5, stockStatus);
                    pstmt.setDouble(6, Double.parseDouble(Data.df.format(stockPrice)));
                    String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    pstmt.setString(7, ts);
                    pstmt.executeUpdate();
                    pstmt.close();
                    PreparedStatement ps = connection.prepareStatement("UPDATE account_details SET " +
                            "balance = account_details.balance - ?, updated_date = ? " +
                            "WHERE account_no = ?");
                    ps.setDouble(1, Double.parseDouble(df.format(stockPrice*quantity)));
                    ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    ps.setString(2, ts);
                    ps.setString(3, accountNumber);
                    ps.executeUpdate();
                    ps = connection.prepareStatement("INSERT INTO bank_ledger " +
                            "(from_account_no, transaction_type, debit_amount, transaction_date, customer_id) " +
                            "VALUES (?, ?, ?, ?, ?)");
                    ps.setString(1, accountNumber);
                    ps.setString(2, Data.TransactionTypes.BUY_STOCK.toString());
                    ps.setDouble(3, Double.parseDouble(Data.df.format(stockPrice)));
                    ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    ps.setString(4, ts);
                    ps.setInt(5, loggedInUser.getId());
                    ps.executeUpdate();
                    AddToAllEvents addToAllEvents = new AddToAllEvents();
                    addToAllEvents.addToAllEvents(connection,"New stock bought by customer with ID: " +
                            loggedInUser.getId() + " of stock ID: " + stockId + " of quantity " + quantity +
                            " of price " + stockPrice*quantity);
                    success = true;

                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        return success;
    }
}
