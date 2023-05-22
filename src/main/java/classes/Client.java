package classes;

import java.util.ArrayList;

public class Client {
    private String username;
    private String password;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Client(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }


    public double transfer(Client transferTo_Client, double amount) {
        if (this.balance < amount) {
            return -1;
        } else {
            setBalance(this.balance -= amount);
            transferTo_Client.setBalance(transferTo_Client.balance += amount);

            return getBalance();
        }
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addToTransactions(Transaction transaction) {
        transactions.add(transaction);
    }
}
