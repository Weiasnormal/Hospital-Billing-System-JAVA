package org.example;

import java.util.Scanner;

public class User_Interface {
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

    public static void Main_Menu(){
        ConsoleClear();
        System.out.println("--Welcome to Hospital Billing System--"); // example UI
        System.out.println(
                     """
                        [1] Register a new patient
                        [2] Add Services / Departments Visited
                        [3] Generate and Display Bill
                        [4] Process Payment
                        [5] Exit"""
                );
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        PatientService patient_service = new PatientService();
        String input = scanner.nextLine();
        switch(input){
            case "0":
                // this is temporary only to show all patients
                ConsoleClear();
                patient_service.ViewAllPatients();
                break;
            case "1":
                //go to register new patient
                ConsoleClear();
                patient_service.PatientMain();
                break;
            case "2":
                //add services and departments visited
                System.out.println("adding services and departments visited"); //temporary
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
                Main_Menu();
                break;
        }
    }
}
