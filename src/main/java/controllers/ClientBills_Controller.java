package controllers;

import classes.Bill;
import classes.Client;
import classes.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import main.App;
import main.ConsoleColors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.App.clientsList;
import static main.App.loggedIn_Client;

public class ClientBills_Controller implements Initializable {
    @FXML
    private ComboBox billsComboBox;
    @FXML
    private Label amount, date, label_1, label_2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddToDropDown();
    }

    public void back() throws IOException {
        long startTime = System.currentTimeMillis();
        new App().changeScene("client/client_dashboard.fxml");
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Back to dashboard time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();
    }

    public void AddToDropDown() {
        for (int i = 0; i < loggedIn_Client.getBills().size(); i++) {
            billsComboBox.getItems().add(loggedIn_Client.getBills().get(i).getName());
        }
    }

    private Bill findBill(String billName) {
        for (int i = 0; i < loggedIn_Client.getBills().size(); i++) {
            if (billName.equals(loggedIn_Client.getBills().get(i).getName())) {
                return loggedIn_Client.getBills().get(i);
            }
        }
        return null;
    }

    public void showBillDetails() {
        if (!billsComboBox.getSelectionModel().isEmpty()) {
            Bill bill = findBill(billsComboBox.getSelectionModel().getSelectedItem().toString());
            if (bill == null) {
                return;
            }
            amount.setText(String.valueOf(bill.getAmount()));
            date.setText(String.valueOf(bill.getDueDate()));
        }
    }


    public void payBill() {
        long startTime = System.currentTimeMillis();
        resetLabels();
        Client currentClient = loggedIn_Client;

        if (billsComboBox.getSelectionModel().isEmpty() || amount.getText().trim().isEmpty() || amount.getText().equals("-")) {
            label_1.setTextFill(Color.color(1, 0, 0));
            label_2.setTextFill(Color.color(1, 0, 0));

            if (billsComboBox.getSelectionModel().isEmpty() && (amount.getText().isEmpty() || amount.getText().equals("-"))) {
                label_1.setText("Choose a bill");
            } else if (billsComboBox.getSelectionModel().isEmpty()) {
                label_1.setText("Choose a bill");
            } else if (amount.getText().isEmpty()) {
                label_1.setText("Enter an amount of money");
            }
            return;
        }


        double selectedAmount = Double.parseDouble(amount.getText());
        Bill bill = findBill(billsComboBox.getValue().toString());
        if (bill == null) {
            return;
        }
        if (billsComboBox == null) {
            return;
        }
        if (loggedIn_Client.payBill(bill) > -1) {
            label_1.setTextFill(Color.web("#d5f7e6"));
            amount.setText("-");
            date.setText("-");
            billsComboBox.getItems().remove(billsComboBox.getValue());
            label_1.setText("Payment Successful");

            currentClient.AddTransaction(new Transaction("Bill Successfully payed ", selectedAmount * -1));
        } else {
            label_1.setText("Insufficient Balance");
        }

        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Pay a Bill Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();
    }

    private void resetLabels() {
        label_1.setText("");
        label_2.setText("");
    }


}
