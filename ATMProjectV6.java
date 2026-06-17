import java.util.*;
import java.io.*;


class ATM {

    private long atmNumber;
    private int atmPin;
    private double balance;

    private ArrayList<String> transactions;

    private Scanner sc;

    private final String BALANCE_FILE = "balance.txt";
    private final String TRANSACTION_FILE = "transactions.txt";

    public ATM() {

        atmNumber = 12345;
        atmPin = 1234;

        transactions = new ArrayList<>();
        sc = new Scanner(System.in);

        loadBalance();
        loadTransactions();
    }

    // LOGIN
    public boolean login() {

        System.out.print("Enter ATM Number: ");
        long enteredAtm = getLongInput();

        System.out.print("Enter ATM PIN: ");
        int enteredPin = getIntInput();

        return enteredAtm == atmNumber &&
                enteredPin == atmPin;
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

            System.out.print("Enter Choice: ");

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
                    System.out.println("Thank You");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    // CHECK BALANCE
    public void checkBalance() {

        System.out.println(
                "Current Balance : Rs." + balance);
    }

    // DEPOSIT
    public void deposit() {

        System.out.print(
                "Enter amount to deposit : ");

        double amount = getDoubleInput();

        if (amount > 0) {

            balance += amount;

            String transaction =
                    "Deposited Rs." + amount;

            transactions.add(transaction);

            saveBalance();
            saveTransaction(transaction);

            System.out.println(
                    "Deposit Successful");
        } else {

            System.out.println(
                    "Invalid Amount");
        }
    }

    // WITHDRAW
    public void withdraw() {

        System.out.print(
                "Enter amount to withdraw : ");

        double amount = getDoubleInput();

        if (amount <= 0) {

            System.out.println(
                    "Invalid Amount");
            return;
        }

        if (amount > balance) {

            System.out.println(
                    "Insufficient Balance");
            return;
        }

        balance -= amount;

        String transaction =
                "Withdrawn Rs." + amount;

        transactions.add(transaction);

        saveBalance();
        saveTransaction(transaction);

        System.out.println(
                "Please Collect Cash");
    }

    // TRANSACTION HISTORY
    public void showTransactions() {

        System.out.println(
                "\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {

            System.out.println(
                    "No Transactions Yet");
            return;
        }

        for (String t : transactions) {

            System.out.println(t);
        }
    }

    // CHANGE PIN
    public void changePin() {

        System.out.print(
                "Enter Current PIN : ");

        int oldPin = getIntInput();

        if (oldPin != atmPin) {

            System.out.println(
                    "Incorrect PIN");
            return;
        }

        System.out.print(
                "Enter New PIN : ");

        int newPin = getIntInput();

        System.out.print(
                "Confirm New PIN : ");

        int confirmPin = getIntInput();

        if (newPin == confirmPin) {

            atmPin = newPin;

            System.out.println(
                    "PIN Updated Successfully");
        } else {
            System.out.println(
                    "PIN Mismatch");
        }
    }

    // SAVE BALANCE TO FILE
    private void saveBalance() {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(BALANCE_FILE));

            bw.write(String.valueOf(balance));

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error Saving Balance");
        }
    }

    // LOAD BALANCE
    private void loadBalance() {

        try {

            File file =
                    new File(BALANCE_FILE);

            if (!file.exists()) {

                balance = 10000;

                saveBalance();

                return;
            }

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(BALANCE_FILE));

            balance =
                    Double.parseDouble(
                            br.readLine());

            br.close();

        } catch (Exception e) {

            balance = 10000;
        }
    }

    // SAVE TRANSACTION
    private void saveTransaction(
            String transaction) {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    TRANSACTION_FILE,
                                    true));

            bw.write(transaction);

            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error Saving Transaction");
        }
    }

    // LOAD TRANSACTIONS
    private void loadTransactions() {

        try {

            File file =
                    new File(
                            TRANSACTION_FILE);

            if (!file.exists()) {

                return;
            }

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    TRANSACTION_FILE));

            String line;

            while ((line = br.readLine())
                    != null) {

                transactions.add(line);
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error Loading Transactions");
        }
    }

    // SAFE INPUT METHODS

    private int getIntInput() {

        while (true) {

            try {

                return sc.nextInt();

            } catch (
                    InputMismatchException e) {

                System.out.print(
                        "Enter Number Only : ");

                sc.nextLine();
            }
        }
    }

    private long getLongInput() {

        while (true) {

            try {

                return sc.nextLong();

            } catch (
                    InputMismatchException e) {

                System.out.print(
                        "Enter Valid ATM Number : ");

                sc.nextLine();
            }
        }
    }

    private double getDoubleInput() {

        while (true) {

            try {

                return sc.nextDouble();

            } catch (
                    InputMismatchException e) {

                System.out.print(
                        "Enter Numeric Amount : ");

                sc.nextLine();
            }
        }
    }
}

public class ATMProjectV6 {

    public static void main(
            String[] args) {

        ATM atm = new ATM();

        System.out.println(
                "===== WELCOME TO ATM =====");

        if (atm.login()) {

            atm.menu();

        } else {

            System.out.println(
                    "Invalid ATM Number or PIN");
        }
    }
}
