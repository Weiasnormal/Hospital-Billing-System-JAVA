package org.example;

import java.util.Scanner;

public class Department_Orthopedics extends ServicesUsed{
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String titleColor = "\033[1;93m";
    public static String titleborderColor = "\033[1;96m";

    @Override
    public double addServices() {
        String departmentName = "Orthopedics";
        String serviceUsed;
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+"""+ titleColor + """
                
                ║          Add Services Used          ║
                """ + titleborderColor +"""
                +=====================================+""");
        System.out.println(wColor + """
                [1] Orthopedic Consultation
                [2] Bone X-Ray
                [3] Fracture Treatment
                [4] Physiotherapy Session
                [5] Back
                \f-------------------------------------\f""");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Orthopedic Consultation";
                return 4.1;
            case 2:
                serviceUsed = "Bone X-Ray";
                return 4.2;
            case 3:
                serviceUsed = "Fracture Treatment";
                return 4.3;
            case 4:
                serviceUsed = "Physiotherapy Session";
                return 4.4;
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