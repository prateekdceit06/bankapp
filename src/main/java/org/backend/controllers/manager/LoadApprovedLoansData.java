package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.models.ApprovedLoan;
import org.backend.models.Loan;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Load all approved loans from the database
 */
public class LoadApprovedLoansData {
    public List<ApprovedLoan> loadApprovedLoansData() {
        List<ApprovedLoan> approvedLoans = new ArrayList<>();
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT loan_id, customer_id, account_no, sanctioned_amount," +
                        " loan_interest, loan_interest_unit, remaining_loan_amount, duration_in_months," +
                        "monthly_payment_amount" +
                        " FROM approved_loan_details");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int loanId = resultSet.getInt("loan_id");
                    int customerId = resultSet.getInt("customer_id");
                    String accountNo = resultSet.getString("account_no");
                    double sanctionedAmount = resultSet.getDouble("sanctioned_amount");
                    double loanInterest = resultSet.getDouble("loan_interest");
                    String loanInterestUnit = resultSet.getString("loan_interest_unit");
                    double remainingLoanAmount = resultSet.getDouble("remaining_loan_amount");
                    int durationInMonths = resultSet.getInt("duration_in_months");
                    double monthlyPaymentAmount = resultSet.getDouble("monthly_payment_amount");
                    ApprovedLoan approvedLoan = new ApprovedLoan(loanId, customerId, accountNo, sanctionedAmount,
                            loanInterest, loanInterestUnit, remainingLoanAmount,
                            durationInMonths, monthlyPaymentAmount);
                    approvedLoans.add(approvedLoan);
                }
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e);
                }
            }
        }
        return approvedLoans;

    }
}
