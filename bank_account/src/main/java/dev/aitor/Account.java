package dev.aitor;

public class Account {
    protected float balance;
    protected int numberOfDeposits;
    protected int numberOfWithdrawals;
    protected float annualInterestRate;
    protected float monthlyFee;

    public Account(float balance, float annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.numberOfDeposits = 0;
        this.numberOfWithdrawals = 0;
        this.monthlyFee = 0.0f;
    }

    public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            numberOfDeposits++;
        }
    }

    public void withdraw(float amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            numberOfWithdrawals++;
        }
    }

    public void calculateMonthlyInterest() {
        float monthlyInterest = balance * (annualInterestRate / 12) / 100;
        balance += monthlyInterest;
    }

    public void monthlyStatement() {
        balance -= monthlyFee;
        calculateMonthlyInterest();
        monthlyFee = 0.0f;
    }

    public String printStatement() {
        return "Balance: $" + balance + "\n" +
               "Deposits: " + numberOfDeposits + "\n" +
               "Withdrawals: " + numberOfWithdrawals + "\n" +
               "Annual Interest Rate: " + annualInterestRate + "%\n" +
               "Monthly Fee: $" + monthlyFee;
    }
}
