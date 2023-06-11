package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.App;
import main.ConsoleColors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.App.loggedIn_Client;

public class Client_dashboard_Controller implements Initializable {
    @FXML
    private Label welcomeText, currentBalance;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeText.setText("Hello, " + loggedIn_Client.getUsername());
        currentBalance.setText("Current Balance: " + loggedIn_Client.getBalance());
    }

    public void transfer() throws IOException {
        long startTime = System.currentTimeMillis();
        new App().changeScene("client/transfer.fxml");
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Transfer Money Screen Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();

    }


    public void bills() throws IOException {
        long startTime = System.currentTimeMillis();
        new App().changeScene("client/client_bills.fxml");
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Pay Bills Screen Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();

    }

    public void TRANSACTION() throws IOException {
        long startTime = System.currentTimeMillis();
        new App().changeScene("client/AllTransactions.fxml");
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Transactions Screen Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();

    }


    public void Log_Out() throws IOException {
        long startTime = System.currentTimeMillis();
        new App().changeScene("login.fxml");
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Logout Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();
    }

}
