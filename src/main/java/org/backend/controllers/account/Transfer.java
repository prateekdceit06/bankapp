package org.backend.controllers.account;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;

import java.sql.*;

public class Transfer {
    public boolean transfer(double amount, double transactionFees, String fromAccountNumber, String transactionType, String toAccountNumber, User loggedInUser) {
        boolean isTransferred = false;
        Connect connect = new Connect();
        Connection connection = connect.createConnection();
        String token = loggedInUser.getToken();
        if (token.equals(loggedInUser.getToken())) {
            if (connection != null) {
                try {
                    PreparedStatement ps1 = connection.prepareStatement("SELECT balance, is_active FROM account_details WHERE account_no = ?");
                    ps1.setString(1, fromAccountNumber);
                    ResultSet rs = ps1.executeQuery();
                    PreparedStatement ps2 = connection.prepareStatement("SELECT balance, is_active FROM account_details WHERE account_no = ?");
                    ps2.setString(1, toAccountNumber);
                    ResultSet rs2 = ps2.executeQuery();
                    if (rs.next() && rs2.next()) {
                        if ((transactionType.equals(Data.TransactionTypes.ACCOUNT_CLOSING_FEE.toString())
                        || rs.getInt("is_active") == 1)&& rs2.getInt("is_active") == 1) {
                            double balanceFrom = rs.getDouble("balance");
                            if (balanceFrom >= amount) {
                                amount-=transactionFees;
                                balanceFrom -= amount;
                                PreparedStatement ps3 = connection.prepareStatement("UPDATE account_details SET balance = ?, " +
                                        "updated_date=? WHERE account_no = ?");
                                ps3.setDouble(1, Double.parseDouble(Data.df.format(balanceFrom)));
                                String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                                ps3.setString(2, ts);
                                ps3.setString(3, fromAccountNumber);
                                ps3.executeUpdate();
                                double balanceTo = rs2.getDouble("balance");
                                balanceTo += amount;
                                PreparedStatement ps4 = connection.prepareStatement("UPDATE account_details SET balance = ?," +
                                        "updated_date=? WHERE account_no = ?");
                                ps4.setDouble(1, Double.parseDouble(Data.df.format(balanceTo)));
                                ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                                ps4.setString(2, ts);
                                ps4.setString(3, toAccountNumber);
                                ps4.executeUpdate();
                                PreparedStatement ps = connection.prepareStatement("INSERT INTO bank_ledger " +
                                        "(from_account_no, transaction_type, debit_amount, transaction_date, customer_id) " +
                                        "VALUES (?, ?, ?, ?, ?)");
                                ps.setString(1, fromAccountNumber);
                                ps.setString(2, transactionType);
                                ps.setDouble(3, Double.parseDouble(Data.df.format(amount)));
                                ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                                ps.setString(4, ts);
                                ps.setInt(5, loggedInUser.getId());
                                ps.executeUpdate();
                                ps = connection.prepareStatement("INSERT INTO bank_ledger " +
                                        "(from_account_no, transaction_type, credit_amount, transaction_date, customer_id) " +
                                        "VALUES (?, ?, ?, ?, ?)");
                                ps.setString(1, toAccountNumber);
                                ps.setString(2, transactionType);
                                ps.setDouble(3, Double.parseDouble(Data.df.format(amount)));
                                ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                                ps.setString(4, ts);
                                GetCustomerIdByAccountNumber getCustomerIdByAccountNumber = new GetCustomerIdByAccountNumber();
                                int customerId = getCustomerIdByAccountNumber.getCustomerIdByAccountNumber(connection, toAccountNumber);
                                ps.setInt(5, customerId);
                                ps.executeUpdate();
                                isTransferred = true;
                                AddToAllEvents addToAllEvents = new AddToAllEvents();
                                addToAllEvents.addToAllEvents(connection, transactionType + " of amount " + amount + " " +
                                        "from account number " + fromAccountNumber + " to account number " +
                                        toAccountNumber + " was successful");
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        return isTransferred;
    }
}
