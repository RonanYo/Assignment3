package com.ronyoung.assignment3;

import java.math.BigDecimal;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {
    
    private AccountData savingsBean;
    private AccountData chequingBean; 
    private Chequing chequing;
    private Savings savings;
    
    public void accountData() {
        
        savingsBean = new AccountData();
        chequingBean = new AccountData();
        
        chequing = new Chequing(new BigDecimal("100"), 
                new BigDecimal("0.05"),chequingBean);
        savings = new Savings(new BigDecimal("100"), 
                new BigDecimal("0.05"), savingsBean);
        
    }
    

    @Override
    public void start(Stage stage) {
        
        // Call the accountData method to initialize the account data.
        accountData();
        
        // Declare the type of window, and objects that will be in the window.
        HBox root = new HBox();
        Scene scene = new Scene(root, 400, 200);
        Button btnSavings = new Button("Savings");
        Button btnChequing = new Button("Chequing");
        Button btnExit = new Button("Exit");
        
        // Set the Exit button as the default cancel button.
        btnExit.setCancelButton(true);
        
        // When Savings is pressed the accountButton method is called with Savings.
        btnSavings.setOnAction(event ->{
            accountButton(stage, "Savings");
        });
        
        // When Chequing is pressed the accountButton method is called with Chequing.
        btnChequing.setOnAction(event ->{
            accountButton(stage, "Chequing");
        });
        
        // When Exit is pressed the exitButton method is called, closing the window..
        btnExit.setOnAction(event ->{
            exitButton();
        });
        
        // Sets all the roots for the window.
        root.getChildren().addAll(btnSavings, btnChequing, btnExit);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        
        // Opens up the stage.
        stage.setScene(scene);
        stage.setTitle("Bank Menu");
        stage.show();
        
    }
    
    private void accountButton(Stage accountStage, String accountType) {
                
        // Declare the type of window, and objects that will be in the window.
        HBox root = new HBox();
        Scene scene = new Scene(root, 400, 300);
        Button btnDeposit = new Button("Deposit");
        Button btnWithdrawal = new Button("Withdrawal");
        Button btnReport = new Button("Monthly Report");
        Button btnBack = new Button("Back");
        
        // Set the back button as the default cancel button.
        btnBack.setCancelButton(true);
        
        // When Deposit is selected the numberAmount method is called with Deposit.
        btnDeposit.setOnAction(event ->{
        
            numberAmount(accountStage, "Deposit", accountType);
       
        });
        
        // When Withdrawal is selected the numberAmount method is called with Withdrawal.
        btnWithdrawal.setOnAction(event ->{
        
            numberAmount(accountStage, "Withdrawal", accountType);
            
        });
        
        // When report is pressed the doMonthlyReport method of the Chequing or 
        //Savings classes is called.
        btnReport.setOnAction(event ->{
        
            if (accountType.equalsIgnoreCase("Savings")) {
                
                System.out.println(savings.doMonthlyReport());
                
            } else if (accountType.equals("Chequing")) {
                
                System.out.println(chequing.doMonthlyReport());

            }
            
        });
        
        // backButton method is called when Back is pressed.
        btnBack.setOnAction(event ->{
            
            backButton(accountStage);
        
        });
        
        // Sets all the roots for the window.
        root.getChildren().addAll(btnDeposit, btnWithdrawal, btnReport, 
                btnBack);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        
        // Opens up the stage.
        accountStage.setScene(scene);
        accountStage.setTitle(accountType + " Menu");
        accountStage.show();
        
    }
    
    // Method to go back to the previous page in the stage.
    private void backButton(Stage backStage) {
        System.out.println("Back Selected");
        start(backStage);
    }
    
    // Method to close the stage.
    private void exitButton() {
        System.out.println("Exit Selected");
        System.exit(0);
    }
    
    // Method for a stage to enter a money amount for deposit or withdrawal.
    private BigDecimal numberAmount(Stage amountStage, String depositOrWithdraw, String savingsOrChequing) {
        
        // Declare the type of window, and objects that will be in the window.
        VBox root = new VBox();
        Scene scene = new Scene(root, 400, 200);
        Button btnSubmit = new Button("Submit");
        Button btnCancel = new Button("Cancel");
        btnSubmit.setDefaultButton(true);
        Text text = new Text();       
        TextField field = new TextField();
        
        // Set the cancel button as the default cancel button.
        btnCancel.setCancelButton(true);
        
        // Set a prompt to be in the textField when it's not selected.
        field.setPromptText("Enter " + depositOrWithdraw + " amount");
        
        //Declaration of a BigDecimal array to be used below.
        BigDecimal[] returnValue = new BigDecimal[1];
                
        btnSubmit.setOnAction(event -> {            

            try {
                
            // Gets input from user in the TexTField then puts it in the returnValue.
            String input = field.getText();
            returnValue[0] = new BigDecimal(input);
            
            // Checks if this is depositing then if it's in the Savings or 
            //Chequing menu then makes a deposit into that account.
            if (depositOrWithdraw.equals("Deposit")){

                if (savingsOrChequing.equals("Savings")) {

                    savings.makeDeposit(returnValue[0]);

                } else if (savingsOrChequing.equals("Chequing")){

                   chequing.makeDeposit(returnValue[0]);

                }

            // Checks if this is withdrawinging then if it's in the Savings or 
            //Chequing menu then makes a withdrawal from that account.
            } else if (depositOrWithdraw.equals("Withdrawal")) {

                if (savingsOrChequing.equals("Savings")) {

                    savings.makeWithdraw(returnValue[0]);

                } else if (savingsOrChequing.equals("Chequing")){

                   chequing.makeWithdraw(returnValue[0]);

                }

            }

            accountButton(amountStage, savingsOrChequing);
            
            // Catches a NumberFormatException and displays text to retry with
            //a number.
            }catch (NumberFormatException e) {
                text.setText("Invalid Entry. Please enter a number.");
            }
            
        });
        
        
        // Calls the accountButton method when Cancel is pressed.
        btnCancel.setOnAction(event -> {
            accountButton(amountStage, savingsOrChequing);
    });
        
        // Sets all the roots for the window.
        root.getChildren().addAll(field, btnSubmit, btnCancel, text);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        
        // Opens up the stage.
        amountStage.setScene(scene);
        amountStage.setTitle(savingsOrChequing + " Menu");
        amountStage.show();
        
        // Returns the entered value.
        return returnValue[0];
    }
    
    // Gets the show started.
    public static void main(String[] args) {
        launch(args);
    }

}