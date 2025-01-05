package org.example;
import java.util.Scanner;

public class Department_Dental extends ServicesUsed {
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String titleColor = "\033[1;93m";
    public static String titleborderColor = "\033[1;96m";

    @Override
    public double addServices() {
        String serviceUsed;
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+"""+ titleColor + """
                
                ║          Add Services Used          ║
                """ + titleborderColor +"""
                +=====================================+""");
        System.out.println(wColor + """
                [1] Dental Check-Up
                [2] Tooth Extraction
                [3] Teeth Cleaning
                [4] Root Canal Treatment
                [5] Back
                \f-------------------------------------\f""");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Dental Check-Up";
                return 10.1;
            case 2:
                serviceUsed = "Tooth Extraction";
                return 10.2;
            case 3:
                serviceUsed = "Teeth Cleaning";
                return 10.3;
            case 4:
                serviceUsed = "Root Canal Treatment";
                return 10.4;
            case 5:
                PatientDetails patientDetails = new PatientDetails();
                patientDetails.AddDepartmentAndServices();
            default:
                System.out.println(errorColor + "\n\nPlease enter a valid input!");
                addServices();
                break;
        }
        return 0;
    }
}
