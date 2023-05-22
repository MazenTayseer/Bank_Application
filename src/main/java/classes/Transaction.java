package classes;

public class Transaction {
    private Client fromClient, toClient;
    private double amount;

    public Transaction(Client fromClient, Client toClient, double amount) {
        this.fromClient = fromClient;
        this.toClient = toClient;
        this.amount = amount;
    }

    public String showSending() {
        return "An amount of " + this.amount + " was transferred to " + this.toClient.getUsername();
    }

    public String showReceiving() {
        return "An amount of " + this.amount + " was transferred from " + this.fromClient.getUsername();
    }
}
