package org.backend.controllers.loan;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.controllers.user.GetToken;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Backend logic for applying for a loan
 */
public class ApplyForLoan {
    public boolean applyForLoan(String loanStatus, double loanAmount, double collateralValue,User loggedInUser) {
        Connect c = new Connect();
        Connection connection = c.createConnection();
        boolean success = false;
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);


        if(loggedInUser.getToken().equals(token) && loggedInUser.getIsActive()==1 && loggedInUser.getHasCollateral()==1){
            if(connection!=null){
                try {
                    String query = "INSERT INTO user_loan_detail (customer_id, loan_status, " +
                            "loan_amount, collateral_value, loan_application_date) " +
                            "VALUES (?,?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, loggedInUser.getId());
                    pstmt.setString(2, loanStatus);
                    pstmt.setDouble(3, Double.parseDouble(Data.df.format(loanAmount)));
                    pstmt.setDouble(4, Double.parseDouble(Data.df.format(collateralValue)));
                    String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    pstmt.setString(5, ts);
                    pstmt.executeUpdate();
                    pstmt.close();
                    AddToAllEvents addToAllEvents = new AddToAllEvents();
                    addToAllEvents.addToAllEvents(connection,"New loan applied by customer with ID: " +
                            loggedInUser.getId() + " of amount " + loanAmount);
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
        }
        return success;
    }
}
