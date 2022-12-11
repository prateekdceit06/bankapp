package org.backend.staticdata;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.json.JSONObject;

public class Stocks {

    String[] tickerList;
    HashMap tickerToPriceMap;
    Logger logger = Logger.getLogger(Stocks.class.getName());

    public Stocks() {
        tickerList = new String[]{"AAPL", "MSFT", "AMZN", "GOOG", "FB", "TSLA", "BRK.B", "JPM", "JNJ", "V", "PG", "UNH", "HD", "MA", "DIS", "VZ", "NVDA", "PYPL", "ADBE", "CMCSA", "NFLX", "CRM", "INTC", "T", "PEP", "BAC", "CSCO", "KO", "ABT", "NKE", "XOM", "WMT", "TMO", "MRK", "PFE", "ABBV", "ACN", "AVGO", "COST", "CVX", "DHR", "DOW", "DUK", "MDT", "MCD", "NEE"};
        tickerToPriceMap = new HashMap();
        for (String ticker : tickerList) {
            tickerToPriceMap.put(ticker, 0.0);
        }
    }

    public static void main(String[] args) throws IOException {

        Stocks stocks = new Stocks();
        System.out.println(stocks.getAllCurrentPrices());
        // print ticketToPriceMap here
        for (String ticker : stocks.tickerList) {
            System.out.println(ticker + ": " + stocks.tickerToPriceMap.get(ticker));
        }
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

    public Object getStock(String ticker) throws IOException {
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

        return jsonObject.getJSONObject("quoteSummary").getJSONArray("result").getJSONObject(0).getJSONObject("financialData").getJSONObject("currentPrice").get("raw");
    }

    public static class HelperStockFunctions {
        public boolean pushToSQL(String ticker, double price, Timestamp timestamp) {
            return true;
        }
    }
}
