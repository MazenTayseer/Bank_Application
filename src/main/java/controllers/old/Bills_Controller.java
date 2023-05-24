package controllers.old;

import main.App;

import java.io.IOException;


public class Bills_Controller {
    public void back() throws IOException {
        new App().changeScene("client/client_dashboard.fxml");
    }

    public void currentBills() throws IOException {
        new App().changeScene("client/bills/current_bills.fxml");
    }
}
