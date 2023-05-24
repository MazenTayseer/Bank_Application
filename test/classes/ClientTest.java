package classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assertEquals(testClient_1.transfer(testClient_2, 50.50), 949.50);
        assertEquals(testClient_2.transfer(testClient_2, 500), -1);

        testClient_1 = new Client("Mazen", "12345", 0);
        testClient_2 = new Client("Kareem", "12345", 500);
        assertEquals(testClient_1.transfer(testClient_2, 50), -1);
        assertEquals(testClient_2.transfer(testClient_1, 500), 0);
    }

    @Test
    void payBills() {
        testClient_1 = new Client("Mazen", "12345", 1000);
        bill = new Bill("Water", new Date(2023, 5, 30), 250.50);
        testClient_1.addToBills(bill);

        assertEquals(testClient_1.payBill(bill), 749.50);

        testClient_1 = new Client("Mazen", "12345", 249);
        bill = new Bill("Water", new Date(2023, 5, 30), 250.50);
        testClient_1.addToBills(bill);

        assertEquals(testClient_1.payBill(bill), -1);
    }

    @Test
    void getUsername() {
        testClient_1 = new Client("Mazen", "12345", 0);
        assertEquals(testClient_1.getUsername(), "Mazen");
    }

    @Test
    void getPassword() {
        testClient_1 = new Client("Mazen", "12345", 0);
        assertEquals(testClient_1.getPassword(), "12345");
    }

    @Test
    void getBalance() {
        testClient_1 = new Client("Mazen", "12345", 50.50);
        assertEquals(testClient_1.getBalance(), 50.50);
    }
}