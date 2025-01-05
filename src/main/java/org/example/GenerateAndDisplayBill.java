package org.example;

import org.example.Interfaces.BillingOperations;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GenerateAndDisplayBill implements BillingOperations {

    private static double total;
    private static double totalBalance;
    private int patientID;
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String titleColor = "\033[1;93m";
    public static String titleborderColor = "\033[1;96m";
    // Getter for total
    public double getBalance() {
        return totalBalance;
    }

    // Setter for total
    public void setBalance(double total) {
        GenerateAndDisplayBill.totalBalance = total;
    }

    public double getTotal() {
        return total;
    }

    // Setter for total
    public void setTotal(double total) {
        GenerateAndDisplayBill.total = total;
    }

    // Getter for patientID
    public int getPatientID() {
        return patientID;
    }

    // Setter for patientID
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void Main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Generate and Display Bill ===");

        try {
            System.out.println(wColor + "Enter Patient ID");
            setPatientID(sc.nextInt());
            sc.nextLine(); // Consume the newline
        } catch (InputMismatchException e) {
            System.out.println(errorColor + "Invalid input. Please enter a valid numeric Patient ID.\033[0m");
            sc.nextLine(); // Clear invalid input
        }

        DisplayExpenses(getPatientID());
    }

    @Override
    public void DisplayExpenses(int patientID) {
        DB db = new DB();
        db.SetSessionTimer();
        setTotal(db.CheckTotalExpenses(patientID));
        if (getTotal() < 0) {
            setTotal(0);
        }

        setBalance(db.CheckBalance(patientID));
        if (getBalance() < 0) {
            setBalance(0);
        }
        PatientDetails patientDetails = new PatientDetails();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println(titleborderColor +
                        """
                
                
                +=====================================+""" + titleColor + """
                
                ║          Bill and Payment           ║
                """ + titleborderColor + """
                +=====================================+""" + "\u001B[0m");

                System.out.println( wColor + "---------------------------------------");
                System.out.println("[Patient Information]");
                System.out.println("ID   : " + patientID);
                System.out.println("Name : " + db.GetName(patientID));
                System.out.println("Total Expenses : " + getTotal());
                System.out.println("Total Balance : " + getBalance());
                System.out.println(wColor + """
                    \f-------------------------------------\f
                    Please select an option:
                    [1] View Services Expenses
                    [2] View Medicine Expenses
                    [3] Finalize expenses 
                    [4] Process Payment 
                    [5] Go back to adding patient information
                    [6] Exit to Main Menu
                    \f-------------------------------------\f""");
                System.out.print("⪀⫸ ");

                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        db.FetchServices(patientID);
                        break;
                    case 2:
                        db.FetchMedicine(patientID);
                        break;
                    case 3:
                        db.FetchServices(patientID);
                        db.FetchMedicine(patientID);
                        db.Billing(patientID);
                        DisplayBill(patientID);
                        break;
                    case 4:
                        PaymentBill(patientID);
                        return;
                    case 5:
                        patientDetails.DeptServiceMain();
                        return;
                    case 6:
                        UserInterface.MainMenu();
                        return;
                    default:
                        System.out.println(errorColor + "Invalid option. Please choose a valid option.\033[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println(errorColor + "Invalid input. Please enter a numeric option.\033[0m");
                sc.nextLine(); // Clear invalid input
            }
        }
    }

    @Override
    public void DisplayBill(int patientID) {
        DB db = new DB();
        db.SetSessionTimer();
        Scanner sc = new Scanner(System.in);

        setTotal(db.CheckBill(patientID)); // Call the method with a patient ID
        if (getTotal() != -1) {

            while (true) {
                try {
                    setTotal(db.CheckTotalExpenses(patientID));
                    if (getTotal() < 0) {
                        setTotal(0);
                    }

                    setBalance(db.CheckBalance(patientID));
                    if (getBalance() < 0) {
                        setBalance(0);
                    }
                    System.out.println(wColor + "Is the Patient Paying now? (Y/N)");
                    System.out.print("⪀⫸ ");
                    String choice = sc.nextLine();
                    if (choice.equalsIgnoreCase("Y")) {
                        PaymentBill(patientID);
                        break;
                    } else if (choice.equalsIgnoreCase("N")) {
                        DisplayExpenses(patientID);
                        break;
                    } else {
                        System.out.println(errorColor + "Invalid Choice. Please enter 'Y' or 'N'.\033[0m");
                        DisplayBill(patientID);
                    }
                } catch (Exception e) {
                    System.out.println(errorColor + "An error occurred. Please try again.\033[0m");
                }
            }
        } else {
            System.out.println(errorColor + "Failed to calculate or no expenses found.");
        }

        UserInterface.MainMenu();
    }

    @Override
    public void PaymentBill(int patientID) {
        DB db = new DB();
        db.SetSessionTimer();
        Scanner sc = new Scanner(System.in);
        setTotal(db.CheckBill(patientID));
        PatientDetails patientDetails = new PatientDetails();

        if (getBalance() <= -1) {
            System.out.println(wColor + "No record found for Patient ID: " + patientID);
            patientDetails.DeptServiceMain();
            return;
        } else if (getBalance() == 0) {
            System.out.println(wColor + "Patient: " + patientID + " has no balance");
            patientDetails.DeptServiceMain();
            return;
        }

        System.out.println("\n\n");
        System.out.println(wColor + "--------------------------------------");
        System.out.println("[Patient Information]");
        System.out.println("ID   : " + patientID);
        System.out.println("Name : " + db.GetName(patientID));
        System.out.println("Total Expenses : " + getTotal());
        System.out.println("Outstanding Balance : " + getBalance());
        System.out.println("--------------------------------------");

        double amount = 0;

        while (true) {
            try {
                System.out.println(wColor + "Enter Payment Amount");
                System.out.print("⪀⫸ ");
                amount = sc.nextDouble();
                sc.nextLine(); // Consume the newline
                if (amount > 0) {
                    break;
                } else {
                    System.out.println(errorColor + "Please enter a positive amount.\033[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println(errorColor + "Invalid input. Please enter a valid numeric amount.\033[0m");
                sc.nextLine(); // Clear invalid input
            }
        }

        db.FinalBill(patientID, amount, getTotal());
        UserInterface.MainMenu();
    }
}
