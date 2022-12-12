package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.controllers.user.GetToken;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class UpdateInterestPaidDate {
    public boolean updateInterestPaidDate(User loggedInUser) {
        boolean success = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        if(loggedInUser.getId()==1 && token.equals(loggedInUser.getToken())){
            if (connection != null) {
                try {
                    PreparedStatement ps3 = connection.prepareStatement("UPDATE referenced_data SET value = ? WHERE name = ?;");
                    String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    ps3.setString(1, ts);
                    ps3.setString(2, Data.ReferencedVariables.interest_payment_date.toString());
                    ps3.executeUpdate();
                    success = true;
                    AddToAllEvents addToAllEvents = new AddToAllEvents();
                    addToAllEvents.addToAllEvents(connection, "Interest paid date updated to " + ts);
                } catch (Exception e) {
                    System.err.println(e);
                } finally {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }
        }
        return success;
    }
}
