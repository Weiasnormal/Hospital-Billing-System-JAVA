package org.example;

import java.util.Scanner;

public class User_Interface {
    static void _consoleClear(){
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
        _consoleClear();
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
        String input = scanner.nextLine();
        switch(input){
            case "1":
                //go to register new patient
                _registerNewPatient();
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

    private static void _registerNewPatient(){
        System.out.println("Register New Patient");
        System.out.println("""
                [1] Continue
                [2] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(!(input.equals("1") || input.equals("2"))) {
            _registerNewPatient(); // serves as a error handler
        }
        if(input.equals("2")){
            Main_Menu(); // will return to main menu if 'input' is 2
        }

        // if the 'input' is 1, the _registerNewPatient() method will continue
        // and ask the user to input the patient information
        System.out.println("[INPUT]");
        System.out.print("Name : ");
        String name = scanner.nextLine();
        System.out.print("Age : ");
        int age = scanner.nextInt();
        scanner.nextLine(); // to prevent skipping the next ".nextLine()"
        System.out.print("Gender : ");
        String gender = scanner.nextLine();
        System.out.print("Contact Number : ");
        int contact_number = scanner.nextInt();
        scanner.nextLine(); // to prevent skipping the next ".nextLine()"

        Patient patient = new Patient(name, age, gender, contact_number);
        patient.Validation();
        _consoleClear();
        Main_Menu();
    }
}
