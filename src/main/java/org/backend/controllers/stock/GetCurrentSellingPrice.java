package org.backend.controllers.stock;

import org.backend.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCurrentSellingPrice {
    public double getCurrentSellingPrice(int stockId) {
        double currentSellingPrice = 0.0;
        Connect c = new Connect();
        Connection connection = c.createConnection();
        if(connection!=null){
            try{
                PreparedStatement ps = connection.prepareStatement("SELECT current_price FROM stock WHERE stock_id = ?");
                ps.setInt(1, stockId);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    currentSellingPrice = rs.getDouble("current_price");
                }
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
        return currentSellingPrice;
    }
}
