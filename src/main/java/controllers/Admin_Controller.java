package controllers;

import classes.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.App;
import main.ConsoleColors;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static main.App.clientsList;
import static main.App.loggedIn_Client;

public class Admin_Controller implements Initializable {
    @FXML
    public Label label_1, label_2, label_3, label_4;
    @FXML
    public TextField amount, name;
    @FXML
    public PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amount.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private boolean findUsername(String username) {
        for (int i = 0; i < clientsList.size(); i++) {
            if (username.equals(clientsList.get(i).getUsername())) {
                return true;
            }
        }

        return false;
    }

    public void addClient() {
        long startTime = System.currentTimeMillis();
        resetLabels();

        if (amount.getText().trim().isEmpty() || name.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            label_1.setTextFill(Color.color(1, 0, 0));
            label_1.setText("Enter missing data");
            return;
        }

        String clientName = name.getText();
        String clientPassword = password.getText();
        String clientAmount = amount.getText();

        if (!findUsername(clientName)) {
            Client newClient = new Client(clientName, clientPassword, Double.parseDouble(clientAmount));
            if (newClient.isPasswordValid(clientPassword) && newClient.isValidUsername(clientName)) {
                clientsList.add(newClient);
                name.clear();
                amount.clear();
                password.clear();
                label_1.setTextFill(Color.web("#d5f7e6"));
                label_2.setTextFill(Color.web("#d5f7e6"));
                label_3.setTextFill(Color.web("#d5f7e6"));
                label_4.setTextFill(Color.web("#d5f7e6"));

                label_1.setText("Client with username");
                label_2.setText(clientName);
                label_3.setText("added successfully.");
            } else if (!newClient.isPasswordValid(clientPassword)) {
                label_1.setTextFill(Color.color(1, 0, 0));
                label_2.setTextFill(Color.color(1, 0, 0));
                label_3.setTextFill(Color.color(1, 0, 0));
                label_4.setTextFill(Color.color(1, 0, 0));

                label_1.setText("Password must have:");
                label_2.setText("Capital and small letters");
                label_3.setText("Number and Special characters");
                label_4.setText("More than 7 characters");

            } else {
                label_1.setTextFill(Color.color(1, 0, 0));
                label_2.setTextFill(Color.color(1, 0, 0));
                label_3.setTextFill(Color.color(1, 0, 0));
                label_4.setTextFill(Color.color(1, 0, 0));

                label_1.setText("Username must have:");
                label_2.setText("Letters and numbers");
                label_3.setText("More than 4 letters");
            }
        } else {
            label_1.setTextFill(Color.color(1, 0, 0));
            label_1.setText("Username already exists");
        }

        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Add New Client Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();
    }

    private void resetLabels() {
        label_1.setText("");
        label_2.setText("");
        label_3.setText("");
        label_4.setText("");
    }

    public void Log_Out() throws IOException {
        long startTime = System.currentTimeMillis();
        new App().changeScene("login.fxml");
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Logout Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();
    }

    public void delete_client() throws IOException {
        long startTime = System.currentTimeMillis();
        new App().changeScene("client/delete.fxml");
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println(ConsoleColors.ANSI_BLUE + "Delete Screen Time = " + timeTaken + " ms" + ConsoleColors.ANSI_RESET);
        System.out.println();
    }
}
