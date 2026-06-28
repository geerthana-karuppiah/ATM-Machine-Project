import java.sql.*;
import java.util.Scanner;

public class ATMProjectV9 {

    static Scanner sc =
            new Scanner(System.in);

    static Connection con;

    static long currentATM;

    //------------------------------------------------

    public static void connectDB(){

        try{

            Class.forName(
            "com.mysql.cj.jdbc.Driver"
            );

            con =

            DriverManager.getConnection(

            "jdbc:mysql://localhost:3306/atm_project",

            "root",

            "keerthi@2006"

            );

            System.out.println(
            "Database Connected"
            );

        }

        catch(Exception e){

            System.out.println(e);

        }

    }

    //------------------------------------------------

    public static boolean login(){

        try{

            System.out.print(
            "Enter ATM Number: "
            );

            long atm =
            sc.nextLong();

            System.out.print(
            "Enter PIN: "
            );

            int pin =
            sc.nextInt();

            String sql =

            "SELECT * FROM accounts WHERE atm_number=? AND pin=?";

            PreparedStatement ps =

            con.prepareStatement(sql);

            ps.setLong(1,atm);

            ps.setInt(2,pin);

            ResultSet rs =

            ps.executeQuery();

            if(rs.next()){

                currentATM =
                atm;

                System.out.println(
                "\nLogin Success"
                );

                System.out.println(

                "Welcome "

                +

                rs.getString(
                "holder_name"
                )

                );

                return true;

            }

            else{

                System.out.println(
                "Invalid Login"
                );

                return false;

            }

        }

        catch(Exception e){

            System.out.println(e);

            return false;

        }

    }

    //------------------------------------------------

    public static void showBalance(){

        try{

            String sql =

            "SELECT balance FROM accounts WHERE atm_number=?";

            PreparedStatement ps =

            con.prepareStatement(sql);

            ps.setLong(
            1,
            currentATM
            );

            ResultSet rs =

            ps.executeQuery();

            if(rs.next()){

                System.out.println(

                "\nBalance = "

                +

                rs.getDouble(
                "balance"
                )

                );

            }

        }

        catch(Exception e){

            System.out.println(e);

        }

    }

    //------------------------------------------------

    public static void deposit(){

        try{

            System.out.print(
            "Amount: "
            );

            double amount =
            sc.nextDouble();

            String sql =

            "UPDATE accounts SET balance=balance+? WHERE atm_number=?";

            PreparedStatement ps =

            con.prepareStatement(sql);

            ps.setDouble(
            1,
            amount
            );

            ps.setLong(
            2,
            currentATM
            );

            int rows =

            ps.executeUpdate();

            if(rows>0){

                System.out.println(
                "Deposit Success"
                );

            }

        }

        catch(Exception e){

            System.out.println(e);

        }

    }

    //------------------------------------------------

    public static void withdraw(){

        try{

            showBalance();

            System.out.print(
            "Withdraw Amount: "
            );

            double amount =
            sc.nextDouble();

            String read =

            "SELECT balance FROM accounts WHERE atm_number=?";

            PreparedStatement p1 =

            con.prepareStatement(read);

            p1.setLong(
            1,
            currentATM
            );

            ResultSet rs =

            p1.executeQuery();

            if(rs.next()){

                double bal =

                rs.getDouble(
                "balance"
                );

                if(amount<=bal){

                    String sql =

                    "UPDATE accounts SET balance=balance-? WHERE atm_number=?";

                    PreparedStatement p2 =

                    con.prepareStatement(sql);

                    p2.setDouble(
                    1,
                    amount
                    );

                    p2.setLong(
                    2,
                    currentATM
                    );

                    p2.executeUpdate();

                    System.out.println(
                    "Withdraw Success"
                    );

                }

                else{

                    System.out.println(
                    "Insufficient Balance"
                    );

                }

            }

        }

        catch(Exception e){

            System.out.println(e);

        }

    }

    //------------------------------------------------

    public static void menu(){

        while(true){

            System.out.println(

            "\n1 Balance"

            +

            "\n2 Deposit"

            +

            "\n3 Withdraw"

            +

            "\n4 Exit"

            );

            int choice =
            sc.nextInt();

            switch(choice){

                case 1:

                    showBalance();

                    break;

                case 2:

                    deposit();

                    break;

                case 3:

                    withdraw();

                    break;

                case 4:

                    System.out.println(
                    "Thank You"
                    );

                    return;

                default:

                    System.out.println(
                    "Invalid"
                    );

            }

        }

    }

    //------------------------------------------------

    public static void main(
    String[] args
    ){

        connectDB();

        if(login()){

            menu();

        }

    }

}
