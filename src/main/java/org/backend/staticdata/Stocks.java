package org.backend.staticdata;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.json.JSONObject;

import javax.swing.plaf.nimbus.State;

public class Stocks {

    String[] tickerList;
    HashMap tickerToPriceMap;
    Logger logger = Logger.getLogger(Stocks.class.getName());

    public Stocks() throws IOException {
        tickerList = new String[]{"AAPL", "MSFT", "AMZN", "GOOG", "TSLA", "JPM", "JNJ", "V", "PG", "UNH", "HD", "MA", "DIS", "VZ", "NVDA", "PYPL", "ADBE", "CMCSA", "NFLX", "CRM", "INTC", "T", "PEP", "BAC", "CSCO", "KO", "ABT", "NKE", "XOM", "WMT", "TMO", "MRK", "PFE", "ABBV", "ACN", "AVGO", "COST", "CVX", "DHR", "DOW", "DUK", "MDT", "MCD", "NEE"};
        tickerToPriceMap = new HashMap();
        for (String ticker : tickerList) {
            tickerToPriceMap.put(ticker, 0.0);
        }
        getAllCurrentPrices();
    }

    public static void main(String[] args) throws IOException {
        Stocks stocks = new Stocks();
        HelperStockFunctions helperStockFunctions = new HelperStockFunctions();
        helperStockFunctions.runUpdates(stocks);
    }

    public double getPrice(String ticker) {
        Double price = Double.valueOf((Integer) tickerToPriceMap.get(ticker));
        return price;
    }

    public int getAllCurrentPrices() throws IOException {
        int counter = 0;
        for (String ticker : tickerList) {
            try {
                tickerToPriceMap.put(ticker, getStock(ticker));
                counter++;
            } catch (Exception e) {
                logger.info("Error in getting stock price for ticker: " + ticker);
            }
        }

        return counter;
    }

    public int getStock(String ticker) throws IOException {
        String API_URL = "https://query1.finance.yahoo.com/v11/finance/quoteSummary/" + ticker + "?modules=financialData";
        logger.info("API_URL: " + API_URL);

        // Use Java's built-in URL class to create a connection and make a GET request
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response and build the JSON object from the response string
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject jsonObject = new JSONObject(response.toString());

        Object raw = jsonObject.getJSONObject("quoteSummary").getJSONArray("result").getJSONObject(0).getJSONObject("financialData").getJSONObject("currentPrice").get("raw");
        Double prefinal = raw.toString().equals("null") ? 0.0 : Double.parseDouble(raw.toString());
        int finalPrice = (int) Math.round(prefinal);
        return finalPrice;
    }

    public static class HelperStockFunctions {
        public boolean pushStockToSQL(Statement stmt, int stock_id, String ticker, double price, Timestamp timestamp) throws SQLException {
            try {
                String sql = "INSERT INTO stocks (stock_id, stock_name, price, tradeable, ticker, price_update_date) VALUES ('" + stock_id + "', '" + ticker + "', " + price + ", 0, '" + ticker + "', '" + timestamp + "')";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean updateAll(Statement stmt, Stocks stocks) throws SQLException {
            try {
                int counter = 0;
                stocks.getAllCurrentPrices();
                for (String ticker : stocks.tickerList) {
                    counter++;
                    pushStockToSQL(stmt, 0, ticker, (double) stocks.tickerToPriceMap.get(ticker), new Timestamp(System.currentTimeMillis()));
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean clearTable(Statement stmt) throws SQLException {
            try {
                String sql = "DELETE FROM stocks";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean deleteThisStock(Statement stmt, String ticker) throws SQLException {
            try {
                String sql = "DELETE FROM stocks WHERE ticker = '" + ticker + "'";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean deleteThisStock(Statement stmt, int stock_id) throws SQLException {
            try {
                String sql = "DELETE FROM stocks WHERE stock_id = '" + stock_id + "'";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean makeTradeable(Statement stmt, String ticker) throws SQLException {
            try {
                String sql = "UPDATE stocks SET tradeable = 1 WHERE ticker = '" + ticker + "'";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean makeTradeable(Statement stmt, int stock_id) throws SQLException {
            try {
                String sql = "UPDATE stocks SET tradeable = 1 WHERE stock_id = '" + stock_id + "'";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean makeUntradeable(Statement stmt, String ticker) throws SQLException {
            try {
                String sql = "UPDATE stocks SET tradeable = 0 WHERE ticker = '" + ticker + "'";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean makeUntradeable(Statement stmt, int stock_id) throws SQLException {
            try {
                String sql = "UPDATE stocks SET tradeable = 0 WHERE stock_id = '" + stock_id + "'";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean updatePriceAndTimestamp(Statement stmt, String ticker, int price, Timestamp timestamp) throws SQLException {
            try {
                String sql = "UPDATE stocks SET price = " + price + ", price_update_date = '" + timestamp + "' WHERE ticker = '" + ticker + "'";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean runUpdates(Stocks stocks) {
            try {
                String sql = "";
                for (String ticker : stocks.tickerList) {
                    // write a SQL query to add the stock to the database
                    sql = "INSERT INTO stocks (stock_id, stock_name, price, tradeable, ticker, price_update_date) VALUES ('" + 0 + "', '" + ticker + "', " + (double) stocks.tickerToPriceMap.get(ticker) + ", 0, '" + ticker + "', '" + new Timestamp(System.currentTimeMillis()) + "')";
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
