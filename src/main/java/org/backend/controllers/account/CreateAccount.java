package org.backend.controllers.account;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.controllers.user.GetToken;
import org.backend.models.Account;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CreateAccount {
    public boolean createAccount(Account account, User loggedInUser) {
        boolean accountCreated = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        GenerateAccountNumber generateAccountNumber = new GenerateAccountNumber();
        String accountNumber = generateAccountNumber.generateAccountNumber();
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        if (connection != null) {
            if (loggedInUser.getUserName() == "manager" || token.equals(loggedInUser.getToken())) {
                try {
                    String query = "INSERT INTO account_details(account_no, customer_id, type, balance, is_active, created_date) " +
                            "VALUES(?,?,?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, accountNumber);
                    pstmt.setInt(2, account.getCustomerId());
                    pstmt.setString(3, account.getAccountType());
                    pstmt.setDouble(4, account.getAccountBalance());
                    pstmt.setInt(5, 1);
                    String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    pstmt.setString(6, ts);
                    pstmt.executeUpdate();
                    pstmt.close();
                    PreparedStatement ps1 = connection.prepareStatement("UPDATE user_details SET is_customer = ? WHERE id = ?");
                    ps1.setString(1, "1");
                    ps1.setInt(2, loggedInUser.getId());
                    ps1.executeUpdate();
                    account.setAccountCreationDate(ConvertDate.convertStringToDate(ts));
                    account.setAccountNumber(accountNumber);
                    account.setAccountStatus(1);
                    AddToAllEvents addToAllEvents = new AddToAllEvents();
                    addToAllEvents.addToAllEvents(connection, "New account created with account number: " +
                            accountNumber + " and following details : " + account);
                    accountCreated = true;


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
        return accountCreated;
    }

}
