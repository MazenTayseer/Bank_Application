package controllers;

import classes.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import main.App;
import main.ConsoleColors;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static main.App.clientsList;
import static main.App.loggedIn_Client;

public class Delete_Controller implements Initializable {
    @FXML
    public ComboBox clientsComboBox;
    @FXML
    public Label label_1, label_2, label_3, label_4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddToDropDown();
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

    public void delete() {
        long startTime = System.currentTimeMillis();
        resetLabels();

        if (clientsComboBox.getSelectionModel().isEmpty()) {
            label_1.setTextFill(Color.color(1, 0, 0));
            label_1.setText("Choose a client");
            return;
        }

        Client transferTo_Client = findClient(clientsComboBox.getValue().toString());
        if (transferTo_Client != null) {
            clientsComboBox.getItems().remove(clientsComboBox.getValue());

            label_1.setTextFill(Color.web("#d5f7e6"));
            label_2.setTextFill(Color.web("#d5f7e6"));
            label_3.setTextFill(Color.web("#d5f7e6"));
            label_4.setTextFill(Color.web("#d5f7e6"));

            label_1.setText("Client with Username");
            label_2.setText(transferTo_Client.getUsername());
            label_3.setText("was deleted Successfully");

            transferTo_Client.delete_data();
        }

        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Delete Client Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();
    }


    public void back() throws IOException {
        long startTime = System.currentTimeMillis();
        new App().changeScene("client/admin.fxml");
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Back to dashboard time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();
    }

    private void resetLabels() {
        label_1.setText("");
        label_2.setText("");
        label_3.setText("");
        label_4.setText("");
    }


}
