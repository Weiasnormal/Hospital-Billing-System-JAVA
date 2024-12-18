package org.example;
import java.util.Scanner;

public class Department_Pediatrics extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Pediatrics";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Pediatric Consultation
                [2] Immunization (per vaccine)
                [3] Growth Check-Up
                [4] Back""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Pediatric Consultation";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "Immunization (per vaccine)";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Growth Check-Up";
                return departmentName + " , " + serviceUsed;
            case 4:
                PatientDetails patientDetails = new PatientDetails();
                patientDetails.AddDepartmentAndServices();
            default:
                addServices();
                break;
        }
        return "";
    }
}
