package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePassword {
    public boolean changePassword(String oldPassword, String newPassword, User loggedInUser) {
        boolean isPasswordChanged = false;
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT password,token FROM user_details WHERE id = ?");
                ps.setInt(1, loggedInUser.getId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    if (rs.getString("password").equals(oldPassword) && rs.getString("token").equals(token)) {
                        PreparedStatement ps1 = connection.prepareStatement("UPDATE user_details SET password = ? WHERE id = ?");
                        ps1.setString(1, newPassword);
                        ps1.setInt(2, loggedInUser.getId());
                        ps1.executeUpdate();
                        isPasswordChanged = true;
                        AddToAllEvents addToAllEvents = new AddToAllEvents();
                        addToAllEvents.addToAllEvents(connection, "User " + loggedInUser.getId() + " changed his password.");
                    }
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
        return isPasswordChanged;
    }
}
