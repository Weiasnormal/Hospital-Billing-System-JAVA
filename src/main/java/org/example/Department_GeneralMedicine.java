package org.example;

import java.sql.*;
import java.util.Scanner;

public class Department_GeneralMedicine extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "General Medicine";
        String serviceUsed;
        System.out.println("\033[1;96m" +
            """
            
            
            +=====================================+"""+ "\033[1;33m" + """
            
            ║          Add Services Used          ║
            """ + "\033[1;96m" +"""
            +=====================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
                [1] Consultation
                [2] Blood Test
                [3] Blood Pressure Check
                [4] Back
                \f-------------------------------------\f""" + "\u001B[0m");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Consultation";
                return 1.1;
            case 2:
                serviceUsed = "Blood Test";
                return 1.2;
            case 3:
                serviceUsed = "Blood Pressure Check";
                return 1.3;
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
