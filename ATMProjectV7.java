import java.util.*;

class Account {

    private String holderName;
    private long atmNumber;
    private int pin;
    private double balance;

    private ArrayList<String> transactions;

    public Account(String holderName,
                   long atmNumber,
                   int pin,
                   double balance) {

        this.holderName = holderName;
        this.atmNumber = atmNumber;
        this.pin = pin;
        this.balance = balance;

        transactions = new ArrayList<>();
    }

    public String getHolderName() {
        return holderName;
    }

    public long getAtmNumber() {
        return atmNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }
}

public class ATMProjectV7 {

    private Scanner sc;
    private ArrayList<Account> accounts;
    private Account currentAccount;

    public ATMProjectV7() {

        sc = new Scanner(System.in);

        accounts = new ArrayList<>();

        accounts.add(
                new Account(
                        "Keerthi",
                        1111,
                        1234,
                        10000));

        accounts.add(
                new Account(
                        "Chandru",
                        2222,
                        5678,
                        5000));

        accounts.add(
                new Account(
                        "Raja",
                        3333,
                        9999,
                        15000));
    }

    public void createAccount() {

        System.out.println("\n----- CREATE ACCOUNT -----");

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter ATM Number: ");
        long atmNumber = sc.nextLong();

        System.out.print("Create PIN: ");
        int pin = sc.nextInt();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        Account account =
                new Account(
                        name,
                        atmNumber,
                        pin,
                        balance);

        accounts.add(account);

        System.out.println("Account Created Successfully!");
    }

    public boolean login() {

        System.out.println("\n----- LOGIN -----");

        System.out.print("Enter ATM Number: ");
        long enteredAtm = sc.nextLong();

        System.out.print("Enter PIN: ");
        int enteredPin = sc.nextInt();

        for(Account acc : accounts) {

            if(acc.getAtmNumber() == enteredAtm &&
               acc.getPin() == enteredPin) {

                currentAccount = acc;

                System.out.println(
                        "\nWelcome "
                        + acc.getHolderName());

                return true;
            }
        }

        System.out.println("Invalid ATM Number or PIN");
        return false;
    }

    public void checkBalance() {

        System.out.println(
                "\nCurrent Balance: Rs."
                + currentAccount.getBalance());
    }

    public void deposit() {

        System.out.print(
                "\nEnter Deposit Amount: ");

        double amount = sc.nextDouble();

        currentAccount.setBalance(
                currentAccount.getBalance()
                        + amount);

        currentAccount
                .getTransactions()
                .add("Deposited Rs."
                        + amount);

        System.out.println(
                "Amount Deposited Successfully");
    }

    public void withdraw() {

        System.out.print(
                "\nEnter Withdraw Amount: ");

        double amount = sc.nextDouble();

        if(amount >
                currentAccount.getBalance()) {

            System.out.println(
                    "Insufficient Balance");

            return;
        }

        currentAccount.setBalance(
                currentAccount.getBalance()
                        - amount);

        currentAccount
                .getTransactions()
                .add("Withdrawn Rs."
                        + amount);

        System.out.println(
                "Amount Withdrawn Successfully");
    }

    public void transferMoney() {

        System.out.print(
                "\nEnter Receiver ATM Number: ");

        long receiverAtm =
                sc.nextLong();

        Account receiver = null;

        for(Account acc : accounts) {

            if(acc.getAtmNumber()
                    == receiverAtm) {

                receiver = acc;
                break;
            }
        }

        if(receiver == null) {

            System.out.println(
                    "Receiver Not Found");

            return;
        }

        System.out.print(
                "Enter Amount: ");

        double amount =
                sc.nextDouble();

        if(amount >
                currentAccount.getBalance()) {

            System.out.println(
                    "Insufficient Balance");

            return;
        }

        currentAccount.setBalance(
                currentAccount.getBalance()
                        - amount);

        receiver.setBalance(
                receiver.getBalance()
                        + amount);

        currentAccount
                .getTransactions()
                .add("Transferred Rs."
                        + amount
                        + " to "
                        + receiver.getHolderName());

        System.out.println(
                "Transfer Successful");
    }

    public void showTransactions() {

        System.out.println(
                "\n----- TRANSACTIONS -----");

        ArrayList<String> list =
                currentAccount
                        .getTransactions();

        if(list.isEmpty()) {

            System.out.println(
                    "No Transactions Found");

            return;
        }

        for(String transaction : list) {

            System.out.println(
                    transaction);
        }
    }

    public void atmMenu() {

        while(true) {

            System.out.println(
                    "\n===== ATM MENU =====");

            System.out.println(
                    "1. Check Balance");
            System.out.println(
                    "2. Deposit");
            System.out.println(
                    "3. Withdraw");
            System.out.println(
                    "4. Transfer Money");
            System.out.println(
                    "5. Transactions");
            System.out.println(
                    "6. Logout");

            System.out.print(
                    "Choose Option: ");

            int choice =
                    sc.nextInt();

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
                    transferMoney();
                    break;

                case 5:
                    showTransactions();
                    break;

                case 6:
                    return;

                default:
                    System.out.println(
                            "Invalid Choice");
            }
        }
    }

    public void start() {

        while(true) {

            System.out.println(
                    "\n===== MAIN MENU =====");

            System.out.println(
                    "1. Login");

            System.out.println(
                    "2. Create Account");

            System.out.println(
                    "3. Exit");

            System.out.print(
                    "Choose Option: ");

            int choice =
                    sc.nextInt();

            switch(choice) {

                case 1:

                    if(login()) {

                        atmMenu();
                    }

                    break;

                case 2:

                    createAccount();
                    break;

                case 3:

                    System.out.println(
                            "Thank You!");

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }
    }

    public static void main(
            String[] args) {

        ATMProjectV7 atm =
                new ATMProjectV7();

        atm.start();
    }
}