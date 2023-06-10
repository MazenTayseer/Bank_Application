package controllers;

import classes.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Transaction;

import static main.App.loggedIn_Client;

public class Transactions_Controller implements Initializable {

    @FXML
    public TableView<Transaction> TransfersTable;

    @FXML
    void BACK(ActionEvent event) throws IOException {
        new App().changeScene("client/client_dashboard.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Client currentClient = loggedIn_Client;
        TableColumn<Transaction, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Transaction, String> descriptionColumn = new TableColumn<>("Type");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Transaction, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        idColumn.setPrefWidth(100);
        descriptionColumn.setPrefWidth(504);
        amountColumn.setPrefWidth(252.79998779296875);

        if (TransfersTable != null) {
            TransfersTable.getColumns().clear();
            TransfersTable.getColumns().addAll(idColumn, descriptionColumn, amountColumn);
            TransfersTable.getItems().addAll(currentClient.getTransactions());

        }
    }

}

