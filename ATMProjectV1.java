import java.util.Scanner;

public class ATMProjectV1
{

    public static void main(String[] args) 
    {

        Scanner sc = new Scanner(System.in);

        // Default ATM details
        long atmNumber = 12345;
        int atmPin = 1234;

        // Account details
        double balance = 10000;
        String miniStatement = "";

        System.out.println("========== WELCOME TO ATM ==========");

        // Login Section
        System.out.print("Enter ATM Number: ");
        long enteredAtm = sc.nextLong();

        System.out.print("Enter ATM PIN: ");
        int enteredPin = sc.nextInt();

        if (enteredAtm == atmNumber && enteredPin == atmPin) 
            {

            while (true) 
                {
                System.out.println("\n===== ATM MENU =====");
                System.out.println("1. View Available Balance");
                System.out.println("2. Withdraw Amount");
                System.out.println("3. Deposit Amount");
                System.out.println("4. View Mini Statement");
                System.out.println("5. Exit");

                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();

                switch (choice) 
                {

                    case 1:
                        System.out.println("Available Balance: Rs. " + balance);
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdraw = sc.nextDouble();

                        if (withdraw <= balance) 
                        {
                            balance = balance - withdraw;
                            System.out.println("Please collect your cash");
                            miniStatement += "Withdraw: Rs. " + withdraw + "\n";
                        } 
                        else 
                        {
                            System.out.println("Insufficient Balance");
                        }
                        break;

                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double deposit = sc.nextDouble();

                        balance = balance + deposit;
                        System.out.println("Amount Deposited Successfully");
                        miniStatement += "Deposit: Rs. " + deposit + "\n";
                        break;

                    case 4:
                        System.out.println("\n===== MINI STATEMENT =====");
                        System.out.println(miniStatement);
                        break;

                    case 5:
                        System.out.println("Thank You For Using ATM");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }
            }

        } 
        else 
        {
            System.out.println("Incorrect ATM Number or PIN");
        }
    }
}