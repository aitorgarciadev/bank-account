package dev.aitor;

public class CheckingAccount extends Account {
    public float overdraft;

    public CheckingAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.overdraft = 0.0f;
    }

    @Override
    public void withdraw(float amount) {
        if (amount > balance) {
            overdraft += (amount - balance);
            balance = 0;
        } else {
            balance -= amount;
        }
        numberOfWithdrawals++;
    }

    @Override
    public void deposit(float amount) {
        if (amount > 0) {
            if (overdraft > 0) {
                if (amount >= overdraft) {
                    amount -= overdraft;
                    overdraft = 0;
                } else {
                    overdraft -= amount;
                    amount = 0;
                }
            }
            super.deposit(amount);
        }
    }

    @Override
    public String printStatement() {
        return "Balance: $" + balance + "\n" +
               "Monthly Fee: $" + monthlyFee + "\n" +
               "Transactions: " + (numberOfDeposits + numberOfWithdrawals) + "\n" +
               "Overdraft: $" + overdraft;
    }
}
