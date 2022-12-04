package org.userInterface;

import org.backend.Connect;
import org.backend.staticdata.SHA256;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Helper {
    public static void main(String[] args) throws SQLException {
        // connect to sqlite
        String url = "jdbc:sqlite:bank.db";
        Connect c = new Connect();
        Connection conn = c.createConnection();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(30);  // set timeout to 30 sec.
        // log a sample event
        eventLogger.logEvent("Sample event", stmt);
    }

    public static class eventLogger {

        public static void logEvent(String event, Statement stmt) throws SQLException {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);
            String sql = "INSERT INTO all_events (event, event_date) VALUES ('" + event + "', '" + now + "')";
            try {
                stmt.executeUpdate(sql);
                System.out.println("Event logged successfully");
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

        public static void deleteEvent(int id, Statement stmt) throws SQLException {
            String sql = "DELETE FROM all_events WHERE id = " + id;
            try {
                stmt.executeUpdate(sql);
                System.out.println("Event deleted successfully");
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

        public static void clearLogs(Statement stmt) throws SQLException {
            String sql = "DELETE FROM all_events";
            try {
                stmt.executeUpdate(sql);
                System.out.println("Logs cleared successfully");
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public class SessionHelpers {
        private String generateToken(String userName) throws NoSuchAlgorithmException {
            Random rand = new Random();
            int rand_int1 = rand.nextInt(1000000);
            String token = Integer.toString(Math.abs(SHA256.getSHA(rand_int1 + userName).hashCode()));
            return token;
        }

        private String activateTokenQuery(String userName, String encryptedSignInPassword) throws NoSuchAlgorithmException {
            String token = generateToken(userName);
            String query = "UPDATE user_details SET token = '" + token + "' " + "WHERE username = '" + userName + "'";
            return query;
        }

        private boolean matchPassword(String input, String hash) throws NoSuchAlgorithmException {
            // call the getSHA method
            String hashInput = SHA256.getSHA(input);
            // compare the hashInput with the hash
            return hashInput.equals(hash);
        }

        private boolean userLogin(Statement stmt, String userName, String encryptedSignInPassword) throws NoSuchAlgorithmException, SQLException {
            // add to all_events this user signed in
            String event = "User with username " + userName + " signed in.";
            if (matchPassword(userName, encryptedSignInPassword)) {
                String query = activateTokenQuery(userName, encryptedSignInPassword);
                try {
                    stmt.executeUpdate(query);
                    eventLogger.logEvent(event, stmt);
                    return true;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            } else {
                return false;
            }
        }

        private boolean logoutUser(Statement stmt, String userName) throws SQLException {
            String query = "UPDATE user_details SET token = '' WHERE username = '" + userName + "'";
            String event = "User with username " + userName + " logged out.";
            try {
                stmt.executeUpdate(query);
                eventLogger.logEvent(event, stmt);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        private boolean isActive(Statement stmt, String userName) throws SQLException {
            String query = "SELECT token FROM user_details WHERE username = '" + userName + "'";
            String event = "Checked if user with username " + userName + " is active.";
            try {
                ResultSet rs = stmt.executeQuery(query);
                eventLogger.logEvent(event, stmt);
                if (rs.next()) {
                    String token = rs.getString("token");
                    return !token.equals("");
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public class UserHelpers {
        private boolean isUser(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "'";
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        }

        private boolean isUserActive(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "' AND token != ''";
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        }

        private boolean isUserAdmin(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "' AND is_admin = 1";
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        }

        private boolean isUserCustomer(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "' AND is_admin = 0";
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        }

        private boolean isUserCustomerActive(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "' AND is_admin = 0 AND token != ''";
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        }

        private boolean isUserAdminActive(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "' AND is_admin = 1 AND token != ''";
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        }

        // get all customers who are not admin
        private ResultSet getCustomers(Statement stmt) throws SQLException {
            String query = "SELECT * FROM user_details WHERE is_admin = 0";
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        }

        private ResultSet getCustomer(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "'";
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        }

        private ResultSet getAdmins(Statement stmt) throws SQLException {
            String query = "SELECT * FROM user_details WHERE is_admin = 1";
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        }

        private ResultSet getAdmin(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "'";
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        }

        private boolean deleteUser(Statement stmt, String userName) throws SQLException {
            // is_active to 0
            String sql = "UPDATE user_details SET is_active = 0 WHERE username = '" + userName + "'";
            try {
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        private boolean updateUser(Statement stmt, String userName, String firstName, String lastName, String email, String password) throws SQLException {
            String query = "UPDATE user_details SET first_name = '" + firstName + "', last_name = '" + lastName + "', email = '" + email + "', password = '" + password + "' WHERE username = '" + userName + "'";
            try {
                stmt.executeUpdate(query);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        private ResultSet fetchAccountFromUser(Statement stmt, String userName) throws SQLException {
            // account_details is connected to user_details by customer_id, use inner join
            String query = "SELECT * FROM account_details INNER JOIN user_details ON account_details.customer_id = user_details.customer_id WHERE user_details.username = '" + userName + "'";
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        }

        private ResultSet getActiveAccounts(Statement stmt) {
            // get all logged in accounts
            String query = "SELECT * FROM user_details WHERE token != ''";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return rs;
        }

        private void logoutAllUsers(Statement stmt) throws SQLException {
            String query = "UPDATE user_details SET token = ''";
            stmt.executeUpdate(query);
        }
    }

    public class Admin {

        private boolean isAdmin(Statement stmt, String userName) throws SQLException {
            String query = "SELECT * FROM user_details WHERE username = '" + userName + "' AND is_admin = 1";
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        }

        private boolean isCurrentSessionAdmin(Statement stmt) throws SQLException {
            UserHelpers userHelpers = new UserHelpers();
            ResultSet rs_active = userHelpers.getActiveAccounts(stmt);
            // if rs_active has more than 1 row, then there are more than 1 active sessions, log out everyone and throw out error
            if (rs_active.next()) {
                if (rs_active.next()) {
                    userHelpers.logoutAllUsers(stmt);
                    throw new SQLException("More than 1 active session");
                } else {
                    // if there is only 1 active session, check if the user is admin
                    String userName = rs_active.getString("username");
                    return isAdmin(stmt, userName);
                }
            } else {
                return false;
            }
        }

        // If the user calling this function is the admin, flip the is_active switch on Customer to 1
        private void allowUser(Statement stmt, String userName) throws SQLException {
            UserHelpers userHelpers = new UserHelpers();
            userHelpers.getActiveAccounts(stmt);
            String query = "UPDATE user_details SET is_active = 1 WHERE username = '" + userName + "'";
            try {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
