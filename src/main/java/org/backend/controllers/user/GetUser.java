package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GetUser {
    public HashMap<String, String> getUser(int id, User user) {
        HashMap<String, String> response = new HashMap<>();
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM customer_details WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    response.put("status", "success");
                    response.put("message", "User found");
                    response.put("id", String.valueOf(id));
                    response.put("firstName", rs.getString("first_name"));
                    response.put("lastName", rs.getString("last_name"));
                    response.put("phone", rs.getString("phone"));
                    response.put("address", rs.getString("address"));
                    response.put("email", rs.getString("email"));
                    response.put("userName", rs.getString("username"));
                    response.put("isActive", String.valueOf(rs.getInt("is_active")));
                    response.put("isAdmin", String.valueOf(rs.getInt("is_employee")));
                    response.put("isEmployee", String.valueOf(rs.getInt("is_employee")));
                    response.put("token", rs.getString("token"));
                    response.put("createdAt", rs.getString("created_date"));
                    response.put("updatedAt", rs.getString("updated_date"));
                    if (user.getIsAdmin() == 1) {
                        return response;
                    }
                    if (user.getId() == rs.getInt("id") && user.getToken().equals(rs.getString("token"))) {
                        return response;
                    }
                    response.clear();
                    response.put("status", "error");
                    response.put("message", "You are not authorized to view this user");
                    return response;
                } else {
                    response.put("status", "failure");
                    response.put("message", "User not found");
                }
                rs.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e);
                }
            }
        }
        return response;
    }

    public HashMap<String, String> getUser(String userName, User user) {
        HashMap<String, String> response = new HashMap<>();
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if (connection!=null){
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM customer_details WHERE username = ?");
                ps.setString(1, userName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    response.put("status", "success");
                    response.put("message", "User found");
                    response.put("id", rs.getString("id"));
                    response.put("firstName", rs.getString("first_name"));
                    response.put("lastName", rs.getString("last_name"));
                    response.put("phone", rs.getString("phone"));
                    response.put("address", rs.getString("address"));
                    response.put("email", rs.getString("email"));
                    response.put("userName", rs.getString("username"));
                    response.put("isActive", String.valueOf(rs.getInt("is_active")));
                    response.put("isAdmin", String.valueOf(rs.getInt("is_employee")));
                    response.put("isEmployee", String.valueOf(rs.getInt("is_employee")));
                    response.put("token", rs.getString("token"));
                    response.put("createdAt", rs.getString("created_date"));
                    response.put("updatedAt", rs.getString("updated_date"));
                    if (user.getIsAdmin() == 1) {
                        return response;
                    }
                    if (user.getId() == rs.getInt("id") && user.getToken().equals(rs.getString("token"))) {
                        return response;
                    }
                    response.clear();
                    response.put("status", "error");
                    response.put("message", "You are not authorized to view this user");
                    return response;
                } else {
                    response.put("status", "failure");
                    response.put("message", "User not found");
                }
                rs.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                        connection.close();
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e);
                }
            }
        }
        return response;
    }
}
