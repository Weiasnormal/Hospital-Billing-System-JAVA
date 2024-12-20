package org.example;

import java.util.Scanner;

public class Department_Radiology extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Radiology";
        String serviceUsed;
        System.out.println("\u001B[31m"+
                """
                
                +---------------------------------+
                |        Add Services Used        |
                +---------------------------------+""" + "\u001B[0m");
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
                return 3.1;
            case 2:
                serviceUsed = "CT Scan";
                return 3.2;
            case 3:
                serviceUsed = "MRI";
                return 3.3;
            case 4:
                serviceUsed = "Ultrasound";
                return 3.4;
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
