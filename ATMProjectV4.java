import java.util.*;

class ATM {

    private long atmNumber;
    private int atmPin;
    private double balance;

    private ArrayList<String> transactions = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    // Constructor
    public ATM() {

        atmNumber = 12345;
        atmPin = 1234;
        balance = 10000;


        System.out.println("ATM Account Initialized");
    }

    public boolean login() {

        System.out.print("Enter ATM Number: ");
        long enteredAtm = sc.nextLong();

        System.out.print("Enter ATM PIN: ");
        int enteredPin = sc.nextInt();

        return enteredAtm == atmNumber &&
               enteredPin == atmPin;
    }

    public void menu() {

        while(true) {

            System.out.println("\n===== ATM MENU =====");

            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch(choice) {

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
                    System.out.println("Thank You");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void checkBalance() {

        System.out.println(
            "Current Balance : Rs." + balance
        );
    }

    public void deposit() {

        System.out.print(
            "Enter amount to deposit : "
        );

        double amount = sc.nextDouble();

        if(amount > 0) {

            balance += amount;

            transactions.add(
                "Deposited Rs." + amount
            );

            System.out.println(
                "Amount Deposited Successfully"
            );
        }
        else {

            System.out.println(
                "Invalid Amount"
            );
        }
    }

    public void withdraw() {

        System.out.print(
            "Enter amount to withdraw : "
        );

        double amount = sc.nextDouble();

        if(amount <= 0) {

            System.out.println(
                "Invalid Amount"
            );

            return;
        }

        if(amount > balance) {

            System.out.println(
                "Insufficient Balance"
            );

            return;
        }

        balance -= amount;

        transactions.add(
            "Withdrawn Rs." + amount
        );

        System.out.println(
            "Please Collect Cash"
        );
    }

    public void showTransactions() {

        System.out.println(
            "\n===== TRANSACTION HISTORY ====="
        );

        if(transactions.isEmpty()) {

            System.out.println(
                "No Transactions Yet"
            );

            return;
        }

        for(String t : transactions) {

            System.out.println(t);
        }
    }

    public void changePin() {

        System.out.print(
            "Enter Current PIN : "
        );

        int oldPin = sc.nextInt();

        if(oldPin != atmPin) {

            System.out.println(
                "Incorrect Current PIN"
            );

            return;
        }

        System.out.print(
            "Enter New PIN : "
        );

        int newPin = sc.nextInt();

        System.out.print(
            "Confirm New PIN : "
        );

        int confirmPin = sc.nextInt();

        if(newPin == confirmPin) {

            atmPin = newPin;

            System.out.println(
                "PIN Changed Successfully"
            );
        }
        else {

            System.out.println(
                "PIN Mismatch"
            );
        }
    }
}

public class ATMProjectV4{

    public static void main(String[] args) {

        ATM user = new ATM();

        System.out.println(
            "===== WELCOME TO ATM ====="
        );

        if(user.login()) {

            user.menu();
        }
        else {

            System.out.println(
                "Invalid ATM Number or PIN"
            );
        }
    }
}