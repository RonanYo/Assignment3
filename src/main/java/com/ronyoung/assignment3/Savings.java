package com.ronyoung.assignment3;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class Savings {
    
    
   // private AccountData deepCopy;
    private final AccountData savingsBean;
    
    // Method created to use the savingsBean as a child of the AccountData method.
    public Savings(BigDecimal balance, BigDecimal interestRate, AccountData savingsBean ) {
        
        this.savingsBean = savingsBean;
        
        // Declaration of values within the savingsBean.
        savingsBean.setStartingBalance(balance);
        savingsBean.setCurrentBalance(balance);
        savingsBean.setAnnualInterestRate(interestRate);
    }
    
    // Method to make take deposit amount and add it to the current balance of 
    // the savingsBean. 
    public void makeDeposit(BigDecimal depositAmount) {
        
        // Checks if the current balance is 0 and if the number of withdrawals 
        // and deposits for the month are at 0.
        if (savingsBean.getCurrentBalance().equals(convertToBigDecimal(0)) && 
                savingsBean.getNumberOfWithrawals() == 0 && 
                savingsBean.getNumberOfDeposits() == 0) {
            updateCurrentBalance();
        }
        
        // Increases the number of deposits for the month.
        savingsBean.setNumberOfDeposits(savingsBean.getNumberOfDeposits() + 1);
        
        // Increases the total amount deposited for the month.
        savingsBean.setTotalOfDeposits(savingsBean.getTotalOfDeposits().add(depositAmount));
        
        // Adds the deposit amount to the current balance.
        savingsBean.setCurrentBalance(savingsBean.getCurrentBalance().add(depositAmount));
        
        //Prints out the new balance.
        System.out.println("New balance is: $" + savingsBean.getCurrentBalance()
         + "\n");

        
    }
    
    // Method to take withdrawal amount and remove it from the current balance.
    public boolean makeWithdraw(BigDecimal withdrawAmount) {
        
        // Checks if the current balance is 0 and if the number of withdrawals 
        // and deposits for the month are at 0.
        if (savingsBean.getCurrentBalance().equals(convertToBigDecimal(0)) && 
                savingsBean.getNumberOfWithrawals() == 0 && 
                savingsBean.getNumberOfDeposits() == 0) {
            updateCurrentBalance();
        }


        // Checks if the withdrawal amount is greater than the current balance.
        // Returns false if it is.
        if (withdrawAmount.compareTo(savingsBean.getCurrentBalance()) > 0) {
            
            System.out.println("Insufficient funds. Current balance is " + 
                    savingsBean.getCurrentBalance() + ".\n");
            return false;
            
        }
        
        // Removes the withdrawal amount from the current balance of the 
        //savingsBean.
        else {
            
            // Increases the number of widrawals for the and increases the
            // total amount withdrawn for the month.
            savingsBean.setNumberOfWithrawals(savingsBean.getNumberOfWithrawals()+ 1);
            savingsBean.setTotalOfWithdrawals(savingsBean.getTotalOfWithdrawals().add(withdrawAmount));

            // Checks if the number of with drawals equals 4. Prints a limit 
            // reached message to warn the user.
            if (savingsBean.getNumberOfWithrawals() == 4) {
                System.out.println(" Max number of free withdrawals reached. "
                        + "A $1 service charge will be applied on all further "
                        + "withdrawals this month.");
            }
            
            // Checks if the number of withdrawals is greater than 4. 
            // Subtracts $1 from the current balance as a service charge and 
            // adds $1 to the monthly service charge. Then prints a message of 
            // going over the withdrawal limit.
            else if (savingsBean.getNumberOfWithrawals() > 4) {
                
                savingsBean.setCurrentBalance(savingsBean.getCurrentBalance().subtract(convertToBigDecimal(1)));
                savingsBean.setMonthServiceCharge(savingsBean.getMonthServiceCharge().add(convertToBigDecimal(1)));
                
                System.out.println("Max number of free withdrawals exceeded, "
                        + "a $1 service fee has been applied.");
            }
            
            // Updates current balance, subtracting the withdrawal amount. 
            // Prints a success message and the new balance.
            // Returns true that a withdrawal could be done.
            savingsBean.setCurrentBalance(savingsBean.getCurrentBalance().subtract(withdrawAmount));
            System.out.println("Withdrawal successful, new balance is: $" + 
                    savingsBean.getCurrentBalance() + "\n");
            
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
                savingsBean.getAnnualInterestRate().divide(convertToBigDecimal(12), mc);
        
        // Calculates and sets the interest earned this month.                
        savingsBean.setMonthInterestEarnings(monthlyInterestRate.multiply(savingsBean.getCurrentBalance(), mc));
        
        // Adds the intertest earnings to the current balance.
        savingsBean.setCurrentBalance(savingsBean.getCurrentBalance().add(savingsBean.getMonthInterestEarnings()));
                
    }
    
    // Method to create a monthly report, copy the current data of the month, 
    // then reset all the data for a new month.
    public AccountData doMonthlyReport() {
        
        // Checks if the current balance is 0 and if the number of withdrawals 
        // and deposits for the month are at 0.
        if (savingsBean.getCurrentBalance().equals(convertToBigDecimal(0)) && 
                savingsBean.getNumberOfWithrawals() == 0 && 
                savingsBean.getNumberOfDeposits() == 0) {
            updateCurrentBalance();
        }
        
        // Calls the calculateInterest method.        
        calculateInterest();
        
        // Create deep copy of the savingsBean.
        AccountData monthlyReport = new AccountData(savingsBean);
        
        // Calls the monthlyReset method.
        monthlyReset();
        
        // return copy of the savingsBean.
        return monthlyReport;
        
    }
    
    // Method to set all variables to zero other than startingBalance which is 
    // set to the current balance.
    public void monthlyReset() {
        
        savingsBean.setStartingBalance(savingsBean.getCurrentBalance());
        savingsBean.setMonthInterestEarnings(convertToBigDecimal(0));
        savingsBean.setCurrentBalance(convertToBigDecimal(0));
        savingsBean.setMonthServiceCharge(convertToBigDecimal(0));
        savingsBean.setNumberOfDeposits(0);
        savingsBean.setNumberOfWithrawals(0);
        savingsBean.setTotalOfDeposits(convertToBigDecimal(0));
        savingsBean.setTotalOfWithdrawals(convertToBigDecimal(0));
    }
    
    // Method that takes a float and returns it as a BigDecimal.
    public BigDecimal convertToBigDecimal(float number) {
        
        var newBigDecimal = new BigDecimal(number);
        
        return newBigDecimal;
    }
    
    // Method to update the current balance to match the starting balance.
    public void updateCurrentBalance() {
        savingsBean.setCurrentBalance(savingsBean.getStartingBalance());
    }
    
        
}
