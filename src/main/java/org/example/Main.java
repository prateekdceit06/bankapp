package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/prateek/Documents/CS_611/Final_Project/bankapp/bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

//            statement.executeUpdate("drop table if exists person");
//            statement.executeUpdate("create table person (id integer, name string)");
//            statement.executeUpdate("insert into person values(1, 'leo')");
//            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from login");
            while (rs.next()) {
                // read the result set
                System.out.println("First Name = " + rs.getString("first_name"));
                System.out.println("Last Name = " + rs.getString("last_name"));
                System.out.println("Username = " + rs.getString("username"));
                System.out.println("Password = " + rs.getString("password"));
                System.out.println("Phone = " + rs.getString("phone"));
                System.out.println("Address = " + rs.getString("address"));
                System.out.println("Email = " + rs.getString("email"));
                System.out.println("CustomerID = " + rs.getInt("customer_id"));
                System.out.println("Active = " + rs.getInt("active"));
                System.out.println("Admin = " + rs.getInt("admin"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
