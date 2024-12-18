package org.example;

import java.util.Scanner;

public class UserInterface {
    public static void ConsoleClear(){
        // due to not having a built-in function for clearing the entire text in the console,
        // a function/method equivalent to Console.Clear() is made
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor(); // Substitute for Runtime.exec()
            }
        } catch (Exception e) {
            // Handle exceptions gracefully
        }
    }

    public static void MainMenu(){
        ConsoleClear();
        System.out.println("=== Welcome to Hospital Billing System ==="); // not final UI
        System.out.println(
                     """
                        [1] Manage Patient Records
                        [2] Add Patient Details
                        [3] Generate and Display the Bill
                        [4] Process a Payment
                        [5] Exit the System"""
                );
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ConsoleClear();
        PatientRecords patient_service = new PatientRecords();
        switch(input){
            case "0":
                // this is temporary only to show all patients
                patient_service.ViewAllPatients();
                break;
            case "1":
                //go to register new patient
                patient_service.PatientMain();
                break;
            case "2":
                //add services and departments visited
                PatientDetails patient_details = new PatientDetails();
                patient_details.DeptServiceMain();
                break;
            case "3":
                // generate and display bill
                System.out.println("Generating and Displaying Bill..."); //temporary
                break;
            case "4":
                // process payment
                System.out.println("Processing Payment..."); //temporary
                break;
            case "5":
                // exit program
                System.exit(0);
                break;
            default:
                MainMenu();
                break;
        }
    }
}
