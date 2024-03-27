package com.ronyoung.assignment3;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class Chequing {
    
    private final AccountData chequingBean;
    
    // Method created to use the chequingBean as a child of the AccountData method.
    public Chequing(BigDecimal balance, BigDecimal annualInterestRate, 
            AccountData chequingBean ) {
        
        this.chequingBean = chequingBean;
        
        chequingBean.setStartingBalance(balance);
        chequingBean.setCurrentBalance(balance);
        chequingBean.setAnnualInterestRate(annualInterestRate);
    }
    
    // Method to make take deposit amount and add it to the current balance of 
    // the chequingBean. 
    public void makeDeposit(BigDecimal depositAmount) {
        
        // Checks if the current balance is 0 and if the number of withdrawals 
        // and deposits for the month are at 0.
        if (chequingBean.getCurrentBalance().equals(convertToBigDecimal(0)) && 
                chequingBean.getNumberOfWithrawals() == 0 && 
                chequingBean.getNumberOfDeposits() == 0) {
            updateCurrentBalance();
        }
        
        // Increases the number of deposits for the month.
        chequingBean.setNumberOfDeposits(chequingBean.getNumberOfDeposits() + 1);
        
        // Increases the total amount deposited for the month.
        chequingBean.setTotalOfDeposits(chequingBean.getTotalOfDeposits().add(depositAmount));
        
        // Adds the deposit amount to the current balance.
        chequingBean.setCurrentBalance(chequingBean.getCurrentBalance().add(depositAmount));
        
        //Prints out the new balance.
        System.out.println("New balance is: $" + chequingBean.getCurrentBalance()
        + "\n");
        
        
    }
    
    // Method to take withdrawal amount and remove it from the current balance.
    public boolean makeWithdraw(BigDecimal withdrawAmount) {
            
        // Checks if the current balance is 0 and if the number of withdrawals 
        // and deposits for the month are at 0.
        if (chequingBean.getCurrentBalance().equals(convertToBigDecimal(0)) && 
                chequingBean.getNumberOfWithrawals() == 0 && 
                chequingBean.getNumberOfDeposits() == 0) {
            updateCurrentBalance();
        }
        // Checks if the withdrawal amount is greater than the current balance.
        // Returns false if it is.
        if (withdrawAmount.compareTo(chequingBean.getCurrentBalance()) > 0) {
            
            System.out.println("Insufficient funds. Current balance is " + 
                    chequingBean.getCurrentBalance() + ".\n");
            return false;
            
        }
        
        // Removes the withdrawal amount from the current balance of the 
        //chequingBean.
        else {
            
            // Increases the number of widrawals for the and increases the
            // total amount withdrawn for the month.
            chequingBean.setNumberOfWithrawals(chequingBean.getNumberOfWithrawals()+ 1);  
            chequingBean.setTotalOfWithdrawals(chequingBean.getTotalOfWithdrawals().add(withdrawAmount));

            // Adds a 50cservice charge and subtracts it from the current 
            // balance. 
            chequingBean.setMonthServiceCharge(chequingBean.getMonthServiceCharge().add(convertToBigDecimal(0.5)));
            chequingBean.setCurrentBalance(chequingBean.getCurrentBalance().subtract(convertToBigDecimal(0.5)));
                       
            // Subtracts the withdraw amount from the current abalance and 
            // prints a message stating the new balance and that a service 
            // charge was added.
            // Returns true that a withdrawal could be done.
            chequingBean.setCurrentBalance(chequingBean.getCurrentBalance().subtract(withdrawAmount));
            System.out.println("Withdrawal successful, new balance is: $" + 
                    chequingBean.getCurrentBalance() + ". A service fee of "
                            + "$0.50 was applied\n");
            
            return true;
        }
        
    }
    
    // Method to calculate the interest earned from the balance this month.
    public void calculateInterest() {
                   
        //MathContext set up to have the variables only show two decimal points.
        MathContext mc = new MathContext(2, RoundingMode.HALF_EVEN);

        // New variable to hold the annual interest rate divided by 12 to get 
        // the monthly one.
        BigDecimal monthlyInterestRate = 
                chequingBean.getAnnualInterestRate().divide(convertToBigDecimal(
                        12), mc);
                       
        // Calculates and sets the interest earned this month.
        chequingBean.setMonthInterestEarnings(monthlyInterestRate.multiply(
                chequingBean.getCurrentBalance(), mc));
        
        // Adds the intertest earnings to the current balance.
        chequingBean.setCurrentBalance(chequingBean.getCurrentBalance().add(
                chequingBean.getMonthInterestEarnings()));
        
        
    }
    
    // Method to create a monthly report, copy the current data of the month, 
    // then reset all the data for a new month.
    public AccountData doMonthlyReport() {
        
        // Checks if the current balance is 0 and if the number of withdrawals 
        // and deposits for the month are at 0.
        if (chequingBean.getCurrentBalance().equals(convertToBigDecimal(0)) && 
                chequingBean.getNumberOfWithrawals() == 0 && 
                chequingBean.getNumberOfDeposits() == 0) {
            updateCurrentBalance();
        }
        // Subtract a service charge of $5 from the current balance.
        chequingBean.setCurrentBalance(chequingBean.getCurrentBalance().subtract(convertToBigDecimal(5)));
        System.out.println("A $5 service charge has been applied to create "
                + "this report.");
        
        // Update the total of service charges this month.
        chequingBean.setMonthServiceCharge(convertToBigDecimal(5));
        
        // Calls the calculateInterest method.  
        calculateInterest();
        
        // Create deep copy of the savingsBean.
        AccountData monthlyReport = new AccountData(chequingBean);
        
        // Calls the monthlyReset method.
        monthlyReset();

        // Returns the deep copy.
        return monthlyReport;
        
        
        
    }
    
    // Method to set all variables to zero other than startingBalance which is 
    // set to the current balance.
    public void monthlyReset() {
        
        
        chequingBean.setStartingBalance(chequingBean.getCurrentBalance());
        chequingBean.setMonthInterestEarnings(convertToBigDecimal(0));
        chequingBean.setCurrentBalance(convertToBigDecimal(0));
        chequingBean.setMonthServiceCharge(convertToBigDecimal(0));
        chequingBean.setNumberOfDeposits(0);
        chequingBean.setNumberOfWithrawals(0);
        chequingBean.setTotalOfDeposits(convertToBigDecimal(0));
        chequingBean.setTotalOfWithdrawals(convertToBigDecimal(0));
        
    }
    
    // Method that takes a float and returns it as a BigDecimal.
    public BigDecimal convertToBigDecimal(double number) {
        
        var newBigDecimal = new BigDecimal(number);
        
        return newBigDecimal;
    }
    
    // Method to update the current balance to match the starting balance.
    public void updateCurrentBalance() {
        chequingBean.setCurrentBalance(chequingBean.getStartingBalance());
    }
    
            
}
