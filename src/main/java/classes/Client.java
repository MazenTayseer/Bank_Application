package classes;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private String username;
    private String password;
    private double balance;
    private ArrayList<Bill> bills = new ArrayList<>();

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Client(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            return false;
        }

        Pattern specialChars = Pattern.compile("[^A-Za-z0-9]");
        Matcher matcher = specialChars.matcher(password);
        if (!matcher.find()) {
            return false;
        }

        return true;
    }

    public boolean isValidUsername(String username) {
        int minLength = 5;
        if (username.length() < minLength) {
            return false;
        }

        if (!username.matches("^[a-zA-Z0-9]+$")) {
            return false;
        }

        return true;
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

    public double payBill(Bill bill) {
        if (this.balance < bill.getAmount()) {
            return -1;
        } else {
            setBalance(this.balance -= bill.getAmount());
            for (int i = 0; i < this.bills.size(); i++) {
                if (this.bills.get(i).equals(bill)) {
                    this.bills.remove(i);
                    break;
                }
            }

            return getBalance();
        }
    }

    public void AddTransaction(Transaction transaction) {
        this.transactions.add(transaction);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addToBills(Bill bill) {
        this.bills.add(bill);

    }
}
