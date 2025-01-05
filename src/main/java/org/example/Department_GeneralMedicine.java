package org.example;

import java.sql.*;
import java.util.Scanner;

public class Department_GeneralMedicine extends ServicesUsed{
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
                [1] Consultation
                [2] Blood Test
                [3] Blood Pressure Check
                [4] Back
                \f-------------------------------------\f""");
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
                System.out.println(errorColor + "\n\nPlease enter a valid input!");
                addServices();
                break;
        }
        return 0;
    }

}
