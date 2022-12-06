package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.models.AccountChecking;
import org.backend.models.AccountSavings;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoadCheckingAccounts {
    public List<AccountChecking> loadCheckingAccounts() {
        List<AccountChecking> checkingAccounts = new ArrayList<>();
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        if (connection != null) {
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM account_details WHERE type = '" +
                        Data.AccountTypes.CHECKING + "'");
                while (resultSet.next()) {
                    String account_no = resultSet.getString("account_no");
                    int customer_id = resultSet.getInt("customer_id");
                    String type = resultSet.getString("type");
                    double balance = resultSet.getDouble("balance");
                    int isActive = resultSet.getInt("is_active");
                    LocalDateTime createdAt = ConvertDate.convertStringToDate(resultSet.getString("created_date"));
                    LocalDateTime updatedAt = ConvertDate.convertStringToDate(resultSet.getString("updated_date"));
                    AccountChecking accountChecking = new AccountChecking(customer_id, account_no, type, balance, isActive, createdAt, updatedAt);
                    checkingAccounts.add(accountChecking);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return checkingAccounts;
    }
}
