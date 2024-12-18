package org.example;
import java.util.Scanner;

public class Department_Maternity extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Maternity";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Prenatal Check-Up
                [2] Ultrasound (Pregnancy)
                [3] Delivery (Normal)
                [4] Delivery (Cesarean)
                [5] Back""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Prenatal Check-Up";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "Ultrasound (Pregnancy)";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Delivery (Normal)";
                return departmentName + " , " + serviceUsed;
            case 4:
                serviceUsed = "Delivery (Cesarean)";
                return departmentName + " , " + serviceUsed;
            case 5:
                PatientDetails patientDetails = new PatientDetails();
                patientDetails.AddDepartmentAndServices();
            default:
                addServices();
                break;
        }
        return "";
    }
}
