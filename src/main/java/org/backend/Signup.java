package org.backend;

import org.backend.staticdata.ConvertDate;
import java.sql.*;

public class Signup {
    public boolean signup(String firstName, String lastName, String phone, String address, String email, String userName,
                       String encryptedPassword, int is_employee, int is_admin) {
        Connect c = new Connect();
        Connection connection = c.createConnection();
        boolean success = false;
        try {
            Person p = new Person(firstName, lastName, phone, address, email, userName, 1, is_admin, is_employee);
            String query = "INSERT INTO customer_details(id, first_name, last_name, phone, address, " +
                    "email, username, password, is_employee, is_admin, created_date) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, p.getId());
            pstmt.setString(2, p.getFirstName());
            pstmt.setString(3, p.getLastName());
            pstmt.setString(4, p.getPhone());
            pstmt.setString(5, p.getAddress());
            pstmt.setString(6, p.getEmail());
            pstmt.setString(7, p.getUserName());
            pstmt.setString(8, encryptedPassword);
            pstmt.setInt(9, p.getIsEmployee());
            pstmt.setInt(10, p.getIsAdmin());
            String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
            pstmt.setString(11, ts);
            pstmt.executeUpdate();
            pstmt.close();
            success = true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return success;
    }
}
