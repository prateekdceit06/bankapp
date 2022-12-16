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
 * Loan reject and approve logic
 */

public class ApproveRejectLoan {
    public boolean approveLoan(int loanId, int customerId, String accountNo, double sanctionedAmount,
                                     String loanStatus, double loanInterest,
                                     String loanInterestUnit, int loanDuration, User loggedInUser) {
        boolean success = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        if (loggedInUser.getToken().equals(token) && connection != null && loggedInUser.getId()==1) {
            try {
                if (loggedInUser.getId() == 1 && loanStatus.equals(Data.LoanStatus.APPROVED.toString())) {
                    String query = "INSERT INTO approved_loan_details(loan_id, customer_id, account_no, sanctioned_amount, " +
                            "loan_interest, loan_interest_unit, remaining_loan_amount, duration_in_months, monthly_payment_amount," +
                            " created_date) " +
                            "VALUES(?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    double effetiveInterest = 0;
                    if (loanInterestUnit.equals(Data.LoanInterestUnit.MONTHLY.toString())) {
                        effetiveInterest = loanInterest;
                    } else  if (loanInterestUnit.equals(Data.LoanInterestUnit.YEARLY.toString())) {
                        effetiveInterest = loanInterest/12;
                    }


                    double totalLoanAmount = sanctionedAmount + (sanctionedAmount * loanInterest * loanDuration / 100);
                    pstmt.setInt(1, loanId);
                    pstmt.setInt(2, customerId);
                    pstmt.setString(3, accountNo);
                    pstmt.setDouble(4, Double.parseDouble(Data.df.format(sanctionedAmount)));
                    pstmt.setDouble(5, Double.parseDouble(Data.df.format(loanInterest)));
                    pstmt.setString(6, loanInterestUnit);
                    pstmt.setDouble(7, Double.parseDouble(Data.df.format(totalLoanAmount)));
                    pstmt.setInt(8, loanDuration);
                    pstmt.setDouble(9, Double.parseDouble(Data.df.format(totalLoanAmount/loanDuration)));
                    String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    pstmt.setString(10, ts);
                    pstmt.executeUpdate();
                    pstmt.close();
                }

                PreparedStatement ps1 = connection.prepareStatement("UPDATE user_loan_detail " +
                        "SET loan_status = ?, loan_decision_date=? WHERE loan_id = ?");
                ps1.setString(1, loanStatus);
                String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                ps1.setString(2, ts);
                ps1.setInt(3, loanId);
                ps1.executeUpdate();
                PreparedStatement ps2 = connection.prepareStatement("UPDATE user_details " +
                        "SET has_loan = ?, updated_date=? WHERE id = ?");
                ps2.setInt(1, 1);
                ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                ps2.setString(2, ts);
                ps2.setInt(3, customerId);
                ps2.executeUpdate();
                AddToAllEvents addToAllEvents = new AddToAllEvents();
                addToAllEvents.addToAllEvents(connection, "Loan " + loanStatus + " for loan id " + loanId);
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

    public boolean rejectLoan(int loanId, String loanStatus, User loggedInUser) {
        boolean success = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        if (loggedInUser.getToken().equals(token) && connection != null && loggedInUser.getId()==1) {
            try {
                PreparedStatement ps1 = connection.prepareStatement("UPDATE user_loan_detail " +
                        "SET loan_status = ?, loan_decision_date=? WHERE loan_id = ?");
                ps1.setString(1, loanStatus);
                String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                ps1.setString(2, ts);
                ps1.setInt(3, loanId);
                ps1.executeUpdate();
                AddToAllEvents addToAllEvents = new AddToAllEvents();
                addToAllEvents.addToAllEvents(connection, "Loan " + loanStatus + " for loan id " + loanId);
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
