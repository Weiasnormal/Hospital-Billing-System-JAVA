package org.example;

import java.util.Scanner;

public class Orthopedics  extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Orthopedics";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Orthopedic Consultation
                [2] Bone X-Ray
                [3] Fracture Treatment
                [4] Physiotherapy Session
                [5] Back""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Orthopedic Consultation";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "Bone X-Ray";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Fracture Treatment";
                return departmentName + " , " + serviceUsed;
            case 4:
                serviceUsed = "Physiotherapy Session";
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