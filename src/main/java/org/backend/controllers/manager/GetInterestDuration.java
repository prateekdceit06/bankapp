package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.controllers.user.GetToken;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;

import java.sql.*;

/**
 * Get the interest duration of a user
 */
public class GetInterestDuration {
    public int getInterestDuration(User loggedInUser) {
        int payInterestForMonths = 0;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        if (loggedInUser.getId() == 1 && token.equals(loggedInUser.getToken())) {
            if (connection != null) {
                try {
                    PreparedStatement ps1 = connection.prepareStatement("SELECT value FROM referenced_data " +
                            "WHERE name = ?");
                    ps1.setString(1, Data.ReferencedVariables.interest_payment_date.toString());
                    ResultSet rs = ps1.executeQuery();
                    int interestPaymentMonth = 0;
                    if (rs.next() && rs.getString(("Value")) != null) {
                        String interestPaymentDate = rs.getString("value");
                        interestPaymentMonth = Integer.parseInt(interestPaymentDate.split("-")[1]);
                    }
                    if (interestPaymentMonth == 0) {
                        payInterestForMonths = 1;
                    } else {
                        String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                        int currentMonth = Integer.parseInt(ts.split("-")[1]);
                        if (currentMonth >= interestPaymentMonth) {
                            payInterestForMonths = currentMonth - interestPaymentMonth;
                        } else {
                            payInterestForMonths = 12 - interestPaymentMonth + currentMonth;
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
        }
        return payInterestForMonths;
    }
}
