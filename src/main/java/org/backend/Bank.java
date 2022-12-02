package org.backend;

import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.SHA256;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Bank {
    public Bank() {
        Connect c = new Connect();
        Connection connection = c.createConnection();

        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("select * from customer_details");
            if (!rs.next()) {
                Person p = new Person("Prateek", "Jain", "8574259796",
                        "41 Long Ave, Allston, MA, 02134", "abc@abc.com",
                        "prateekjain", 1, 1, 1);
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
                pstmt.setString(8, SHA256.getSHA("0000"));
                pstmt.setInt(9, p.getIsEmployee());
                pstmt.setInt(10, p.getIsAdmin());
                String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                pstmt.setString(11, ts);
                pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
