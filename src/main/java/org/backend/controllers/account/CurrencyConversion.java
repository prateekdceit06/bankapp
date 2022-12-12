package org.backend.controllers.account;

import org.backend.staticdata.Data;

public class CurrencyConversion {
    public double convertCurrency(double amount, String fromCurrency) {
        double convertedAmount = 0;
        if (fromCurrency.equals(Data.Currencies.INR.toString()) ) {
            convertedAmount = amount * 0.012;
        } else if (fromCurrency.equals(Data.Currencies.EUR.toString()) ) {
            convertedAmount = amount * 1.05;
        } else if (fromCurrency.equals(Data.Currencies.USD.toString())){
            convertedAmount = amount;
        }
        return convertedAmount;
    }
}
