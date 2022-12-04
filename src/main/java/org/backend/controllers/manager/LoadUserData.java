package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoadUserData {
    public List<User> loadUserData() {
        List<User> activeUsers = new ArrayList<>();
        List<User> inactiveUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user_details");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String phone = resultSet.getString("phone");
                    String address = resultSet.getString("address");
                    String email = resultSet.getString("email");
                    String userName = resultSet.getString("username");
                    int isActive = resultSet.getInt("is_active");
                    int isAdmin = resultSet.getInt("is_admin");
                    int isEmployee = resultSet.getInt("is_employee");
                    String token = resultSet.getString("token");
                    LocalDateTime createdAt = ConvertDate.convertStringToDate(resultSet.getString("created_date"));
                    LocalDateTime updatedAt = ConvertDate.convertStringToDate(resultSet.getString("updated_date"));
                    int hasCollateral = resultSet.getInt("has_collateral");
                    int hasLoan = resultSet.getInt("has_loan");
                    int isCustomer = resultSet.getInt("is_customer");
                    User user = new User(id, firstName, lastName, phone, address,
                            email, userName, isActive, isAdmin, isEmployee, token,
                            createdAt, updatedAt, hasCollateral, hasLoan, isCustomer);
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return users;
    }
}
