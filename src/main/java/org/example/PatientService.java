package org.example;

import java.util.Scanner;

public class PatientService extends PatientServiceTemplate{
    @Override
    void PatientMain(){
        System.out.println("Patient Main Menu");
        System.out.println("""
                [1] Register New Patient
                [2] View All Patients
                [3] Search Patient
                [4] Back"""); // view all patients is only temporary here for testing
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
                User_Interface.Main_Menu();
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
            User_Interface.Main_Menu(); // will return to main menu if 'input' is 2
        }

        // if the 'input' is 1, the _registerNewPatient() method will continue
        // and ask the user to input the patient information
        User_Interface.ConsoleClear();
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
        User_Interface.ConsoleClear();
        patient.Validation();
        User_Interface.Main_Menu();
    }

    @Override
    void SearchPatient() {
        System.out.print("Search Patient ID : ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Database database = new Database();
        database.GetUserInformation(id);
        User_Interface.Main_Menu();
    }

    @Override
    void ViewAllPatients() {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        database.GetUserInformation();
        System.out.println("Press enter to continue...");
        scanner.nextLine(); // if enter key is pressed, it will continue and execute the next line of code
        User_Interface.Main_Menu();
    }
}