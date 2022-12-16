package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Logout a user, expires token
 */
public class Logout {
    public boolean logout(User loggedInUser) {
        boolean isLogoutSuccessful = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        if(connection!=null){
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE user_details SET token = ? WHERE id = ?");
                ps.setString(1, null);
                ps.setInt(2, loggedInUser.getId());
                ps.executeUpdate();
                isLogoutSuccessful = true;
                AddToAllEvents addToAllEvents = new AddToAllEvents();
                addToAllEvents.addToAllEvents(connection, "User "+loggedInUser.getId()+" logged out.");
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

        return isLogoutSuccessful;
    }
}
