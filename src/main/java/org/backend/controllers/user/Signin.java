package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.staticdata.SHA256;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;

/**
 * Signin a user
 */
public class Signin {
    public HashMap<String, String> signin(String userName, String encryptedSignInPassword) {
        Connect c = new Connect();
        Connection connection = c.createConnection();
        HashMap<String, String> response = new HashMap<>();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                ResultSet rs = statement.executeQuery("select id, password, is_active from user_details " +
                        "where username = '" + userName + "'");
                if (rs.next()) {
                    if (rs.getInt("is_active") == 1) {
                        String pass = rs.getString("password");
                        if (pass.equals(encryptedSignInPassword)) {
                            Random rand = new Random();
                            int rand_int1 = rand.nextInt(1000000);
                            String token = Integer.toString(Math.abs(SHA256.getSHA(rand_int1 + userName).hashCode()));
                            String query = "UPDATE user_details SET token = '" + token + "' " +
                                    "WHERE username = '" + userName + "'";
                            Statement statement2 = connection.createStatement();
                            statement2.setQueryTimeout(30);
                            statement2.executeUpdate(query);
                            response.put("status", "success");
                            response.put("message", "Sign in successful");
                            AddToAllEvents addToAllEvents = new AddToAllEvents();
                            addToAllEvents.addToAllEvents(connection, "User with id " +
                                    rs.getString("id") + " signed in.");
                        } else {
                            response.put("status", "failure");
                            response.put("message", "Incorrect password");
                        }
                    } else {
                        response.put("status", "failure");
                        response.put("message", "User is not active");
                    }

                }
                rs.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return response;
    }
}
