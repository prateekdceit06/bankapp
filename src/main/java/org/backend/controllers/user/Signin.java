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

public class Signin {
    public HashMap<String, String> signin(String userName, String encryptedSignInPassword) {
        Connect c = new Connect();
        Connection connection = c.createConnection();
        HashMap<String, String> response = new HashMap<>();
        if(connection!=null){
            try {
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                ResultSet rs = statement.executeQuery("select password from user_details " +
                        "where username = '" + userName + "'");
                if (rs.next()) {
                    String pass = rs.getString("password");
                    if (pass.equals(encryptedSignInPassword)) {
                        Random rand = new Random();
                        int rand_int1 = rand.nextInt(1000000);
                        String token = Integer.toString(Math.abs(SHA256.getSHA(rand_int1 + userName).hashCode()));
                        response.put("token", token);
                        String query = "UPDATE user_details SET token = '" + token + "' " +
                                "WHERE username = '" + userName + "'";
                        statement.executeUpdate(query);
                        query = "SELECT id, first_name, last_name, phone, address, email, is_active, " +
                                "is_admin, is_employee, token, has_loan, has_collateral, is_customer, " +
                                "created_date, updated_date " +
                                "FROM user_details WHERE username = '" + userName + "'";
                        rs = statement.executeQuery(query);
                        if (rs.next()) {
                            response.put("id", rs.getString("id"));
                            response.put("firstName", rs.getString("first_name"));
                            response.put("lastName", rs.getString("last_name"));
                            response.put("phone", rs.getString("phone"));
                            response.put("address", rs.getString("address"));
                            response.put("email", rs.getString("email"));
                            response.put("username", userName);
                            response.put("isActive", rs.getString("is_active"));
                            response.put("isAdmin", rs.getString("is_admin"));
                            response.put("isEmployee", rs.getString("is_employee"));
                            response.put("token", rs.getString("token"));
                            response.put("hasLoan", rs.getString("has_loan"));
                            response.put("hasCollateral", rs.getString("has_collateral"));
                            response.put("isCustomer", rs.getString("is_customer"));
                            response.put("createdAt", rs.getString("created_date"));
                            response.put("updatedAt", rs.getString("updated_date"));
                            response.put("status", "success");
                            response.put("message", "Sign in successful");
                            AddToAllEvents addToAllEvents = new AddToAllEvents();
                            addToAllEvents.addToAllEvents(connection, "User with id "+rs.getString("id")+" signed in.");
                        }
                        rs.close();
                    } else {
                        response.put("status", "failure");
                        response.put("message", "Incorrect password");
                    }
                }
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
