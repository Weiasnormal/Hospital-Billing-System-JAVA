package org.example;

import org.example.Model.PatientInformation;

import java.util.Scanner;

public class UserInterface {
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String successColor = "\033[0;92m";
    public static String loadingColor = "\033[0;37m";
    public static String titleborderColor = "\033[1;96m";
    public static String titleColor = "\033[1;93m";

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
                    titleborderColor +
                            """
                            
                            +-------------------------------------------------------------+
                            |""" + titleColor + """
                            ╦ ╦╔═╗╔═╗╔═╗╦╔╦╗╔═╗╦    ╔╗ ╦╦  ╦  ╦╔╗╔╔═╗  ╔═╗╦ ╦╔═╗╔╦╗╔═╗╔╦╗""" + titleborderColor + """
                            |
                            |""" + titleColor + """
                            ╠═╣║ ║╚═╗╠═╝║ ║ ╠═╣║    ╠╩╗║║  ║  ║║║║║ ╦  ╚═╗╚╦╝╚═╗ ║ ║╣ ║║║""" + titleborderColor + """
                            |
                            |""" + titleColor + """
                            ╩ ╩╚═╝╚═╝╩  ╩ ╩ ╩ ╩╩═╝  ╚═╝╩╩═╝╩═╝╩╝╚╝╚═╝  ╚═╝ ╩ ╚═╝ ╩ ╚═╝╩ ╩""" + titleborderColor + """
                            |
                            +-------------------------------------------------------------+"""
            );
            System.out.println(
                    wColor +
                            """
                            [1] Manage Patient Records
                            [2] Manage Patient Details
                            [3] Exit the System""");
            System.out.println("\f-------------------------------------------------------------\f");
            System.out.print("⪀⫸ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            System.out.println();
            System.out.println(loadingColor + """                       
                        ········································
                        Please wait a moment...
                        ········································""");
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
                    System.out.println(errorColor + "\n\nPlease enter a valid input!");
                    MainMenu(); // Go back to MainMenu on invalid input
                    break;
            }
        } catch (Exception e) {
            // Any exception redirects back to MainMenu
            MainMenu();
        }
    }
}
