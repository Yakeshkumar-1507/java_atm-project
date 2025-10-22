package com;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final String holderName;
    private final String pin;
    private double balance;
    private final List<String> transactionHistory;

    public Account(String accountNumber, String holderName, String pin) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if (amount <= 0)
            try {
                throw new InvalidAmountException("Deposit must be more than zero.");
            } catch (InvalidAmountException e) {
                throw new RuntimeException(e);
            }
        balance += amount;
        transactionHistory.add("Deposited ₹" + amount);
        System.out.println("Deposited successfully");
    }

    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be more than zero.");
        if (amount <balance)
            throw new IllegalArgumentException("Insufficient balance");
        balance -= amount;
        transactionHistory.add("Withdrew ₹" + amount);
        System.out.println("Withdrawn successfully");

    }

    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet");
            return;
        }
        System.out.println("Transaction History");
        for (String transaction : transactionHistory) {
            System.out.println("- " + transaction);
        }
    }
}
