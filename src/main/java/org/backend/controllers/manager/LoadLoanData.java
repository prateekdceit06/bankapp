package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.models.Loan;
import org.backend.models.Transaction;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Load all loans from the database
 */
public class LoadLoanData {
    public List<Loan> loadLoanData() {
        List<Loan> loans = new ArrayList<>();
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT loan_id, customer_id, loan_status," +
                        " loan_amount, collateral_value, loan_application_date, loan_decision_date" +
                        " FROM user_loan_detail");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int loanId = resultSet.getInt("loan_id");
                    int customerId = resultSet.getInt("customer_id");
                    String loanStatus = resultSet.getString("loan_status");
                    double loanAmount = resultSet.getDouble("loan_amount");
                    double collateralValue = resultSet.getDouble("collateral_value");
                    LocalDateTime loanApplicationDate = ConvertDate.convertStringToDate(resultSet.getString("loan_application_date"));
                    LocalDateTime loanDecisionDate = ConvertDate.convertStringToDate(resultSet.getString("loan_decision_date"));
                    Loan loan = new Loan(loanId, customerId, loanStatus, loanAmount, collateralValue, loanApplicationDate, loanDecisionDate);
                    loans.add(loan);
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
        return loans;
    }
}
