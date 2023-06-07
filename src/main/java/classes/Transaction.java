package classes;

public class Transaction {
    public static int ID = 0;
    public int id;
    public String type;
    public double amount;

    public Transaction(String type, double amount) {
        ID++;
        this.id = ID;
        this.type = type;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}