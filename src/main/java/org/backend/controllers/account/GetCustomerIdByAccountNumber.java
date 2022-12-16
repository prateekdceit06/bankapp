package org.backend.controllers.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to get customer id by account number.
 */
public class GetCustomerIdByAccountNumber {
    public int getCustomerIdByAccountNumber(Connection connection, String accountNumber) {
        int customerId = 0;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT customer_id FROM " +
                    "account_details WHERE account_no = ?");
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            customerId = rs.getInt("customer_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerId;
    }
}
