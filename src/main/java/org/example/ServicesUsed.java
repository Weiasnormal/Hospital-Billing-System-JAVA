// this is a superclass for all departments

package org.example;

import java.util.Scanner;

public class ServicesUsed {
    public String addServices() {
        String departmentName = "Department Name";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Serivce 1
                [2] Service 2
                [3] Service 3
                [4] Back""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Service 1";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "Service 2";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Service 3";
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

    public void InsertToDatabase(String name) {
        String[] makeEmSplit = name.split(",");
        String departmentName = makeEmSplit[0].trim();
        String serviceUsed = makeEmSplit[1].trim();
        System.out.println("They have been inserted into the database");
        System.out.println("Department Name: " + departmentName);
        System.out.println("Service Used: " + serviceUsed);
    }
}
