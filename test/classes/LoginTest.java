package classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    private Client testClient_1, testClient_2;
    private Admin testAdmin;

    @BeforeEach
    void setUp() {
        System.out.println("Starting a Test Case");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Case Passed");
    }

    @Test
    void login() {
        testClient_1 = new Client("Mazen", "CowMoon1$", 0);
        testClient_2 = new Client("kareem", "asdfgh", 0);

        assertTrue(testClient_1.login("Mazen", "CowMoon1$"));

        assertFalse(testClient_1.login("Mazen", "asdfgh"));
        assertFalse(testClient_2.login("Kareem", "12345"));

        testAdmin = new Admin("admin", "admin");
        assertTrue(testAdmin.login("admin", "admin"));
        assertFalse(testAdmin.login("admin", "12345"));
    }

    @Test
    void getUsername() {
        testClient_1 = new Client("Mazen", "12345", 0);
        assertEquals(testClient_1.getUsername(), "Mazen");

        testAdmin = new Admin("admin", "admin");
        assertEquals(testAdmin.getUsername(), "admin");
    }

    @Test
    void getPassword() {
        testClient_1 = new Client("Mazen", "12345", 0);
        assertEquals(testClient_1.getPassword(), "12345");

        testAdmin = new Admin("admin", "admin");
        assertEquals(testAdmin.getPassword(), "admin");
    }
}