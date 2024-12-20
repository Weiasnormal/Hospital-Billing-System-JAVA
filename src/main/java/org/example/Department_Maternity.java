package org.example;
import java.util.Scanner;

public class Department_Maternity extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Maternity";
        String serviceUsed;
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║          Add Services Used          ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
                [1] Prenatal Check-Up
                [2] Ultrasound (Pregnancy)
                [3] Delivery (Normal)
                [4] Delivery (Cesarean)
                [5] Back
                \f-------------------------------------\f""" + "\u001B[0m");
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
                addServices();
                break;
        }
        return 0;
    }
}
