package controllers;

import classes.Client;
import classes.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.App.clientsList;
import static main.App.loggedIn_Client;

public class Transfer_Controller implements Initializable {
    @FXML
    public ComboBox clientsComboBox;
    @FXML
    public Label availableBalance, label_1, label_2, label_3, label_4;
    @FXML
    public TextField amount;

    @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddToDropDown();


        Client currentClient = null;
        for (Client client : clientsList) {
            if (client == loggedIn_Client) {
                currentClient = client;
            }
        }
        if (currentClient == null) {
            return;
        }
        if (availableBalance == null) {
            return;
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

    public void back() throws IOException {
        new App().changeScene("client/client_dashboard.fxml");
    }

    public void AddToDropDown() {
        for (Client client : clientsList) {
            if (client != loggedIn_Client) {
                if (clientsComboBox == null) {
                    return;
                }
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
            availableBalance.setText("Available Balance: " + currentClient.getBalance());
            label_1.setTextFill(Color.web("#d5f7e6"));
            label_2.setTextFill(Color.web("#d5f7e6"));

            label_1.setText("Amount of");
            label_2.setText(amount.getText());
            label_3.setText("sent successfully to");
            label_4.setText(clientsComboBox.getValue().toString());


            currentClient.AddTransaction(new Transaction("Transfered to " + transferTo_Client.getUsername(), selectedAmount * -1));
            transferTo_Client.AddTransaction(new Transaction("Recieved From " + currentClient.getUsername(), selectedAmount));


            amount.clear();
        } else {
            label_1.setTextFill(Color.color(1, 0, 0));
            label_1.setText("Insufficient Balance");
        }
    }

    private void resetLabels() {
        label_1.setText("");
        label_2.setText("");
        label_3.setText("");
        label_4.setText("");
    }


}
