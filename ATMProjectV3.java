import java.util.Scanner;

class ATM 
{

    private long atmNumber = 12345;
    private int atmPin = 1234;
    private double balance = 10000;
    private String miniStatement = "";

    Scanner sc = new Scanner(System.in);

    // Login Method
    public boolean login() 
    {
        System.out.print("Enter ATM Number: ");
        long enteredAtm = sc.nextLong();

        System.out.print("Enter ATM PIN: ");
        int enteredPin = sc.nextInt();

        return (enteredAtm == atmNumber && enteredPin == atmPin);
    }

    // Menu Method
    public void menu() 
    {
        while (true) 
            {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. View Available Balance");
            System.out.println("2. Withdraw Amount");
            System.out.println("3. Deposit Amount");
            System.out.println("4. View Mini Statement");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) 
            {
                case 1:
                    viewBalance();
                    break;

                case 2:
                    withdrawAmount();
                    break;

                case 3:
                    depositAmount();
                    break;

                case 4:
                    viewMiniStatement();
                    break;

                case 5:
                    changePin();
                    break;

                case 6:
                    System.out.println("Thank You For Using ATM");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    // View Balance
    public void viewBalance() 
    {
        System.out.println("Available Balance: Rs. " + balance);
    }

    // Withdraw Amount
    public void withdrawAmount() 
    {
        System.out.print("Enter amount to withdraw: ");
        double withdraw = sc.nextDouble();

        if (withdraw > 0 && withdraw <= balance) 
        {
            balance -= withdraw;
            System.out.println("Please collect your cash");
            miniStatement += "Withdraw: Rs. " + withdraw + "\n";
        } 
        else 
        {
            System.out.println("Invalid or Insufficient Balance");
        }
    }

    // Deposit Amount
    public void depositAmount() 
    {
        System.out.print("Enter amount to deposit: ");
        double deposit = sc.nextDouble();

        if (deposit > 0) 
        {
            balance += deposit;
            System.out.println("Amount Deposited Successfully");
            miniStatement += "Deposit: Rs. " + deposit + "\n";
        } 
        else 
        {
            System.out.println("Invalid Deposit Amount");
        }
    }

    // Mini Statement
    public void viewMiniStatement() 
    {
        System.out.println("\n===== MINI STATEMENT =====");

        if (miniStatement.isEmpty()) 
        {
            System.out.println("No Transactions Yet");
        } 
        else 
        {
            System.out.println(miniStatement);
        }
    }

    // Change PIN
    public void changePin() 
    {
        System.out.print("Enter New PIN: ");
        int newPin = sc.nextInt();

        atmPin = newPin;
        System.out.println("PIN Changed Successfully");
    }
}

public class ATMProject 
{

    public static void main(String[] args) 
    {

        ATM user = new ATM();

        System.out.println("========== WELCOME TO ATM ==========");

        if (user.login()) 
        {
            user.menu();
        } 
        else 
        {
            System.out.println("Incorrect ATM Number or PIN");
        }
    }
}