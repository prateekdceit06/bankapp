package org.userInterface;

import org.backend.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class sampleDisplay extends JFrame {
    // JDBC driver and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";


    // SQLite connection
    Connection conn = null;

    // UI components
    JButton btnConnect = new JButton("Click here to display all customers");
    JTextArea taData = new JTextArea();
    JScrollPane spData = new JScrollPane(taData);

    public sampleDisplay() {
        setTitle("SQLite Display");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create UI layout
        setLayout(new BorderLayout());
        add(btnConnect, BorderLayout.NORTH);
        add(spData, BorderLayout.CENTER);

        // Add action listener to connect button
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Register JDBC driver
                    Class.forName(JDBC_DRIVER);

                    // Open a connection
                    System.out.println("Connecting to database...");
                    Connect c = new Connect();
                    Connection conn = c.createConnection();

                    // Execute query
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT * FROM customer_details";
                    ResultSet rs = stmt.executeQuery(sql);
                    System.out.println(rs.getString("first_name"));

                    // Clear text area
                    taData.setText("Data goes here");
                    try {
                        taData.setText("");
                        while (rs.next()) {
                            taData.append(rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getString("username") + " " + rs.getString("password") + " " + rs.getString("phone") + " " + rs.getString("address") + " " + rs.getString("email") + " " + rs.getInt("id") + " " + rs.getInt("is_active") + " " + rs.getInt("is_admin") + " " + rs.getInt("is_employee"));
                            taData.append("\n");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    // Close connection
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException se) {
                    // Handle errors for JDBC
                    se.printStackTrace();
                } catch (Exception ex) {
                    // Handle errors for Class.forName
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        // Create and show UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new sampleDisplay().setVisible(true);
            }
        });
    }

}
