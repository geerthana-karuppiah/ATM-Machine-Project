import java.io.*;
import java.util.*;

class Account {

    private String holderName;
    private long atmNumber;
    private int pin;
    private double balance;


    private ArrayList<String> transactions;


    public Account(
            String holderName,
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


    public ArrayList<String> getTransactions(){

        return transactions;
    }


}


public class ATMProjectV8 {

    private Scanner sc;

    private ArrayList<Account> accounts;

    private Account currentAccount;


    private final String FILE =
            "accounts.txt";

    public ATMProjectV8(){

        sc = new Scanner(System.in);


        accounts = new ArrayList<>();


        loadAccounts();

    }

// LOAD DATA FROM FILE

    public void loadAccounts(){


        try{


            BufferedReader br =
                    new BufferedReader(
                            new FileReader(FILE));


            String line;



            while(
              (line = br.readLine()) != null
            ){



                String parts[] =
                        line.split(",");



                Account acc =
                        new Account(

                        parts[0],

                        Long.parseLong(parts[1]),

                        Integer.parseInt(parts[2]),

                        Double.parseDouble(parts[3])

                        );



                accounts.add(acc);


            }


            br.close();



        }
        catch(FileNotFoundException e){


            System.out.println(
              "No file found. Creating new data.");

        }
        catch(IOException e){

            System.out.println(
              "File error");

        }


    }
     // SAVE DATA TO FILE

    public void saveAccounts(){

        try{


            BufferedWriter bw =
                    new BufferedWriter(
                    new FileWriter(FILE));




            for(Account acc : accounts){



                bw.write(
                acc.getHolderName()
                + ","
                + acc.getAtmNumber()
                + ","
                + acc.getPin()
                + ","
                + acc.getBalance()
                );



                bw.newLine();


            }



            bw.close();


        }
        catch(IOException e){

            System.out.println(
              "Saving failed");

        }

    }







    public boolean login(){


        System.out.print(
        "Enter ATM Number: ");


        long atm =
                sc.nextLong();



        System.out.print(
        "Enter PIN: ");


        int pin =
                sc.nextInt();





        for(Account acc : accounts){



            if(acc.getAtmNumber()==atm
              &&
              acc.getPin()==pin){



                currentAccount = acc;


                System.out.println(
                "Welcome "
                + acc.getHolderName());


                return true;

            }

        }


        System.out.println(
        "Invalid Login");


        return false;

    }


    public void deposit(){



        System.out.print(
        "Amount: ");


        double amount =
                sc.nextDouble();



        currentAccount.setBalance(
        currentAccount.getBalance()
        + amount);



        saveAccounts();



        System.out.println(
        "Deposited Successfully");


    }

    public void withdraw(){

        System.out.print(
        "Amount: ");

        double amount =
                sc.nextDouble();

        if(amount >
        currentAccount.getBalance()){


            System.out.println(
            "Insufficient Balance");

            return;
        }

        currentAccount.setBalance(

        currentAccount.getBalance()
        - amount

        );



        saveAccounts();



        System.out.println(
        "Withdraw Successful");


    }

    public void checkBalance(){


        System.out.println(
        "Balance : "
        + currentAccount.getBalance());


    }


    public void menu(){

    while(true){



            System.out.println(
            "\n1 Balance");

            System.out.println(
            "2 Deposit");

            System.out.println(
            "3 Withdraw");

            System.out.println(
            "4 Logout");



            int choice =
                    sc.nextInt();



            switch(choice){



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

                    return;

            }


        }

    }







    public void start(){



        while(true){


            System.out.println(
            "\n1 Login");

            System.out.println(
            "2 Exit");



            int choice =
                    sc.nextInt();




            switch(choice){


                case 1:

                    if(login())
                        menu();

                    break;



                case 2:

                    saveAccounts();

                    System.out.println(
                    "Thank You");

                    System.exit(0);


            }


        }

    }

    public static void main(String args[]){


        ATMProjectV8 atm =
                new ATMProjectV8();



        atm.start();


    }
}
