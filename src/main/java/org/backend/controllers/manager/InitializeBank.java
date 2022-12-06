package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.controllers.account.CreateAccount;
import org.backend.models.AccountSavings;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;
import org.backend.staticdata.SHA256;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class InitializeBank {
    public String initializeBank(){
        Connect c = new Connect();
        Connection connection = c.createConnection();
        String accountNumber = null;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("select * from user_details");
            if (!rs.next()) {
                User p = new User("Manager", "Manager", "8574259796",
                        "41 Long Ave, Allston, MA, 02134", "abc@abc.com",
                        "manager", 1, 1, 1,0,0,1);
                String query = "INSERT INTO user_details(id, first_name, last_name, phone, address, " +
                        "email, username, password, is_employee, is_admin, created_date) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, p.getId());
                pstmt.setString(2, p.getFirstName());
                pstmt.setString(3, p.getLastName());
                pstmt.setString(4, p.getPhone());
                pstmt.setString(5, p.getAddress());
                pstmt.setString(6, p.getEmail());
                pstmt.setString(7, p.getUserName());
                pstmt.setString(8, SHA256.getSHA("0000"));
                pstmt.setInt(9, p.getIsEmployee());
                pstmt.setInt(10, p.getIsAdmin());
                String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                pstmt.setString(11, ts);
                pstmt.executeUpdate();
                pstmt.close();
                AccountSavings accountSavings = new AccountSavings(p.getId(), Data.AccountTypes.SAVINGS.toString(),
                        Data.bankInitialBalance, 1);
                CreateAccount createAccount = new CreateAccount();
                createAccount.createAccount(accountSavings, p, null);
                accountNumber = accountSavings.getAccountNumber();
            } else {
                PreparedStatement ps = connection.prepareStatement("SELECT account_no FROM account_details " +
                        "WHERE customer_id = '1'");
                ResultSet result = ps.executeQuery();
                if(result.next()){
                    accountNumber = result.getString("account_no");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return accountNumber;
    }
}
