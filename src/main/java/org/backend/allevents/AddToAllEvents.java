package org.backend.allevents;

import org.backend.Connect;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AddToAllEvents {
    public boolean addToAllEvents(Connection connection, String event) {
        boolean success = false;
        try {
            String query = "INSERT INTO all_events(event, event_date) " +
                    "VALUES(?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, event);
            String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
            pstmt.setString(2, ts);
            pstmt.executeUpdate();
            pstmt.close();
            success = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return success;
    }
}
