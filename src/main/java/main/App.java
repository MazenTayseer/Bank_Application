package main;

import classes.Admin;
import classes.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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
        clientsList.add(new Client("Mazen", "1", 1000));
        clientsList.add(new Client("Ahmed", "1", 1500));

        adminsList.add(new Admin("admin", "admin"));

        launch();
    }
}