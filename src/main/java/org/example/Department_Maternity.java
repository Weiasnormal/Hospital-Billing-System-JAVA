package org.example;
import java.util.Scanner;

public class Department_Maternity extends ServicesUsed{
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String titleColor = "\033[1;93m";
    public static String titleborderColor = "\033[1;96m";

    @Override
    public double addServices() {
        String departmentName = "Maternity";
        String serviceUsed;
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+"""+ titleColor + """
                
                ║          Add Services Used          ║
                """ + titleborderColor +"""
                +=====================================+""");
        System.out.println(wColor + """
                [1] Prenatal Check-Up
                [2] Ultrasound (Pregnancy)
                [3] Delivery (Normal)
                [4] Delivery (Cesarean)
                [5] Back
                \f-------------------------------------\f""");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Prenatal Check-Up";
                return 9.1;
            case 2:
                serviceUsed = "Ultrasound (Pregnancy)";
                return 9.2;
            case 3:
                serviceUsed = "Delivery (Normal)";
                return 9.3;
            case 4:
                serviceUsed = "Delivery (Cesarean)";
                return 9.4;
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
