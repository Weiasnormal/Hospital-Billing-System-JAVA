package org.example;

import java.util.Scanner;

public class Department_LaboratoryServices extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Laboratory Services";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Complete Blood Count (CBC)
                [2] Urine Analysis
                [3] Cholesterol Test
                [4] COVID-19 Test
                [5] Back""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Complete Blood Count (CBC)";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "Urine Analysis";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Cholesterol Test";
                return departmentName + " , " + serviceUsed;
            case 4:
                serviceUsed = "COVID-19 Test";
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