package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.models.AccountSavings;
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
 * Load all transactions from the database
 */
public class LoadTransactions {
    public List<Transaction>  loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT transaction_id, from_account_no, " +
                        "customer_id, transaction_type, debit_amount, credit_amount, is_active, transaction_date" +
                        " FROM bank_ledger");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int transactionId = resultSet.getInt("transaction_id");
                    String accountNumber = resultSet.getString("from_account_no");
                    int customerId = resultSet.getInt("customer_id");
                    String transactionType = resultSet.getString("transaction_type");
                    double debitAmount = resultSet.getDouble("debit_amount");
                    double creditAmount = resultSet.getDouble("credit_amount");
                    int isActive = resultSet.getInt("is_active");
                    LocalDateTime transactionDate = ConvertDate.convertStringToDate(resultSet.getString("transaction_date"));
                    Transaction transaction = new Transaction(transactionId, accountNumber, customerId, transactionType,
                            debitAmount, creditAmount, isActive, transactionDate);
                    transactions.add(transaction);
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
        return transactions;
    }
}
