import java.util.Scanner;

public class ATMProjectV2 
{

    static Scanner sc = new Scanner(System.in);

    static long atmNumber = 1234567890L;
    static int atmPin = 1234;
    static double balance = 10000;
    static String miniStatement = "";

    public static void main(String[] args) 
    {

        System.out.println("========== WELCOME TO ATM ==========");

        if (login()) 
        {
            menu();
        } 
        else 
        {
            System.out.println("Incorrect ATM Number or PIN");
        }
    }

    // Login Method
    public static boolean login() 
    {
        System.out.print("Enter ATM Number: ");
        long enteredAtm = sc.nextLong();

        System.out.print("Enter ATM PIN: ");
        int enteredPin = sc.nextInt();

        return (enteredAtm == atmNumber && enteredPin == atmPin);
    }

    // Main Menu Method
    public static void menu() 
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

    // Balance Method
    public static void viewBalance() 
    {
        System.out.println("Available Balance: Rs. " + balance);
    }

    // Withdraw Method
    public static void withdrawAmount() 
    {
        System.out.print("Enter amount to withdraw: ");
        double withdraw = sc.nextDouble();

        if (withdraw <= balance && withdraw > 0) 
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

    // Deposit Method
    public static void depositAmount() 
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

    // Mini Statement Method
    public static void viewMiniStatement() 
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

    // Change PIN Method
    public static void changePin() 
    {
        System.out.print("Enter New PIN: ");
        int newPin = sc.nextInt();

        atmPin = newPin;
        System.out.println("PIN Changed Successfully");
    }
}