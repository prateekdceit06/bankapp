package org.backend.controllers.account;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CloseAccount {
    public boolean closeAccount(Account account) {
        boolean isAccountClosed = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE account_details SET is_active = ? " +
                        "WHERE account_no = ?");
                ps.setString(1, "0");
                ps.setString(2, account.getAccountNumber());
                ps.executeUpdate();
                isAccountClosed = true;
                AddToAllEvents addToAllEvents = new AddToAllEvents();
                addToAllEvents.addToAllEvents(connection, " User " + account.getCustomerId() + " closed his account" +
                        " with account number " + account.getAccountNumber());
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
