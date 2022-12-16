package org.backend.controllers.manager;

import org.backend.Connect;
import org.backend.allevents.AddToAllEvents;
import org.backend.staticdata.ConvertDate;
import org.backend.staticdata.Data;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Update the stocks using an openly accessibly API
 */
public class UpdateStocks {
    String[] tickerList = new String[]{"AAPL", "MSFT", "AMZN", "GOOG", "TSLA", "JPM", "V",
            "PG", "UNH", "HD", "MA", "DIS", "VZ", "NVDA", "PYPL", "ADBE", "CMCSA", "NFLX",
            "CRM", "INTC", "T", "BAC", "CSCO", "KO", "ABT", "XOM", "WMT", "TMO", "PFE", "ABBV",
            "ACN", "AVGO", "COST", "CVX", "DHR", "DOW", "DUK", "MCD", "NEE"};

    HashMap<String, String> tickerMap = new HashMap<String, String>() {
        {
            put("AAPL", "Apple Inc.");
            put("MSFT", "Microsoft Corporation");
            put("AMZN", "Amazon.com, Inc.");
            put("GOOG", "Alphabet Inc.");
            put("TSLA", "Tesla, Inc.");
            put("JPM", "JPMorgan Chase & Co.");
            put("V", "Visa Inc.");
            put("PG", "Procter & Gamble Company");
            put("UNH", "UnitedHealth Group Incorporated");
            put("HD", "The Home Depot, Inc.");
            put("MA", "Mastercard Incorporated");
            put("DIS", "The Walt Disney Company");
            put("VZ", "Verizon Communications Inc.");
            put("NVDA", "NVIDIA Corporation");
            put("PYPL", "PayPal Holdings, Inc.");
            put("ADBE", "Adobe Inc.");
            put("CMCSA", "Comcast Corporation");
            put("NFLX", "Netflix, Inc.");
            put("CRM", "Salesforce.com Inc");
            put("INTC", "Intel Corporation");
            put("T", "AT&T Inc.");
            put("BAC", "Bank of America Corporation");
            put("CSCO", "Cisco Systems, Inc.");
            put("KO", "The Coca-Cola Company");
            put("ABT", "Abbott Laboratories");
            put("XOM", "Exxon Mobil Corporation");
            put("WMT", "Walmart Inc.");
            put("TMO", "Thermo Fisher Scientific Inc.");
            put("PFE", "Pfizer Inc.");
            put("ABBV", "AbbVie Inc.");
            put("ACN", "Accenture plc");
            put("AVGO", "Broadcom Inc.");
            put("COST", "Costco Wholesale Corporation");
            put("CVX", "Chevron Corporation");
            put("DHR", "Danaher Corporation");
            put("DOW", "Dow Inc.");
            put("DUK", "Duke Energy Corporation");
            put("MCD", "McDonalds Corporation");
            put("NEE", "NextEra Energy, Inc.");
        }
    };
    HashMap<String, Double> tickerToPriceMap;
    Logger logger = Logger.getLogger(UpdateStocks.class.getName());

    public UpdateStocks() {
        tickerToPriceMap = new HashMap();
        for (String ticker : tickerList) {
            tickerToPriceMap.put(ticker, 0.0);
        }

        for (String ticker : tickerList) {
            try {
                tickerToPriceMap.put(ticker, getStock(ticker));
            } catch (IOException e) {
                logger.info("Error in getting stock price for ticker: " + ticker);
            }
        }
    }

    public boolean updateStocks() {
        boolean success = false;
        Connect connect = new Connect();
        try (Connection connection = connect.createConnection()) {
            HelperStockFunctions helperStockFunctions = new HelperStockFunctions();
            Statement stmt = connection.createStatement();
            stmt.setQueryTimeout(60);  // set timeout to 60 sec.
            helperStockFunctions.clearTable(stmt);
            success = helperStockFunctions.addAll(stmt);
            stmt.close();
            AddToAllEvents addToAllEvents = new AddToAllEvents();
            addToAllEvents.addToAllEvents(connection,"Stocks prices updated(online).");
        } catch (SQLException | IOException e) {
            logger.log(Level.SEVERE, "Error in initializing stocks", e);
        }
        return success;
    }


    private double getPrice(String ticker) {
        Double price = tickerToPriceMap.get(ticker);
        return price;
    }

    private double getStock(String ticker) throws IOException {
        String API_URL = "https://query1.finance.yahoo.com/v11/finance/quoteSummary/" + ticker + "?modules=financialData";
//        logger.info("API_URL: " + API_URL);

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
        double prefinal = raw.toString().equals("null") ? 0.00 : Double.parseDouble(raw.toString());
        return Double.parseDouble(Data.df.format(prefinal));
    }

    private class HelperStockFunctions {

