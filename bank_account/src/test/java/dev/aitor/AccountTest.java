package dev.aitor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testDeposit() {
        Account account = new Account(1000, 5);
        account.deposit(500);
        assertEquals(1500, account.balance);
    }

    @Test
    void testWithdraw() {
        Account account = new Account(1000, 5);
        account.withdraw(200);
        assertEquals(800, account.balance);
    }

    @Test
    void testCalculateMonthlyInterest() {
        Account account = new Account(1200, 12);
        account.calculateMonthlyInterest();
        assertEquals(1212, account.balance, 0.01);
    }
}
