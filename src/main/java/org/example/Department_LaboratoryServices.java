package org.example;

import java.util.Scanner;

public class Department_LaboratoryServices extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Laboratory Services";
        String serviceUsed;
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║          Add Services Used          ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
                [1] Complete Blood Count (CBC)
                [2] Urine Analysis
                [3] Cholesterol Test
                [4] COVID-19 Test
                [5] Back
                \f-------------------------------------\f""" + "\u001B[0m");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Complete Blood Count (CBC)";
                return 6.1;
            case 2:
                serviceUsed = "Urine Analysis";
                return 6.2;
            case 3:
                serviceUsed = "Cholesterol Test";
                return 6.3;
            case 4:
                serviceUsed = "COVID-19 Test";
                return 6.4;
            case 5:
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