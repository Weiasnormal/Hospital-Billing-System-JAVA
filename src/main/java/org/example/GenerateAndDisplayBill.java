package org.example;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GenerateAndDisplayBill {
    private int patientID;
    private static double total;

    public void Main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Generate and Display Bill ===");
        int patientID = -1;


            try {
                System.out.println("Enter Patient ID");
                patientID = sc.nextInt();
                sc.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
                System.out.println("\033[1;31mInvalid input. Please enter a valid numeric Patient ID.\033[0m");
                sc.nextLine(); // Clear invalid input
            }

        DisplayExpenses(patientID);
    }

    public void DisplayExpenses(int patientID) {
        DB db = new DB();
        total = db.CheckBalance(patientID);
        if(total < 0){
            total = 0;
        }
        PatientDetails patientDetails = new PatientDetails();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║          Bill and Payment           ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");

                System.out.println("\033[1;97m-------------------------------------");
                System.out.println("[Patient Information]");
                System.out.println("ID   : " + patientID);
                System.out.println("Name : " + db.GetName(patientID));
                System.out.println("Total Expenses : " + total);
                System.out.println("\033[1;97m" + """
                    \f-------------------------------------\f
                    Please select an option:
                    [1] View Services Expenses
                    [2] View Medicine Expenses
                    [3] Finalize expenses 
                    [4] Process Payment 
                    [5] Go back to adding patient information
                    [6] Exit to Main Menu
                    \f-------------------------------------\f""" + "\u001B[0m");
                System.out.print("⪀⫸ ");

                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        db.FetchServices(patientID);
                        System.out.println("\033[1;97m-------------------------------------");
                        System.out.println("[Patient Information]");
                        System.out.println("ID   : " + patientID);
                        System.out.println("Name : " + db.GetName(patientID));
                        System.out.println("Total Expenses : " + total);
                        System.out.println("\033[1;97m" + """
                    \f-------------------------------------\f""" + "\u001B[0m");
                        break; // Prevent fall-through
                    case 2:
                        db.FetchMedicine(patientID); // Corrected to fetch medicine expenses
                        System.out.println("\033[1;97m-------------------------------------");
                        System.out.println("[Patient Information]");
                        System.out.println("ID   : " + patientID);
                        System.out.println("Name : " + db.GetName(patientID));
                        System.out.println("Total Expenses : " + total);
                        System.out.println("\033[1;97m" + """
                    \f-------------------------------------\f""" + "\u001B[0m");
                        break; // Prevent fall-through
                    case 3:
                        db.FetchServices(patientID);
                        db.FetchMedicine(patientID);
                        db.Billing(patientID);
                        DisplayBill(patientID);
                    case 4:
                        PaymentBill(patientID);
                        return; // Exit method after payment
                    case 5:
                        patientDetails.DeptServiceMain();
                        return; // Exit method after going back
                    case 6:
                        UserInterface.MainMenu();
                        return; // Exit method to return to main menu
                    default:
                        System.out.println("\033[1;31mInvalid option. Please choose a valid option.\033[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[1;31mInvalid input. Please enter a numeric option.\033[0m");
                sc.nextLine(); // Clear invalid input
            }
        }
    }





    public static void DisplayBill(int patientID) {
        DB db = new DB();
        PatientDetails patient = new PatientDetails();
        Scanner sc = new Scanner(System.in);

        total = db.CheckBill(patientID); // Call the method with a patient ID
        if (total != -1) {
            System.out.println("Total expenses: " + total);

            while (true) {
                try {
                    System.out.println("Is the Patient Paying now? (Y/N)");
                    System.out.print("⪀⫸ ");
                    String choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("Y")) {
                        PaymentBill(patientID);
                        break;
                    } else if (choice.equalsIgnoreCase("N")) {
                        patient.DeptServiceMain();
                        break;
                    } else {
                        System.out.println("\033[1;31mInvalid Choice. Please enter 'Y' or 'N'.\033[0m");
                    }
                } catch (Exception e) {
                    System.out.println("\033[1;31mAn error occurred. Please try again.\033[0m");
                }
            }
        } else {
            System.out.println("Failed to calculate or no expenses found.");
        }

        UserInterface.MainMenu();
    }

    public static void PaymentBill(int selectedId) {
        DB db = new DB();
        Scanner sc = new Scanner(System.in);
        total = db.CheckBill(selectedId);
        PatientDetails patientDetails = new PatientDetails();
        if (total <= -1) {
            System.out.println("No record found for Patient ID: " + selectedId);
            patientDetails.DeptServiceMain();
        }else if(total == 0){
            System.out.println("Patient: " + selectedId + " has no balance");
            patientDetails.DeptServiceMain();
        }


        System.out.println("\n\n");
        System.out.println("--------------------------------------");
        System.out.println("[Patient Information]");
        System.out.println("ID   : " + selectedId);
        System.out.println("Name : " + db.GetName(selectedId));
        System.out.println("Total Expenses : " + total);
        System.out.println("--------------------------------------");

        double amount = 0;
        if (total <= -1) {
            System.out.println("No record found for Patient ID: " + selectedId);
            patientDetails.DeptServiceMain();
        }else if(total == 0){
            System.out.println("Patient: " + selectedId + " has no balance");
            patientDetails.DeptServiceMain();
        }else {
            while (true) {
                try {
                    System.out.println("Enter Payment Amount");
                    System.out.print("⪀⫸ ");
                    amount = sc.nextDouble();
                    sc.nextLine(); // Consume the newline
                    if (amount > 0) {
                        break;
                    } else {
                        System.out.println("\033[1;31mPlease enter a positive amount.\033[0m");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\033[1;31mInvalid input. Please enter a valid numeric amount.\033[0m");
                    sc.nextLine(); // Clear invalid input
                }
            }
        }
        db.FinalBill(selectedId, amount, total);
        UserInterface.MainMenu();
    }

}
