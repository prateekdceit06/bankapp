package org.userInterface;

import org.backend.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends JFrame {
    // JDBC driver and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    // UI components
    JButton btnConnectDisplayAll = new JButton("Click here to display all customers");
    JButton btnRegistration = new JButton("Click here to register");
    JButton btnLogin = new JButton("Click here to login");
    JTextArea taData = new JTextArea();
    JScrollPane spData = new JScrollPane(taData);

    public Main() {
        setTitle("SQLite Display");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create UI layout
        setLayout(new BorderLayout());
        // create a Java UI panel with display all button, registration button and login button
        JPanel pnlButtons = new JPanel(new GridLayout(3, 1));
        pnlButtons.add(btnConnectDisplayAll);
        pnlButtons.add(btnRegistration);
        pnlButtons.add(btnLogin);
        add(pnlButtons, BorderLayout.NORTH);
        // spdata in center
        add(spData, BorderLayout.CENTER);

        // Add action listener to connect button
        btnConnectDisplayAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Write a function to create a JTable and display it for SQL query "SELECT * from customer_details"

                    Connect c = new Connect();
                    Connection conn = c.createConnection();
                    Statement stmt = conn.createStatement();
                    stmt.setQueryTimeout(30);  // set timeout to 30 sec.

                    // inner join SQL query to fetch customer details and account details
                    String sql = "SELECT customer_details.last_name,account_details.account_number, account_details.balance from customer_details INNER JOIN account_details ON customer_details.id = account_details.customer_id";
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    String[] columnNames = new String[columnsNumber];
                    for (int i = 1; i <= columnsNumber; i++) {
                        columnNames[i - 1] = rsmd.getColumnName(i);
                    }
                    String[][] data = new String[100][columnsNumber];
                    int row = 0;
                    while (rs.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {
                            data[row][i - 1] = rs.getString(i);
                        }
                        row++;
                    }
                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    add(scrollPane, BorderLayout.WEST);
                    setVisible(true);

                } catch (SQLException se) {
                    // Handle errors for JDBC
                    se.printStackTrace();
                } catch (Exception ex) {
                    // Handle errors for Class.forName
                    ex.printStackTrace();
                }
            }
        });

        //registration screen display
        btnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Launch the login window in this window
                CustomerRegistration cr = new CustomerRegistration();
                cr.setVisible(true);
            }
        });

        //login screen display
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Launch the login window in this window
                CustomerLogin cl = new CustomerLogin();
                cl.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // Create and show UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

}
