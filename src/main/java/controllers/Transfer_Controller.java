package controllers;

import classes.Client;
import classes.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static main.App.clientsList;
import static main.App.loggedIn_Client;

public class Transfer_Controller implements Initializable {
    @FXML
    private ComboBox clientsComboBox;
    @FXML
    private Label availableBalance, label_1, label_2, label_3, label_4;
    @FXML
    private TextField amount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddToDropDown();

        Client currentClient = null;
        for (Client client : clientsList) {
            if (client == loggedIn_Client) {
                currentClient = client;
            }
        }
        availableBalance.setText("Available Balance: " + currentClient.getBalance());
        label_1.setText("");
        label_2.setText("");
        label_3.setText("");
        label_4.setText("");

        amount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amount.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void AddToDropDown() {
        for (Client client : clientsList) {
            if (client != loggedIn_Client) {
                clientsComboBox.getItems().add(client.getUsername());
            }
        }
    }

    private Client findClient(String username) {
        for (Client currentClient : clientsList) {
            if (currentClient.getUsername().equals(username)) {
                return currentClient;
            }
        }
        return null;
    }

    public void transfer() {
        Client currentClient = loggedIn_Client;
        resetLabels();

        if (clientsComboBox.getSelectionModel().isEmpty() || amount.getText() == null) {
            label_1.setTextFill(Color.color(1, 0, 0));
            label_2.setTextFill(Color.color(1, 0, 0));

            if (clientsComboBox.getSelectionModel().isEmpty() && amount.getText().isEmpty()) {
                label_1.setText("Choose a client");
                label_2.setText("Enter an amount of money");
            } else if (clientsComboBox.getSelectionModel().isEmpty()) {
                label_1.setText("Choose a client");
            } else if (amount.getText().isEmpty()) {
                label_1.setText("Enter an amount of money");
            }
            return;
        }

        Client transferTo_Client = findClient(clientsComboBox.getValue().toString());
        double selectedAmount = Double.parseDouble(amount.getText());
        if (currentClient.transfer(transferTo_Client, selectedAmount) > -1) {
            Transaction newTransaction = new Transaction(currentClient, transferTo_Client, Double.parseDouble(amount.getText()));
            currentClient.addToTransactions(newTransaction);
            transferTo_Client.addToTransactions(newTransaction);

            availableBalance.setText("Available Balance: " + currentClient.getBalance());
            label_1.setText("Amount of");
            label_2.setText(amount.getText());
            label_3.setText("was sent successfully to");
            label_4.setText(clientsComboBox.getValue().toString());

            amount.clear();
        } else {
            label_1.setTextFill(Color.color(1, 0, 0));
            label_1.setText("Invalid Amount");
        }
    }

    private void resetLabels() {
        label_1.setText("");
        label_2.setText("");
        label_3.setText("");
        label_4.setText("");
    }


}
