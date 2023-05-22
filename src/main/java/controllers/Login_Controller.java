package controllers;

import classes.Admin;
import classes.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.App;

import java.io.IOException;

import static main.App.*;

public class Login_Controller {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    private Client findClient(String username) {
        for (int i = 0; i < clientsList.size(); i++) {
            Client currentClient = clientsList.get(i);
            if (currentClient.getUsername().equals(username)) {
                return currentClient;
            }
        }
        return null;
    }

    private Admin findAdmin(String username) {
        for (int i = 0; i < adminsList.size(); i++) {
            Admin currentAdmin = adminsList.get(i);
            if (currentAdmin.getUsername().equals(username)) {
                return currentAdmin;
            }
        }
        return null;
    }

    public void login() throws IOException {
        Client currentClient = findClient(username.getText());
        Admin currentAdmin = findAdmin(username.getText());

        if (currentClient != null) {
            if (currentClient.login(username.getText(), password.getText())) {
                loggedIn_Client = currentClient;
                new App().changeScene("client/client_dashboard.fxml");
                return;
            }
        } else if (currentAdmin != null) {
            if (currentAdmin.login(username.getText(), password.getText())) {
//                new Main().changeScene("");
                System.out.println("Success");
                return;
            }
        }

        error.setText("Wrong username or password");
    }

}
