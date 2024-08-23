package dev.aitor;

public class SavingsAccount extends Account {
    public boolean isActive;

    public SavingsAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.isActive = balance >= 10000;
    }

    @Override
    public void deposit(float amount) {
        if (isActive) {
            super.deposit(amount);
            isActive = balance >= 10000;
        }
    }

    @Override
    public void withdraw(float amount) {
        if (isActive && numberOfWithdrawals < 4) {
            super.withdraw(amount);
            isActive = balance >= 10000;
        }
    }

    @Override
    public void monthlyStatement() {
        if (numberOfWithdrawals > 4) {
            monthlyFee += 1000 * (numberOfWithdrawals - 4);
        }
        super.monthlyStatement();
        isActive = balance >= 10000;
    }

    @Override
    public String printStatement() {
        return "Balance: $" + balance + "\n" +
               "Monthly Fee: $" + monthlyFee + "\n" +
               "Transactions: " + (numberOfDeposits + numberOfWithdrawals);
    }
}
