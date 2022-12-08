package org.backend.models;

import org.backend.controllers.loan.ApplyForLoan;
import org.backend.controllers.loan.ApproveRejectLoan;

import java.time.LocalDateTime;

public class Loan {
    private int loanId;
    private int customerId;
    private String loanStatus;
    private double loanAmount;
    private double collateralValue;

    private LocalDateTime loanApplicationDate;
    private LocalDateTime loanDecisionDate;

    public int getLoanId() {
        return loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getCollateralValue() {
        return collateralValue;
    }

    public void setCollateralValue(double collateralValue) {
        this.collateralValue = collateralValue;
    }


    public LocalDateTime getLoanDecisionDate() {
        return loanDecisionDate;
    }

    public void setLoanDecisionDate(LocalDateTime loanDecisionDate) {
        this.loanDecisionDate = loanDecisionDate;
    }

    //constructor
    public Loan(){}
    //constructor
    public Loan(int loanId, int customerId, String loanStatus,
                double loanAmount, double collateralValue,
                LocalDateTime loanApplicationDate, LocalDateTime loanDecisionDate) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.loanStatus = loanStatus;
        this.loanAmount = loanAmount;
        this.collateralValue = collateralValue;
        this.loanApplicationDate = loanApplicationDate;
        this.loanDecisionDate = loanDecisionDate;
    }

    //override toString method
    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", customerId=" + customerId +
                ", loanStatus='" + loanStatus + '\'' +
                ", loanAmount=" + loanAmount +
                ", collateralValue=" + collateralValue +
                ", loanApplicationDate=" + loanApplicationDate +
                ", loanDecisionDate=" + loanDecisionDate +
                '}';
    }

    public boolean approveLoan(int loanId, int customerId, String accountNo, double sanctionedAmount,
                                     String loanStatus, double loanInterest,
                                     String loanInterestUnit, int loanDurationInMonths, User loggedInUser) {
        boolean success = false;
        ApproveRejectLoan applyRejectLoan = new ApproveRejectLoan();
        success= applyRejectLoan.approveLoan(loanId, customerId, accountNo, sanctionedAmount, loanStatus,
                loanInterest, loanInterestUnit, loanDurationInMonths, loggedInUser);
        return success;
    }

    public boolean rejectLoan(int loanId, String loanStatus, User loggedInUser) {
        boolean success = false;
        ApproveRejectLoan applyRejectLoan = new ApproveRejectLoan();
        success= applyRejectLoan.rejectLoan(loanId, loanStatus, loggedInUser);
        return success;
    }
}
