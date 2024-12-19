package org.example;

import java.util.Scanner;

public class Department_Radiology extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Radiology";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] X-Ray
                [2] CT Scan
                [3] MRI
                [4] Ultrasound
                [5] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "X-Ray";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "CT Scan";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "MRI";
                return departmentName + " , " + serviceUsed;
            case 4:
                serviceUsed = "Ultrasound";
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
