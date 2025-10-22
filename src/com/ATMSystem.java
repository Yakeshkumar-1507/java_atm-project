package com;

import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;


public class ATMSystem {
    private final HashMap<String, Account> accounts = new HashMap<>();
    private final Scanner scanner;
    private Account currentAccount;

    public ATMSystem(){
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("--- Welcome to Java ATM ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> login();
                    case 3 -> {
                        System.out.println("Thank you for using ATM");
                        return;
                    }
                    default -> {
                        System.out.println("Invalid Choice: Try Again ");
                    }
            }
        }
    }
    private void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Create a PIN: ");
        String pin = scanner.nextLine();
        String accountNumber = UUID.randomUUID().toString().substring(0, 8);
        Account account = new Account(accountNumber, name, pin);

        accounts.put(accountNumber, account);
        System.out.println("Account created successfully. Your account number is " +
                accountNumber);
    }
    private void login() {
        System.out.print("Enter account number: ");
        String accNo = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        Account acc = accounts.get(accNo);
        if (acc != null && acc.validatePin(pin)) {
            currentAccount = acc;
            System.out.println("Login successfull! ");
            accountMenu();
        } else {
            System.out.println("Invalid account number or PIN " );
        }
    }
    private void accountMenu() {
        while (true) {
            System.out.println(" ATM Menu ");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Balance: ₹" + currentAccount.getBalance());
                case 2 -> {
                    System.out.print("Enter amount to deposit: ₹");
                    double amount = scanner.nextDouble();
                    try {
                        currentAccount.deposit(amount);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 3 ->{
                    System.out.print("Enter amount to withdraw: ₹");
                    double amount = scanner.nextDouble();
                    try {
                        currentAccount.withdraw(amount);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 4 -> currentAccount.printTransactionHistory();
                case 5 -> {
                    System.out.println("Logged out successfully");
                    return;
                }
                default-> System.out.println("Invalid choice.");
            }
        }
    }

}



