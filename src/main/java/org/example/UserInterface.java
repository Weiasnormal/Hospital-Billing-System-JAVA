package org.example;

import org.example.Model.PatientInformation;

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

        System.out.println(
                "\033[1;96m" +
                        """
                        
                        +-------------------------------------------------------------+
                        |"""+ "\033[0;33m" + """ 
                        ╦ ╦╔═╗╔═╗╔═╗╦╔╦╗╔═╗╦    ╔╗ ╦╦  ╦  ╦╔╗╔╔═╗  ╔═╗╦ ╦╔═╗╔╦╗╔═╗╔╦╗""" + "\033[1;96m" +"""
                        |
                        |"""+ "\033[0;33m" + """
                        ╠═╣║ ║╚═╗╠═╝║ ║ ╠═╣║    ╠╩╗║║  ║  ║║║║║ ╦  ╚═╗╚╦╝╚═╗ ║ ║╣ ║║║"""+ "\033[1;96m" +"""
                        |
                        |"""+ "\033[0;33m" + """
                        ╩ ╩╚═╝╚═╝╩  ╩ ╩ ╩ ╩╩═╝  ╚═╝╩╩═╝╩═╝╩╝╚╝╚═╝  ╚═╝ ╩ ╚═╝ ╩ ╚═╝╩ ╩""" + "\033[1;96m" +"""
                        |
                        +-------------------------------------------------------------+"""
        + "\u001B[0m"); // not final UI
        System.out.println(
                 "\033[1;97m" +
                        """
                        [1] Manage Patient Records
                        [2] Add Patient Details
                        [3] Generate and Display the Bill
                        [4] Process a Payment
                        [5] Exit the System""" + "\u001B[0m"
                );
        System.out.println("\f--------------------------------------\f");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        ConsoleClear();
        PatientInformation patient_service = new PatientInformation();
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
               GenerateAndDisplayBill bill = new GenerateAndDisplayBill();
               bill.Main();
                break;
            case "4":
                GenerateAndDisplayBill payment = new GenerateAndDisplayBill();
                payment.PaymentBill();
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
