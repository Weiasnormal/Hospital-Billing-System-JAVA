package org.example;

import java.util.Scanner;

public class Department_Emergency extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Emergency";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Emergency Room Admission
                [2] First Aid Treatment
                [3] Minor Surgery
                [4] Ambulance Service
                [5] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Emergency Room Admission";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "First Aid Treatment";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Minor Surgery";
                return departmentName + " , " + serviceUsed;
            case 4:
                serviceUsed = "Ambulance Service";
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