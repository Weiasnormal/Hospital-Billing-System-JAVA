package org.example;
import java.util.Scanner;

public class Department_Pediatrics extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Pediatrics";
        String serviceUsed;
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║          Add Services Used          ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
                [1] Pediatric Consultation
                [2] Immunization (per vaccine)
                [3] Growth Check-Up
                [4] Back
                \f-------------------------------------\f""" + "\u001B[0m");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Pediatric Consultation";
                return 8.1;
            case 2:
                serviceUsed = "Immunization (per vaccine)";
                return 8.2;
            case 3:
                serviceUsed = "Growth Check-Up";
                return 8.3;
            case 4:
                PatientDetails patientDetails = new PatientDetails();
                patientDetails.AddDepartmentAndServices();
            default:
                System.out.println("\n\nPlease enter a valid input!");
                addServices();
                break;
        }
        return 0;
    }
}
