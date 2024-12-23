package org.example;

import java.util.Scanner;

public class Department_Radiology extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Radiology";
        String serviceUsed;
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║          Add Services Used          ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
                [1] X-Ray
                [2] CT Scan
                [3] MRI
                [4] Ultrasound
                [5] Back
                \f-------------------------------------\f""" + "\u001B[0m");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "X-Ray";
                return 3.1;
            case 2:
                serviceUsed = "CT Scan";
                return 3.2;
            case 3:
                serviceUsed = "MRI";
                return 3.3;
            case 4:
                serviceUsed = "Ultrasound";
                return 3.4;
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
