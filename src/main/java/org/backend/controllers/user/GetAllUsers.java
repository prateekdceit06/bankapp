package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllUsers {
    public List<User> getAllUsers(User loggedInUser) {
        List<User> users = new ArrayList<>();
        if(loggedInUser.getIsAdmin()==1){
            Connect c = new Connect();
            Connection connection = c.createConnection();
            if (connection != null) {
                try {
                    PreparedStatement ps = connection.prepareStatement("SELECT id, first_name," +
                            " last_name, phone, address, email, username, is_active, is_admin, token," +
                            " is_employee, created_date, updated_date, has_collateral, has_loan, is_customer" +
                            " FROM user_details");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setFirstName(rs.getString("first_name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setPhone(rs.getString("phone"));
                        user.setAddress(rs.getString("address"));
                        user.setEmail(rs.getString("email"));
                        user.setUserName(rs.getString("username"));
                        user.setIsActive(rs.getInt("is_active"));
                        user.setIsAdmin(rs.getInt("is_admin"));
                        user.setIsEmployee(rs.getInt("is_employee"));
                        user.setToken(rs.getString("token"));
                        user.setCreatedAt(ConvertDate.convertStringToDate(rs.getString("created_date")));
                        user.setUpdatedAt(ConvertDate.convertStringToDate(rs.getString("updated_date")));
                        user.setHasCollateral(rs.getInt("has_collateral"));
                        user.setHasLoan(rs.getInt("has_loan"));
                        user.setIsCustomer(rs.getInt("is_customer"));
                        users.add(user);
                    }
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        // connection close failed.
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        return users;
    }

}
