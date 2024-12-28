package org.example.Model;
import org.example.DB;
import org.example.UserInterface;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientInformation extends PatientInformationTemplate {
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String loadingColor = "\033[0;37m";
    public static String titleborderColor = "\033[1;96m";
    public static String titleColor = "\033[1;93m";

    @Override
    public void PatientMain(){
        System.out.println(titleborderColor +
                """
                
                
                +======================================+"""+ titleColor + """
                
                ║        Manage Patient Records        ║
                """ + titleborderColor +"""
                +======================================+""");
        System.out.println(wColor + """
        Please select an option:
        [1] Register a Patient
        [2] View Patient List
        [3] Search for a Patient
        [4] Return to Main Menu"""); // view all patients is only temporary here for testing
        System.out.println("\f--------------------------------------\f");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch(input){
            case "1":
                RegisterNewPatient();
            case "2":
                System.out.println();
                System.out.println(loadingColor + """                       
                        ·········································
                        Please wait a moment for the Patient List
                        ·········································""");
                ViewAllPatients();
            case "3":
                SearchPatient();
            case "4":
                UserInterface.MainMenu();
            default:
                System.out.println(errorColor + "\n\nPlease enter a valid input...");
                PatientMain();
        }
    }

    @Override
    public void RegisterNewPatient() {
        System.out.println( "\n" + titleColor + "⫍⫍⫍⫍" + wColor +"   Register a Patient   " + titleColor + "⫎⫎⫎⫎");
        System.out.println(wColor + """
        [1] Continue
        [2] Back""");
        System.out.println("\f-------------------------------------\f");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(!(input.equals("1") || input.equals("2"))) {
            System.out.println(errorColor + "\nPlease enter a valid input...");
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
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+"""+ titleColor + """
                
                ║      Input Patient Information      ║
                """ + titleborderColor +"""
                +=====================================+""");
        System.out.println();
        System.out.println(loadingColor + """                       
                        ·······································
                        Please Wait. Loading the form...
                        ·······································\n""");
        try {
            DB db = new DB();
            System.out.print(wColor + "Patient ID: ");
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
            System.out.println(loadingColor + "\nProcessing...");
            boolean isValid = patient.Validation();
            db.InsertToDatabase(isValid, ID, name, age, gender, contact_number, address);
            UserInterface.MainMenu();
        }
        catch (InputMismatchException e){
            System.out.println(errorColor + "Input invalid, try again.");
            PatientMain();
        }
    }

    @Override
    public void SearchPatient() {
        String error = errorColor + "\nInput invalid, please enter a numeric ID.";
        try {
            System.out.println(titleborderColor +
                """
                
                
                +======================================+"""+ titleColor + """
                
                ║      Search Patient Information      ║
                """ + titleborderColor +"""
                +======================================+""");
            System.out.print(wColor + "Enter Patient ID: ");
            Scanner scanner = new Scanner(System.in);
            String id = scanner.nextLine();


            if(id == null || Integer.parseInt(id) < 1) {
                System.out.println(error);
                SearchPatient();
            }
            System.out.println();
            System.out.println(loadingColor + """                       
                        ········································
                        Please Wait. Searching for patient...
                        ········································\n""");
            DB db = new DB();

            db.GetUserInformation(Integer.parseInt(id));
            System.out.println(loadingColor + "Press enter to continue...");
            scanner.nextLine();
            PatientMain();
        }
        catch (InputMismatchException e){
            System.out.println(error);
            SearchPatient();
        }
        catch (NumberFormatException e){
            System.out.println(error);
            SearchPatient();
        }
    }

    @Override
    public void ViewAllPatients() {
        DB db = new DB();
        Scanner scanner = new Scanner(System.in);

        System.out.println(titleborderColor +
                """
                
                
                +======================================+"""+ titleColor + """
                
                ║             Patient List             ║
                """ + titleborderColor +"""
                +======================================+""");

        db.GetUserInformation();
        System.out.println(loadingColor + "Press enter to continue...");
        scanner.nextLine(); // if enter key is pressed, it will continue and execute the next line of code
        PatientMain();
    }
}
