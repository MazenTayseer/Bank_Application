package controllers.old;

import classes.Bill;
import classes.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import main.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.App.loggedIn_Client;

public class CurrentBills_Controller implements Initializable {
    @FXML
    private TableView<Bill> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Client currentClient = loggedIn_Client;
    }

    public void back() throws IOException {
        new App().changeScene("client/client_dashboard.fxml");
    }

    public void currentBills() throws IOException {
        new App().changeScene("client/bills/current_bills.fxml");
    }
}
