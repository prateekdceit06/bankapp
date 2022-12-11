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

    public Stocks()
    {
        tickerList = new String[]{"AAPL", "MSFT", "AMZN", "GOOG", "FB", "TSLA", "BRK.B", "JPM", "JNJ", "V", "PG", "UNH", "HD", "MA", "DIS", "VZ", "NVDA", "PYPL", "ADBE", "CMCSA", "NFLX", "CRM", "INTC", "T", "PEP", "BAC", "CSCO", "KO", "ABT", "NKE", "XOM", "WMT", "TMO", "MRK", "PFE", "ABBV", "ACN", "AVGO", "COST", "CVX", "DHR", "DOW", "DUK", "MDT", "MCD", "NEE"};
    }

    Logger logger = Logger.getLogger(Stocks.class.getName());
    String API_KEY = "9f8jsEpI6stdnhDDg9oWH11cEsN05tsA";

    public static void main(String[] args) {
        Stocks stocks = new Stocks();
        try {
            System.out.println(stocks.getStock("IBM"));
        } catch (IOException e) {
            System.out.println("Returned 404: Data not found");
            stocks.logger.info(e.toString());
        }
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

        JSONObject quoteSummary = jsonObject.getJSONObject("quoteSummary");
        JSONArray result = quoteSummary.getJSONArray("result");
        JSONObject financialDataArray = result.getJSONObject(0);
        JSONObject financialData = financialDataArray.getJSONObject("financialData");
        JSONObject currentPrice = financialData.getJSONObject("currentPrice");
        Object raw = currentPrice.get("raw");
        return raw;
    }

    public static class HelperStockFunctions {
        public String getTodayTimeStamp(Logger logger) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String today = timestamp.toString().split(" ")[0];
            logger.info("Today: " + today);
            return today;
        }
    }
}
