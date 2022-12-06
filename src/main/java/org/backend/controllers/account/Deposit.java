package org.backend.controllers.account;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.*;

public class Deposit {
    public static boolean deposit(double amount, double transactionFees, String fromAccountNumber,
                                  String transactionType, User loggedInUser) {
        boolean isDeposit = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        String token = loggedInUser.getToken();
        if (token.equals(loggedInUser.getToken())) {
            if (connection != null) {
                try {
                    PreparedStatement ps1 = connection.prepareStatement("SELECT balance, is_active FROM account_details WHERE account_no = ?");
                    ps1.setString(1, fromAccountNumber);
                    ResultSet rs = ps1.executeQuery();
                    if (rs.next()) {
                        if (rs.getInt("is_active") == 1) {
                            double balanceFrom = rs.getDouble("balance");
                            amount -= transactionFees;
                            balanceFrom += amount;
                            PreparedStatement ps3 = connection.prepareStatement("UPDATE account_details SET balance = ?, " +
                                    "updated_date=? WHERE account_no = ?");
                            ps3.setDouble(1, balanceFrom);
                            String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                            ps3.setString(2, ts);
                            ps3.setString(3, fromAccountNumber);
                            ps3.executeUpdate();
                            PreparedStatement ps = connection.prepareStatement("INSERT INTO bank_ledger " +
                                    "(from_account_no, transaction_type, credit_amount, transaction_date) " +
                                    "VALUES (?, ?, ?, ?)");
                            ps.setString(1, fromAccountNumber);
                            ps.setString(2, transactionType);
                            ps.setDouble(3, amount);
                            ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                            ps.setString(4, ts);
                            ps.executeUpdate();
                            isDeposit = true;
                            AddToAllEvents addToAllEvents = new AddToAllEvents();
                            addToAllEvents.addToAllEvents(connection, transactionType + " of amount " + amount + " " +
                                    "to account number " + fromAccountNumber + " was successful");

                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        return isDeposit;
    }
}
