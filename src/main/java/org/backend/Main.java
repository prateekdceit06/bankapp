package org.backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.userInterface.*;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.SHA256;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;


public class Main {

    public static void main(String[] args) {
        Connect c = new Connect();
        Connection connection = c.createConnection();
        try {
            Bank bank = new Bank();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("select * from customer_details");
            while (rs.next()) {
                boolean pass = SHA256.matchSHA("0000", rs.getString("password"));
                System.out.println();
                System.out.println("First Name = " + rs.getString("first_name"));
                System.out.println("Last Name = " + rs.getString("last_name"));
                System.out.println("Username = " + rs.getString("username"));
                System.out.println("Password = " + pass);
                System.out.println("Phone = " + rs.getString("phone"));
                System.out.println("Address = " + rs.getString("address"));
                System.out.println("Email = " + rs.getString("email"));
                System.out.println("CustomerID = " + rs.getInt("id"));
                System.out.println("Active = " + rs.getInt("is_active"));
                System.out.println("Admin = " + rs.getInt("is_admin"));
                System.out.println("Employee = " + rs.getInt("is_employee"));
                LocalDateTime dt = ConvertDate.convertStringToDate(rs.getString("created_date"));
                System.out.println("Created Date = " + dt);
                System.out.println("-----------------------------------------------");
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        //customer registration form invoke
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerRegistration().setVisible(true);
            }
        });

        /* Create and display the login form */
        // java.awt.EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         new CustomerLogin().setVisible(true);
        //     }
        // });
    }
}
