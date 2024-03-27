package com.ronyoung.assignment3;

import java.math.BigDecimal;

public class AccountData {

    // Variables that will be used throughout the program.
    private BigDecimal startingBalance = new BigDecimal(0);
    private BigDecimal currentBalance = new BigDecimal(0);
    private BigDecimal totalOfDeposits = new BigDecimal(0);
    private int numberOfDeposits = 0;
    private BigDecimal totalOfWithdrawals = new BigDecimal(0);
    private int numberOfWithrawals = 0;
    private BigDecimal annualInterestRate = new BigDecimal(0);
    private BigDecimal monthInterestEarnings = new BigDecimal(0);
    private BigDecimal monthServiceCharge = new BigDecimal(0);

    
    // Method created for other methods to call.
    public AccountData() {
    }

    // The constructor.
    public AccountData(BigDecimal startingBalance, BigDecimal currentBalance, BigDecimal totalOfDeposits, int numberOfDeposits, BigDecimal totalOfWithdrawals, int numberOfWithrawals, BigDecimal annualInterestRate, BigDecimal monthInterestEarnings, BigDecimal monthServiceCharge) {
        this.startingBalance = startingBalance;
        this.currentBalance = currentBalance;
        this.totalOfDeposits = totalOfDeposits;
        this.numberOfDeposits = numberOfDeposits;
        this.totalOfWithdrawals = totalOfWithdrawals;
        this.numberOfWithrawals = numberOfWithrawals;
        this.annualInterestRate = annualInterestRate;
        this.monthInterestEarnings = monthInterestEarnings;
        this.monthServiceCharge = monthServiceCharge;
    }
    
    // Copy constructor.
    public AccountData(AccountData original) {
        this.startingBalance = new BigDecimal(original.startingBalance.toString());
        this.currentBalance = new BigDecimal(original.currentBalance.toString());
        this.totalOfDeposits = new BigDecimal(original.totalOfDeposits.toString());
        this.numberOfDeposits = original.numberOfDeposits;
        this.totalOfWithdrawals = new BigDecimal(original.totalOfWithdrawals.toString());
        this.numberOfWithrawals = original.numberOfWithrawals;
        this.annualInterestRate = new BigDecimal(original.annualInterestRate.toString());
        this.monthInterestEarnings = new BigDecimal(original.monthInterestEarnings.toString());
        this.monthServiceCharge = new BigDecimal(original.monthServiceCharge.toString());
    }
    
    
    
    // Getters and setters.
    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getTotalOfDeposits() {
        return totalOfDeposits;
    }

    public void setTotalOfDeposits(BigDecimal totalOfDeposits) {
        this.totalOfDeposits = totalOfDeposits;
    }

    public int getNumberOfDeposits() {
        return numberOfDeposits;
    }

    public void setNumberOfDeposits(int numberOfDeposits) {
        this.numberOfDeposits = numberOfDeposits;
    }

    public BigDecimal getTotalOfWithdrawals() {
        return totalOfWithdrawals;
    }

    public void setTotalOfWithdrawals(BigDecimal totalOfWithdrawals) {
        this.totalOfWithdrawals = totalOfWithdrawals;
    }

    public int getNumberOfWithrawals() {
        return numberOfWithrawals;
    }

    public void setNumberOfWithrawals(int numberOfWithrawals) {
        this.numberOfWithrawals = numberOfWithrawals;
    }

    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(BigDecimal annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public BigDecimal getMonthInterestEarnings() {
        return monthInterestEarnings;
    }

    public void setMonthInterestEarnings(BigDecimal monthInterestEarnings) {
        this.monthInterestEarnings = monthInterestEarnings;
    }

    public BigDecimal getMonthServiceCharge() {
        return monthServiceCharge;
    }

    public void setMonthServiceCharge(BigDecimal monthServiceCharge) {
        this.monthServiceCharge = monthServiceCharge;
    }

    // Override of toString.
    @Override
    public String toString() {
        return "Starting Balance this month: $" + startingBalance + 
                "\nCurrent Balance: $" + currentBalance + 
                "\nTotal of deposits this month: $" + totalOfDeposits + 
                "\nNumber of deposits this month: " + numberOfDeposits + 
                "\nTotal of withdrawals this month: $" + totalOfWithdrawals + 
                "\nNumber of withrawals this month: " + numberOfWithrawals + 
                "\nAnnual Interest Rate: " + annualInterestRate + "%" +
                "\nInterest earnings this month: $" + monthInterestEarnings + 
                "\nTotal of service charges this month: $" + monthServiceCharge
                + "\n";
    }
    
           
}
