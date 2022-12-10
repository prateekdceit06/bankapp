package org.backend.models;

public class ApprovedLoan {
    private int loanId;
    private String accountNumber;

    private int customerId;
    double sanctionedAmount;
    private double loanInterestRate;
    private String loanInterestUnit;
    private double remainingLoanAmount;
    private double monthlyPaymentAmount;
    private int loanDurationInMonths;
    private Loan loan;

    //constructor
    public ApprovedLoan(int loan_id, int customerId, String accountNumber, double sanctionedAmount, double loanInterestRate,
                        String loanInterestUnit, double remainingLoanAmount, int loanDurationInMonths,
                        double monthlyPaymentAmount) {
        this.loanId = loan_id;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.sanctionedAmount = sanctionedAmount;
        this.loanInterestRate = loanInterestRate;
        this.loanInterestUnit = loanInterestUnit;
        this.remainingLoanAmount = remainingLoanAmount;
        this.monthlyPaymentAmount = monthlyPaymentAmount;
        this.loanDurationInMonths = loanDurationInMonths;
        this.loan = null;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getSanctionedAmount() {
        return sanctionedAmount;
    }

    public void setSanctionedAmount(double sanctionedAmount) {
        this.sanctionedAmount = sanctionedAmount;
    }

    public double getLoanInterestRate() {
        return loanInterestRate;
    }

    public void setLoanInterestRate(double loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    public String getLoanInterestUnit() {
        return loanInterestUnit;
    }

    public void setLoanInterestUnit(String loanInterestUnit) {
        this.loanInterestUnit = loanInterestUnit;
    }

    public double getRemainingLoanAmount() {
        return remainingLoanAmount;
    }

    public void setRemainingLoanAmount(double remainingLoanAmount) {
        this.remainingLoanAmount = remainingLoanAmount;
    }

    public double getMonthlyPaymentAmount() {
        return monthlyPaymentAmount;
    }

    public void setMonthlyPaymentAmount(double monthlyPaymentAmount) {
        this.monthlyPaymentAmount = monthlyPaymentAmount;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public int getLoanDurationInMonths() {
        return loanDurationInMonths;
    }


    public void setLoanDurationInMonths(int loanDurationInMonths) {
        this.loanDurationInMonths = loanDurationInMonths;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    //toString
    @Override
    public String toString() {
        return "ApprovedLoan{" + "loanId=" + loanId + ", " +
                "customerId=" + customerId + ", " +
                "accountNumber=" + accountNumber +
                ", sanctionedAmount=" + sanctionedAmount +
                ", loanInterestRate=" + loanInterestRate +
                ", loanInterestUnit=" + loanInterestUnit +
                ", remainingLoanAmount=" + remainingLoanAmount +
                ", monthlyPaymentAmount=" + monthlyPaymentAmount +
                ", loanDurationInMonths=" + loanDurationInMonths +
                ", loan=" + loan + '}';
    }

}
