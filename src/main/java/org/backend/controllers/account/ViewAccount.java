package org.backend.controllers.account;

import org.backend.Connect;
import org.backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to get account details by account number.
 */
public class ViewAccount {
    public boolean viewAccount(String accountNumber, User user) {
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        boolean isAccountFound = false;
        String token = user.getToken();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM account_details WHERE account_no = ?");
                ps.setString(1, accountNumber);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    if ((user.getIsAdmin() == 1) ||
                            (user.getId() == rs.getInt("customer_id") &&
                                    user.getToken().equals(token))) {
                        isAccountFound = true;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return isAccountFound;
    }
}
