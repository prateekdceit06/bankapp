package org.backend.controllers.user;

import org.backend.Connect;
import org.backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetToken {
    public String getToken(User user) {
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        if(connection !=null){
            try{
                PreparedStatement ps = connection.prepareStatement("SELECT token FROM customer_details WHERE id = ?");
                ps.setInt(1, user.getId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("token");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }
        return null;
    }
}
