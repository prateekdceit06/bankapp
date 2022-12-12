package org.backend.controllers.stock;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.controllers.user.GetToken;
import org.backend.models.Stock;
import org.backend.models.User;
import org.backend.staticdata.ConvertDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UpdateStock {
    public boolean updateStock(User loggedInUser, Stock stock) {
        boolean success = false;
        Connect c = new Connect();
        Connection connection = c.createConnection();
        GetToken getToken = new GetToken();
        String token = getToken.getToken(loggedInUser);
        if (connection != null) {
            try {
                if (loggedInUser.getIsAdmin() == 1 && loggedInUser.getToken().equals(token)) {
                    //statement to update user
                    String query = "UPDATE stock SET  current_price=?, tradable=?, " +
                            "price_update_date=? WHERE stock_id=?;";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setDouble(1, stock.getCurrentPrice());
                    pstmt.setInt(2, stock.getTradable());
                    String ts = ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis()));
                    pstmt.setString(3, ts);
                    pstmt.setInt(4, stock.getStockId());
                    pstmt.executeUpdate();
                    pstmt.close();
                    AddToAllEvents addToAllEvents = new AddToAllEvents();
                    addToAllEvents.addToAllEvents(connection, "Stock : " + stock.getStockId() +
                            " has been updated to " + stock);
                    success = true;
                }
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
