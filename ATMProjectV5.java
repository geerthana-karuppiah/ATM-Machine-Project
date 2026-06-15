import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class ATM {

    private long atmNumber;
    private int atmPin;
    private double balance;

    private ArrayList<String> transactions;
    private Scanner sc;

    // Constructor
    public ATM() {

        atmNumber = 1234567890L;
        atmPin = 1234;
        balance = 10000;

        transactions = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    // LOGIN
    public boolean login() {

        System.out.print("Enter ATM Number: ");
        long enteredAtm = getLongInput();

        System.out.print("Enter ATM PIN: ");
        int enteredPin = getIntInput();

        return enteredAtm == atmNumber && enteredPin == atmPin;
    }

    // MENU
    public void menu() {

        while (true) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = getIntInput();

            switch (choice) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    showTransactions();
                    break;

                case 5:
                    changePin();
                    break;

                case 6:
                    System.out.println("Thank you for using ATM");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    // BALANCE
    public void checkBalance() {
        System.out.println("Available Balance: Rs." + balance);
    }

    // DEPOSIT
    public void deposit() {

        System.out.print("Enter amount to deposit: ");
        double amount = getDoubleInput();

        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited Rs." + amount);
            System.out.println("Deposit Successful");
        } else {
            System.out.println("Invalid Amount");
        }
    }

    // WITHDRAW
    public void withdraw() {

        System.out.print("Enter amount to withdraw: ");
        double amount = getDoubleInput();

        if (amount <= 0) {
            System.out.println("Invalid Amount");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient Balance");
            return;
        }

        balance -= amount;
        transactions.add("Withdrawn Rs." + amount);

        System.out.println("Please Collect Cash");
    }

    // TRANSACTIONS
    public void showTransactions() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No Transactions Yet");
            return;
        }

        for (String t : transactions) {
            System.out.println(t);
        }
    }

    // CHANGE PIN
    public void changePin() {

        System.out.print("Enter current PIN: ");
        int oldPin = getIntInput();

        if (oldPin != atmPin) {
            System.out.println("Incorrect PIN");
            return;
        }

        System.out.print("Enter new PIN: ");
        int newPin = getIntInput();

        System.out.print("Confirm new PIN: ");
        int confirmPin = getIntInput();

        if (newPin == confirmPin) {
            atmPin = newPin;
            System.out.println("PIN Updated Successfully");
        } else {
            System.out.println("PIN Mismatch");
        }
    }

    // SAFE INTEGER INPUT
    private int getIntInput() {

        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Enter number again: ");
                sc.nextLine(); // clear buffer
            }
        }
    }

    // SAFE LONG INPUT
    private long getLongInput() {

        while (true) {
            try {
                return sc.nextLong();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Enter valid ATM number: ");
                sc.nextLine();
            }
        }
    }

    // SAFE DOUBLE INPUT
    private double getDoubleInput() {

        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Enter numeric amount: ");
                sc.nextLine();
            }
        }
    }
}

// MAIN CLASS
public class ATMProjectV5 {

    public static void main(String[] args) {

        ATM atm = new ATM();

        System.out.println("===== WELCOME TO ATM =====");

        if (atm.login()) {
            atm.menu();
        } else {
            System.out.println("Invalid ATM Number or PIN");
        }
    }
}
