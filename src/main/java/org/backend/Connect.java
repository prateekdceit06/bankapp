package org.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    static final String DB_URL = "jdbc:sqlite:src/bank.db";
    public Connect (){}
    public Connection createConnection() {

        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        return connection;
    }
}
