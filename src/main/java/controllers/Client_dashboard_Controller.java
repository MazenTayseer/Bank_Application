package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.App.loggedIn_Client;

public class Client_dashboard_Controller implements Initializable {
    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeText.setText("Hello, " + loggedIn_Client.getUsername());
    }

    public void transfer() throws IOException {
        new App().changeScene("client/transfer.fxml");
    }

    public void bills() throws IOException {
        new App().changeScene("client/bills/bills.fxml");
    }
}