        private boolean addAll(Statement stmt) throws SQLException, IOException {
            UpdateStocks updateStocks = new UpdateStocks();
            try {
                int counter = 0;
                String sql = "";
                for (String ticker : updateStocks.tickerList) {
                    counter++;
                    sql = "INSERT INTO stock (stock_id, stock_name, current_price, tradable, ticker, price_update_date) VALUES " +
                            "(" + counter + ",'" + tickerMap.get(ticker) + "', " + updateStocks.getPrice(ticker) + ", 1, '" + ticker + "', '" +
                            ConvertDate.convertDateToString(new Timestamp(System.currentTimeMillis())) + "')";
//                    logger.log(Level.INFO, sql);
                    stmt.executeUpdate(sql);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }


        private boolean clearTable(Statement stmt) throws SQLException {
            try {
                String sql = "DELETE FROM stock";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}


//    public void updateStocks() throws SQLException, IOException {
//        Connect connect = new Connect();
//        Connection connection = connect.createConnection();
//        Logger logger = Logger.getLogger(Stocks.class.getName());
//        HelperStockFunctions helperStockFunctions = new HelperStockFunctions();
//        try{
//            Statement stmt = connection.createStatement();
//            stmt.setQueryTimeout(30);  // set timeout to 30 sec.
//            helperStockFunctions.updateAll(stmt, logger);
//            stmt.close();
//        } catch (SQLException | IOException e) {
//            logger.log(Level.SEVERE, "Error in initializing stocks", e);
//        } finally {
//            if(connection!=null)
//                connection.close();
//        }
//    }

//        private boolean pushStockToSQL(Statement stmt, String ticker, double price, Timestamp timestamp) throws SQLException {
//            try {
//                String sql = "INSERT INTO stock (stock_name, current_price, tradable, " +
//                        "ticker, price_update_date) VALUES ('" + ticker + "', " + price +
//                        ", 0, '" + ticker + "', '" + ConvertDate.convertDateToString(timestamp) + "')";
//                stmt.executeUpdate(sql);
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }

//        private boolean updateAll(Statement stmt, Logger logger) throws SQLException, IOException {
//            Stocks stocks = new Stocks();
//            try {
//                int counter = 0;
//                for (String ticker : stocks.tickerList) {
//                    counter++;
//                    double price = stocks.getPrice(ticker);
//                    logger.info("Updating stock: " + ticker + " with current price: " + price);
//                    pushStockToSQL(stmt, ticker, price , new Timestamp(System.currentTimeMillis()));
//                }
//                return true;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        }


//        public boolean deleteThisStock(Statement stmt, String ticker) throws SQLException {
//            try {
//                String sql = "DELETE FROM stock WHERE ticker = '" + ticker + "'";
//                stmt.executeUpdate(sql);
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//        public boolean deleteThisStock(Statement stmt, int stock_id) throws SQLException {
//            try {
//                String sql = "DELETE FROM stock WHERE stock_id = '" + stock_id + "'";
//                stmt.executeUpdate(sql);
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//        public boolean makeTradeable(Statement stmt, String ticker) throws SQLException {
//            try {
//                String sql = "UPDATE stock SET tradeable = 1 WHERE ticker = '" + ticker + "'";
//                stmt.executeUpdate(sql);
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//        public boolean makeTradeable(Statement stmt, int stock_id) throws SQLException {
//            try {
//                String sql = "UPDATE stock SET tradeable = 1 WHERE stock_id = '" + stock_id + "'";
//                stmt.executeUpdate(sql);
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//        public boolean makeUntradeable(Statement stmt, String ticker) throws SQLException {
//            try {
//                String sql = "UPDATE stock SET tradeable = 0 WHERE ticker = '" + ticker + "'";
//                stmt.executeUpdate(sql);
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//        public boolean makeUntradeable(Statement stmt, int stock_id) throws SQLException {
//            try {
//                String sql = "UPDATE stock SET tradeable = 0 WHERE stock_id = '" + stock_id + "'";
//                stmt.executeUpdate(sql);
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//        public boolean updatePriceAndTimestamp(String ticker, int price, Timestamp timestamp) throws SQLException {
//            try {
//                Connect c = new Connect();
//                Connection conn = c.createConnection();
//                Statement stmt = conn.createStatement();
//                stmt.setQueryTimeout(30);  // set timeout to 30 sec.
//                Logger logger = Logger.getLogger(Stocks.class.getName());
//                String sql = "UPDATE stock SET price = " + price + ", price_update_date = '" + timestamp + "' WHERE ticker = '" + ticker + "'";
//                stmt.executeUpdate(sql);
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }


//    public static void main(String[] args) throws IOException {
//        Stocks stocks = new Stocks();
//        try {
//            stocks.initialize();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

