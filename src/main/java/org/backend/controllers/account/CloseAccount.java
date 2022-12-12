package org.backend.controllers.account;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.Account;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CloseAccount {
    public boolean closeAccount(Account account) {
        boolean isAccountClosed = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        if (connection != null) {
            try {
                double remainingBalance = 0;
                remainingBalance = Data.accountClosingFees;
                PreparedStatement ps = connection.prepareStatement("UPDATE account_details SET is_active = ?," +
                        "updated_date=?, balance=? " +
                        "WHERE account_no = ?");
                ps.setString(1, "0");
                String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                ps.setString(2, ts);
                ps.setDouble(3, Double.parseDouble(Data.df.format(remainingBalance)));
                ps.setString(4, account.getAccountNumber());
                ps.executeUpdate();
                isAccountClosed = true;
                AddToAllEvents addToAllEvents = new AddToAllEvents();
                addToAllEvents.addToAllEvents(connection, " User " + account.getCustomerId() + " closed his account" +
                        " with account number " + account.getAccountNumber());
                ps.close();
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
        return isAccountClosed;
    }
}
