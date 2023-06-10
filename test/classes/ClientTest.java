package classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client testClient_1, testClient_2;
    private Bill bill;


    @BeforeEach
    void setUp() {
        System.out.println("Starting a Test Case");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Case Passed");
    }

    @Test
    void transfer() {
        testClient_1 = new Client("Mazen", "12345", 1000);
        testClient_2 = new Client("Kareem", "12345", 300);

        assertEquals(949.50, testClient_1.transfer(testClient_2, 50.50));
        assertEquals(-1, testClient_2.transfer(testClient_2, 500));

        testClient_1 = new Client("Mazen", "12345", 0);
        testClient_2 = new Client("Kareem", "12345", 500);
        assertEquals(-1, testClient_1.transfer(testClient_2, 50));
        assertEquals(0, testClient_2.transfer(testClient_1, 500));
    }

    @Test
    void payBills() {
        testClient_1 = new Client("Mazen", "12345", 1000);
        bill = new Bill("Water", new Date(2023, 5, 30), 250.50);
        testClient_1.addToBills(bill);

        assertEquals(749.50, testClient_1.payBill(bill));

        testClient_1 = new Client("Mazen", "12345", 249);
        bill = new Bill("Water", new Date(2023, 5, 30), 250.50);
        testClient_1.addToBills(bill);

        assertEquals(-1, testClient_1.payBill(bill));
    }

    @Test
    void getUsername() {
        testClient_1 = new Client("Mazen", "12345", 0);
        assertEquals("Mazen", testClient_1.getUsername());
    }

    @Test
    void getPassword() {
        testClient_1 = new Client("Mazen", "12345", 0);
        assertEquals("12345", testClient_1.getPassword());
    }

    @Test
    void getBalance() {
        testClient_1 = new Client("Mazen", "12345", 50.50);
        assertEquals(50.50, testClient_1.getBalance());

        testClient_1 = new Client("Mazen", "12345", 50.50);
        testClient_1.setBalance(705.30);
        assertEquals(705.30, testClient_1.getBalance());

        testClient_1 = new Client("Mazen", "12345", 50.50);
        testClient_1.setBalance(-50);
        assertEquals(0, testClient_1.getBalance());
    }

    @Test
    void getBills() {
        testClient_1 = new Client("Mazen", "12345", 50.50);

        Bill bill1 = new Bill("Water", new Date(2023, 5, 30), 250.50);
        Bill bill2 = new Bill("Electricity", new Date(2023, 6, 10), 400);

        testClient_1.addToBills(bill1);
        testClient_1.addToBills(bill2);

        ArrayList<Bill> test_bills = new ArrayList<>();
        test_bills.add(bill1);
        test_bills.add(bill2);

        assertEquals(test_bills, testClient_1.getBills());
    }


    @Test
    void getTransactions() {
        testClient_1 = new Client("Mazen", "12345", 50.50);

        Transaction transaction1 = new Transaction("Bill", 250);
        Transaction transaction2 = new Transaction("Electricity", 600);

        testClient_1.AddTransaction(transaction1);
        testClient_1.AddTransaction(transaction2);

        ArrayList<Transaction> test_transactions = new ArrayList<>();
        test_transactions.add(transaction1);
        test_transactions.add(transaction2);

        assertEquals(test_transactions, testClient_1.getTransactions());
    }


    @Test
    void testUsername() {
        testClient_1 = new Client("Maz", "12345", 50.50);
        assertFalse(testClient_1.isValidUsername(testClient_1.getUsername()));

        testClient_1.setUsername("Mazen");
        assertTrue(testClient_1.isValidUsername(testClient_1.getUsername()));

        testClient_1.setUsername("Mohamed11");
        assertTrue(testClient_1.isValidUsername(testClient_1.getUsername()));
    }

    @Test
    void testPassword() {
        testClient_1 = new Client("Mazen", "12345", 50.50);
        assertFalse(testClient_1.isPasswordValid(testClient_1.getPassword()));

        testClient_1.setPassword("CowMoonBus");
        assertFalse(testClient_1.isPasswordValid(testClient_1.getPassword()));

        testClient_1.setPassword("CowMoonBus14");
        assertFalse(testClient_1.isPasswordValid(testClient_1.getPassword()));

        testClient_1.setPassword("cowmoonbus12");
        assertFalse(testClient_1.isPasswordValid(testClient_1.getPassword()));

        testClient_1.setPassword("cowmoonbus12$");
        assertFalse(testClient_1.isPasswordValid(testClient_1.getPassword()));

        testClient_1.setPassword("COWMOONBUS12$");
        assertFalse(testClient_1.isPasswordValid(testClient_1.getPassword()));

        testClient_1.setPassword("CowMoonBus14$");
        assertTrue(testClient_1.isPasswordValid(testClient_1.getPassword()));

    }


}