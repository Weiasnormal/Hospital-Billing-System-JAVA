package org.example.Model;
import org.example.DB;
import org.example.Database;
import org.example.UserInterface;

import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.ResultSet;
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
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║      Input Patient Information      ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");

        try {
            System.out.print("Patient ID: ");
            int ID = scanner.nextInt();
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
            boolean validate = patient.Validation();
            DB db = new DB();
            db.InsertToDatabase(validate, ID, name, age, gender, contact_number, address);
            UserInterface.MainMenu();
        }
        catch (InputMismatchException e){}
    }

    @Override
    public void SearchPatient() {
        System.out.println("\033[1;96m" +
                """
                
                
                +======================================+"""+ "\033[1;33m" + """
                
                ║      Search Patient Information      ║
                """ + "\033[1;96m" +"""
                +======================================+""" + "\u001B[0m");
        System.out.print("Input Patient ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();


        DB db = new DB();
        db.GetUserInformation(id);
        UserInterface.MainMenu();
    }

    @Override
    public void ViewAllPatients() {
        DB db = new DB();
        Scanner scanner = new Scanner(System.in);
        db.GetUserInformation();
        System.out.println("Press enter to continue...");
        scanner.nextLine(); // if enter key is pressed, it will continue and execute the next line of code
        UserInterface.MainMenu();
    }
}
