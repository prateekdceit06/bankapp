package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UpdateUser {

    public boolean updateUser(User loggedInUser, User updatedUser) {
        boolean success = false;
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if (connection != null) {
            try {
                if ((loggedInUser.getIsAdmin() == 1 || loggedInUser.getId() == updatedUser.getId()) &&
                        loggedInUser.getToken().equals(loggedInUser.getToken())) {
                    //statement to update user
                    String query = "UPDATE user_details SET first_name =?, last_name=?, phone=?, address=?, " +
                            "email=?, username=?, is_active=?, is_employee=?, is_admin=?, updated_date=?, " +
                            "has_collateral=? WHERE id=?;";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, updatedUser.getFirstName());
                    pstmt.setString(2, updatedUser.getLastName());
                    pstmt.setString(3, updatedUser.getPhone());
                    pstmt.setString(4, updatedUser.getAddress());
                    pstmt.setString(5, updatedUser.getEmail());
                    pstmt.setString(6, updatedUser.getUserName());
                    pstmt.setInt(7, updatedUser.getIsActive());
                    pstmt.setInt(8, updatedUser.getIsEmployee());
                    pstmt.setInt(9, updatedUser.getIsAdmin());
                    String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    pstmt.setString(10, ts);
                    pstmt.setInt(11, updatedUser.getHasCollateral());
                    pstmt.setString(12, String.valueOf(updatedUser.getId()));
                    pstmt.executeUpdate();
                    pstmt.close();
                    AddToAllEvents addToAllEvents = new AddToAllEvents();
                    addToAllEvents.addToAllEvents(connection, "User : " + updatedUser.getId() + " has been updated to " + updatedUser);
                    success = true;
                }
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
