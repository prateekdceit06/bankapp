package org.tests;

import sun.rmi.runtime.Log;

import java.sql.*;
import java.util.ArrayList;

public class testConnection {

    ArrayList baseCheck = new ArrayList();

    public testConnection() {
        baseCheck = new ArrayList();
        baseCheck.add("sqlite_sequence");
        baseCheck.add("sqlite_stat1");
        baseCheck.add("sqlite_stat4");
        baseCheck.add("all_events");
        baseCheck.add("user_details");
        baseCheck.add("account_details");
        baseCheck.add("bank_ledger");
        baseCheck.add("user_loan_detail");
        baseCheck.add("approved_loan_details");
        baseCheck.add("referenced_data");
        baseCheck.add("stock");
        baseCheck.add("customer_stock");
    }

    public boolean testAll(String DB_URL) throws SQLException {
        boolean testPass = false;
        if (connectToDatabase(DB_URL) != null) {
            System.out.println("Connection established");
            if (listTables(connectToDatabase(DB_URL)) != null) {
                System.out.println("Tables listed, not null");
                ArrayList res = getResultSet(listTables(connectToDatabase(DB_URL)));
                for (int i = 0; i < res.size(); i++) {
                    if (baseCheck.contains(res.get(i))) {
                        testPass = true;
                    } else {
                        System.out.println("Table " + res.get(i) + " not found");
                        testPass = false;
                    }
                }
            } else {
                System.out.println("Tables null");
            }
        } else {
            System.out.println("Connection failed");
        }

        connectToDatabase(DB_URL).close();
        return testPass;
    }

    public Connection connectToDatabase(String DB_URL) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return connection;
    }

    public ResultSet listTables(Connection connection) {
        ResultSet rs = null;
        try {
            String query = "SELECT name FROM sqlite_master WHERE type='table'";
            rs = connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rs;
    }

    public ArrayList getResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        ArrayList resultSetArray = new ArrayList();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                resultSetArray.add(columnValue);
            }
        }

        return resultSetArray;
    }
}
