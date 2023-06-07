package main;

import classes.Admin;
import classes.Bill;
import classes.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App extends Application {
    private static Stage stg;
    public static List<Client> clientsList = new ArrayList<>();
    public static List<Admin> adminsList = new ArrayList<>();
    public static Client loggedIn_Client;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Online Banking");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        Client c1 = new Client("Mazen", "1", 1000);
        Client c2 = new Client("1", "1", 1000);
        c1.addToBills(new Bill("Water", new Date(2023, 5, 30), 250.50));
        c2.addToBills(new Bill("Electricity", new Date(2023, 6, 10), 400));

        clientsList.add(c1);
        clientsList.add(c2);
        clientsList.add(new Client("Ahmed", "1", 1500));

        adminsList.add(new Admin("admin", "admin"));

        launch();
    }
}