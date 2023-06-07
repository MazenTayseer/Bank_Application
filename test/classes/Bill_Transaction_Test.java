package classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Bill_Transaction_Test {
    private Bill bill;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        System.out.println("Starting a Test Case");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Case Passed");
    }

    @Test
    void getBillValues() {
        bill = new Bill("Water", new Date(2023, 5, 30), 200.50);
        assertEquals("Water", bill.getName());
        assertEquals(new Date(2023, 5, 30), bill.getDueDate());
        assertEquals(200.50, bill.getAmount());
    }

    @Test
    void getTransactionValues() {
        transaction = new Transaction("Water", 50);
        assertEquals(50, transaction.getAmount());

        transaction = new Transaction("Water", 50);
        transaction.setAmount(300);
        assertEquals(300, transaction.getAmount());
    }
}