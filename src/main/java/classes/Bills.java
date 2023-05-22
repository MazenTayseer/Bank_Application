package classes;

import java.util.Date;

public class Bills {
    private String name;
    private Date dueDate;
    private double amount;

    public Bills(String name, Date dueDate, double amount) {
        this.name = name;
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public double getAmount() {
        return amount;
    }
}
