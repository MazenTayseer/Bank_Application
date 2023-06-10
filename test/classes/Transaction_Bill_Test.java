package classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class Transaction_Bill_Test {
    private Bill bill;
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        System.out.println("Starting a Test Case");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test Case Passed");
    }

    @Test
    public void getBillValues() {
        bill = new Bill("Water", new Date(2023, 5, 30), 200.50);
        assertEquals("Water", bill.getName());
        assertEquals(new Date(2023, 5, 30), bill.getDueDate());
        assertEquals(200.50, bill.getAmount(), 0.001);
    }

    @Test
    public void getTransactionValues() {
        transaction = new Transaction("Water", 50);
        assertEquals(50, transaction.getAmount(), 0.001);

        transaction = new Transaction("Water", 50);
        transaction.setAmount(300);
        assertEquals(300, transaction.getAmount(), 0.001);
    }
}