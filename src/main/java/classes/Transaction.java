package classes;

public class Transaction {
    private Client fromClient, toClient;
    private double amount;

    public Transaction(Client fromClient, Client toClient, double amount) {
        this.fromClient = fromClient;
        this.toClient = toClient;
        this.amount = amount;
    }

//    public String transfer_showSending() {
//        return "An amount of " + this.amount + " was transferred to " + this.toClient.getUsername();
//    }
//
//    public String transfer_showReceiving() {
//        return "An amount of " + this.amount + " was transferred from " + this.fromClient.getUsername();
//    }
}
