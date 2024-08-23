package dev.aitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {
    private SavingsAccount savingsAccount;

    @BeforeEach
    void setUp() {
        // Setup a SavingsAccount with a balance of 10,000 and an annual interest rate of 3%
        savingsAccount = new SavingsAccount(10000, 3);
    }

    @Test
    void testInitialAccountState() {
        // Check that the account is active when initialized with a balance of 10,000
        assertTrue(savingsAccount.printStatement().contains("Balance: $10000.0"));
        assertTrue(savingsAccount.printStatement().contains("Transactions: 0"));
    }

    @Test
    void testDepositWhenAccountIsActive() {
        savingsAccount.deposit(2000);
        assertEquals(12000, savingsAccount.balance);
        assertTrue(savingsAccount.isActive);  // Account should remain active
    }

    @Test
    void testDepositWhenAccountIsInactive() {
        savingsAccount.withdraw(9500);  // Make the account inactive by lowering the balance below 10,000
        savingsAccount.deposit(500);
        assertEquals(500, savingsAccount.balance);  // Balance remains unchanged because the account is inactive
        assertFalse(savingsAccount.isActive);  // Account should still be inactive
    }

    @Test
    void testWithdrawWhenAccountIsActive() {
        savingsAccount.withdraw(1000);
        assertEquals(9000, savingsAccount.balance);
        assertFalse(savingsAccount.isActive);  // Account should become inactive
    }

    @Test
    void testWithdrawWhenAccountIsInactive() {
        savingsAccount.withdraw(9500);  // Lower the balance below 10,000, making the account inactive
        savingsAccount.withdraw(500);  // Attempt to withdraw when account is inactive
        assertEquals(500, savingsAccount.balance);  // Balance should remain unchanged because account is inactive
    }

    @Test
    void testMonthlyStatementWithMoreThanFourWithdrawals() {
        // Perform 5 withdrawals
        for (int i = 0; i < 5; i++) {
            savingsAccount.withdraw(1000);
        }
        savingsAccount.monthlyStatement();
        assertTrue(savingsAccount.printStatement().contains("Monthly Fee: $1000.0"));
        assertFalse(savingsAccount.isActive);  // Account should become inactive
    }

    @Test
    void testMonthlyStatementWithLessThanFourWithdrawals() {
        // Perform 4 withdrawals
        for (int i = 0; i < 4; i++) {
            savingsAccount.withdraw(1000);
        }
        savingsAccount.monthlyStatement();
        assertFalse(savingsAccount.printStatement().contains("Monthly Fee: $1000.0"));
        assertFalse(savingsAccount.isActive);  // Account should become inactive
    }

    @Test
    void testAccountReactivationAfterDeposit() {
        savingsAccount.withdraw(9500);  // Make account inactive
        savingsAccount.deposit(6000);   // Deposit enough to reactivate the account
        assertTrue(savingsAccount.printStatement().contains("Balance: $11000.0"));
        assertTrue(savingsAccount.isActive);  // Account should become active again
    }
    
    @Test
    void testPrintStatement() {
        String statement = savingsAccount.printStatement();
        assertTrue(statement.contains("Balance: $10000.0"));
        assertTrue(statement.contains("Monthly Fee: $0.0"));
        assertTrue(statement.contains("Transactions: 0"));
    }
}
