package org.backend.models;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private String accountNumber;
    private int customerId;
    private String transactionType;
    private double debitAmount;
    private double creditAmount;
    private int transactionStatus;
    private LocalDateTime transactionDate;

    public int getTransactionId() {
        return transactionId;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Transaction(int transactionId, String accountNumber, int customerId,
                       String transactionType, double debitAmount, double creditAmount,
                       int transactionStatus, LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.transactionType = transactionType;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.transactionStatus = transactionStatus;
        this.transactionDate = transactionDate;
    }

    //override toString() method
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountNumber='" + accountNumber + '\'' +
                ", customerId=" + customerId +
                ", transactionType='" + transactionType + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                ", transactionStatus=" + transactionStatus +
                ", transactionDate=" + transactionDate +
                '}';
    }

}
