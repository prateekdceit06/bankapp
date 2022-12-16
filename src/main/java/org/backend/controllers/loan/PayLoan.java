package org.backend.controllers.loan;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.controllers.user.GetToken;
import org.backend.models.ApprovedLoan;
import org.backend.models.Customer;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.*;

import static org.backend.staticdata.Data.df;

/**
 * Backend logic for paying a loan
 */
public class PayLoan {
    public double payLoan(int loanId, double amount, User loggedInUser) {
        double amountDeducted = amount;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        if (connection != null) {
            if (loggedInUser.getIsActive() == 1 && loggedInUser.getToken().equals(token)
                    && loggedInUser.getHasLoan() == 1) {
                try {
                    PreparedStatement ps = connection.prepareStatement("Select remaining_loan_amount " +
                            "from approved_loan_details " +
                            "WHERE loan_id = ?");
                    ps.setInt(1, loanId);
                    ResultSet rs = ps.executeQuery();
                    double remainingLoanAmount = rs.getDouble("remaining_loan_amount");
                    if (remainingLoanAmount < amount) {
                        amountDeducted = remainingLoanAmount;
                    }
                    ps = connection.prepareStatement("UPDATE approved_loan_details SET " +
                            "remaining_loan_amount = remaining_loan_amount - ?, updated_date = ? " +
                            "WHERE loan_id = ?");
                    ps.setDouble(1, Double.parseDouble(df.format(amountDeducted)));
                    String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    ps.setString(2, ts);
                    ps.setInt(3, loanId);
                    ps.executeUpdate();
                    double totalDue=0.0;
                    for(ApprovedLoan approvedLoan : ((Customer)loggedInUser).getApprovedLoans()) {
                        totalDue += approvedLoan.getRemainingLoanAmount();
                    }
                    if(totalDue-amountDeducted == 0.0){
                        ps = connection.prepareStatement("UPDATE user_details SET " +
                                "has_loan=?, updated_date = ? " +
                                "WHERE id = ?");
                        ps.setInt(1, 0);
                        ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                        ps.setString(2, ts);
                        ps.setInt(3, loggedInUser.getId());
                        ps.executeUpdate();
                    }
                    AddToAllEvents addToAllEvents = new AddToAllEvents();
                    addToAllEvents.addToAllEvents(connection, "Loan amount " + amountDeducted + " paid for loan id " + loanId);
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
        }
        return amountDeducted;
    }
}
