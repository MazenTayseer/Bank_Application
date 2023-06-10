package classes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Client_Test.class,
        Login_Test.class,
        Transaction_Bill_Test.class
})

public class TestSuite {
}