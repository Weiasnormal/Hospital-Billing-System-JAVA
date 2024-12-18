package org.example;

import java.util.Scanner;

public class GeneralMedicine extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "General Medicine";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Consultation
                [2] Blood Test
                [3] Blood Pressure Check
                [4] Back""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Consultation";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "Blood Test";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Blood Pressure Check";
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
