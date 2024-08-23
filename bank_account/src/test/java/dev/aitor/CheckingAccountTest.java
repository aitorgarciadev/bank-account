package dev.aitor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

    @Test
    void testOverdraft() {
        CheckingAccount checkingAccount = new CheckingAccount(500, 5);
        checkingAccount.withdraw(1000);
        assertEquals(500, checkingAccount.overdraft);
    }


    @Test
    void testReduceOverdraft() {
        CheckingAccount checkingAccount = new CheckingAccount(0, 5);
        checkingAccount.overdraft = 500;
        checkingAccount.deposit(300);
        assertEquals(200, checkingAccount.overdraft);
    }
}
