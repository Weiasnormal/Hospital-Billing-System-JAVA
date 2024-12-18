package org.example;

import java.util.Scanner;

public class PatientRecords extends PatientRecordsTemplate {
    @Override
    void PatientMain(){
        System.out.println("=== Manage Patient Records ===");
        System.out.println("""
        Please select an option:
        [1] Register a Patient
        [2] View Patient List
        [3] Search for a Patient
        [4] Return to Main Menu
        """); // view all patients is only temporary here for testing
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch(input){
            case "1":
                RegisterNewPatient();
                break;
            case "2":
                ViewAllPatients();
                break;
            case "3":
                SearchPatient();
                break;
            case "4":
                UserInterface.MainMenu();
                break;
        }
    }

    @Override
    void RegisterNewPatient() {
        System.out.println("Register New Patient");
        System.out.println("""
                [1] Continue
                [2] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(!(input.equals("1") || input.equals("2"))) {
            RegisterNewPatient(); // serves as a error handler
        }
        if(input.equals("2")){
            UserInterface.MainMenu(); // will return to main menu if 'input' is 2
        }

        // if the 'input' is 1, the _registerNewPatient() method will continue
        // and ask the user to input the patient information
        UserInterface.ConsoleClear();
        System.out.println("[INPUT]");
        System.out.print("Name : ");
        String name = scanner.nextLine();
        System.out.print("Age : ");
        int age = scanner.nextInt();
        scanner.nextLine(); // to prevent skipping the next ".nextLine()"
        System.out.print("Gender : ");
        String gender = scanner.nextLine();
        System.out.print("Contact Number : ");
        String contact_number = scanner.nextLine();

        Patient patient = new Patient(name, age, gender, contact_number);
        UserInterface.ConsoleClear();
        patient.Validation();
        UserInterface.MainMenu();
    }

    @Override
    void SearchPatient() {
        System.out.print("Search Patient ID : ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Database database = new Database();
        database.GetUserInformation(id);
        UserInterface.MainMenu();
    }

    @Override
    void ViewAllPatients() {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        database.GetUserInformation();
        System.out.println("Press enter to continue...");
        scanner.nextLine(); // if enter key is pressed, it will continue and execute the next line of code
        UserInterface.MainMenu();
    }
}
