import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class connectTest {

    // Replace with your SQLite database file path
    private static final String SQLITE_DB_FILEPATH = "jdbc:sqlite:C:/sqlite/db/test.db";

    @Test
    public void testJDBCConnection() {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(SQLITE_DB_FILEPATH);

            // If the connection is successful, this will print "Connection established"
            System.out.println("Connection established");

            // Close the connection
            conn.close();

        } catch (SQLException e) {
            fail("Failed to establish connection to the database: " + e.getMessage());
        }
    }

}
