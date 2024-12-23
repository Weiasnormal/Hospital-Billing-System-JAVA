package org.example;

import org.example.Model.PatientInformation;

import java.util.Scanner;

public class UserInterface {
    public static void ConsoleClear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor(); // Substitute for Runtime.exec()
            }
        } catch (Exception e) {
            // Ignoring exceptions for ConsoleClear
        }
    }

    public static void MainMenu() {
        try {
            ConsoleClear();

            System.out.println(
                    "\033[1;96m" +
                            """
                            
                            +-------------------------------------------------------------+
                            |""" + "\033[0;33m" + """
                            ╦ ╦╔═╗╔═╗╔═╗╦╔╦╗╔═╗╦    ╔╗ ╦╦  ╦  ╦╔╗╔╔═╗  ╔═╗╦ ╦╔═╗╔╦╗╔═╗╔╦╗""" + "\033[1;96m" + """
                            |
                            |""" + "\033[0;33m" + """
                            ╠═╣║ ║╚═╗╠═╝║ ║ ╠═╣║    ╠╩╗║║  ║  ║║║║║ ╦  ╚═╗╚╦╝╚═╗ ║ ║╣ ║║║""" + "\033[1;96m" + """
                            |
                            |""" + "\033[0;33m" + """
                            ╩ ╩╚═╝╚═╝╩  ╩ ╩ ╩ ╩╩═╝  ╚═╝╩╩═╝╩═╝╩╝╚╝╚═╝  ╚═╝ ╩ ╚═╝ ╩ ╚═╝╩ ╩""" + "\033[1;96m" + """
                            |
                            +-------------------------------------------------------------+"""
                            + "\u001B[0m"); // not final UI
            System.out.println(
                    "\033[1;97m" +
                            """
                            [1] Manage Patient Records
                            [2] Manage Patient Details
                            [3] Exit the System""" + "\u001B[0m"
            );
            System.out.println("\f--------------------------------------\f");
            System.out.print("⪀⫸ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            ConsoleClear();
            PatientInformation patient_service = new PatientInformation();
            switch (input) {
                case "1":
                    patient_service.PatientMain();
                    break;
                case "2":
                    PatientDetails patient_details = new PatientDetails();
                    patient_details.DeptServiceMain();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\nPlease enter a valid input!");
                    MainMenu(); // Go back to MainMenu on invalid input
                    break;
            }
        } catch (Exception e) {
            // Any exception redirects back to MainMenu
            MainMenu();
        }
    }
}
