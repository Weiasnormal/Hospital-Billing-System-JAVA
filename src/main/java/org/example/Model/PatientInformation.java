package org.example.Model;
import org.example.DB;
import org.example.UserInterface;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientInformation extends PatientInformationTemplate {
    @Override
    public void PatientMain(){
        System.out.println("\033[1;96m" +
                """
                
                
                +======================================+"""+ "\033[1;33m" + """
                
                ║    Manage Patient Medical Records    ║
                """ + "\033[1;96m" +"""
                +======================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
        Please select an option:
        [1] Register a Patient
        [2] View Patient List
        [3] Search for a Patient
        [4] Return to Main Menu""" + "\u001B[0m"); // view all patients is only temporary here for testing
        System.out.println("\f--------------------------------------\f");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch(input){
            case "1":
                RegisterNewPatient();
            case "2":
                ViewAllPatients();
            case "3":
                SearchPatient();
            case "4":
                UserInterface.MainMenu();
            default:
                PatientMain();
        }
    }

    @Override
    public void RegisterNewPatient() {
        System.out.println( "\n" + "\033[1;93m" + "⫍⫍⫍⫍" + "\033[1;97m" +"   Register a Patient   " + "\033[1;93m" + "⫎⫎⫎⫎" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
        [1] Continue
        [2] Back""" + "\u001B[0m");
        System.out.println("\f-------------------------------------\f");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(!(input.equals("1") || input.equals("2"))) {
            RegisterNewPatient(); // serves as a error handler
        }
        if(input.equals("2")){
            PatientMain(); // will return to main menu if 'input' is 2
        }

        // if the 'input' is 1, the _registerNewPatient() method will continue
        // and ask the user to input the patient information
        UserInterface.ConsoleClear();
        NewPatient();
    }

    private void NewPatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║      Input Patient Information      ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");

        try {
            DB db = new DB();
            System.out.print("Patient ID: ");
            int ID = scanner.nextInt();
            if(db.PatientIDExists(ID)){
                System.out.print("Patient ID Already Exists ");
                NewPatient();
            }
            scanner.nextLine(); // to prevent skipping the next ".nextLine()"
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // to prevent skipping the next ".nextLine()"
            System.out.print("Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Contact Number: ");
            String contact_number = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();

            Patient patient = new Patient(ID, name, age, gender, contact_number, address);
            UserInterface.ConsoleClear();
            boolean isValid = patient.Validation();
            db.InsertToDatabase(isValid, ID, name, age, gender, contact_number, address);
            UserInterface.MainMenu();
        }
        catch (InputMismatchException e){
            System.out.println("Input invalid, try again.");
            PatientMain();
        }
    }

    @Override
    public void SearchPatient() {
        try {
            System.out.println("\033[1;96m" + """
                +======================================+"""+ "\033[1;33m" + """
                
                ║      Search Patient Information      ║
                """ + "\033[1;96m" +"""
                +======================================+""" + "\u001B[0m");
            System.out.print("Enter Patient ID: ");
            Scanner scanner = new Scanner(System.in);
            String id = scanner.nextLine();

            if(id == null || Integer.parseInt(id) < 1) {
                System.out.println("Input invalid, please enter a numeric ID.");
                SearchPatient();
            }

            DB db = new DB();
            db.GetUserInformation(Integer.parseInt(id));
            PatientMain();
        }
        catch (InputMismatchException e){
            System.out.println("Input invalid, please enter a numeric ID.");
            SearchPatient();
        }
        catch (NumberFormatException e){
            System.out.println("Input invalid, please enter a numeric ID.");
            SearchPatient();
        }
    }

    @Override
    public void ViewAllPatients() {
        DB db = new DB();
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Patient List ===");
        db.GetUserInformation();
        System.out.println("Press enter to continue...");
        scanner.nextLine(); // if enter key is pressed, it will continue and execute the next line of code
        PatientMain();
    }
}
