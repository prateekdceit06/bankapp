package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;
import java.sql.*;

public class Signup {
    public boolean signup(String firstName, String lastName, String phone, String address, String email, String userName,
                       String encryptedPassword, int isEmployee, int isAdmin, int hasCollateral) {
        Connect c = new Connect();
        Connection connection = c.createConnection();
        boolean success = false;
        if(connection!=null){
            try {
                User p = new User(firstName, lastName, phone, address, email, userName, 1, isAdmin,
                        isEmployee, hasCollateral,0,0);
                String query = "INSERT INTO user_details(id, first_name, last_name, phone, address, " +
                        "email, username, password, is_employee, is_admin, created_date, has_collateral, " +
                        "has_loan, is_customer) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, p.getId());
                pstmt.setString(2, p.getFirstName());
                pstmt.setString(3, p.getLastName());
                pstmt.setString(4, p.getPhone());
                pstmt.setString(5, p.getAddress());
                pstmt.setString(6, p.getEmail());
                pstmt.setString(7, p.getUserName());
                pstmt.setString(8, encryptedPassword);
                pstmt.setInt(9, p.getIsEmployee());
                pstmt.setInt(10, p.getIsAdmin());
                String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                pstmt.setString(11, ts);
                pstmt.setInt(12, hasCollateral);
                pstmt.setInt(13, 0);
                pstmt.setInt(14, 0);
                pstmt.executeUpdate();
                pstmt.close();
                AddToAllEvents addToAllEvents = new AddToAllEvents();
                addToAllEvents.addToAllEvents(connection,"New user signed up with user ID: " + p.getId() + " and following details : " + p);
                success = true;

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                        connection.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return success;
    }
}
