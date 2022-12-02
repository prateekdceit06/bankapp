package org.backend;

import org.backend.staticdata.ConvertDate;

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
                        "41 Long Ave, Allston, MA, 02134", "abc@abc.com");
                String query = "INSERT INTO customer_details(id, first_name, last_name, phone, address, " +
                        "email, username, is_employee, is_admin, created_date) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, p.getId());
                pstmt.setString(2, p.getFirstName());
                pstmt.setString(3, p.getLastName());
                pstmt.setString(4, p.getPhone());
                pstmt.setString(5, p.getAddress());
                pstmt.setString(6, p.getEmail());
                pstmt.setString(7, "prateekjain");
                pstmt.setInt(8, 1);
                pstmt.setInt(9, 1);
                String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                pstmt.setString(10, ts);
                pstmt.executeUpdate();
                pstmt.close();
            }
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
    }
}
