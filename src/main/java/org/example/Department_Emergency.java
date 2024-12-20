package org.example;

import java.util.Scanner;

public class Department_Emergency extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Emergency";
        String serviceUsed;
        System.out.println("\u001B[31m"+
                """
                
                +---------------------------------+
                |        Add Services Used        |
                +---------------------------------+""" + "\u001B[0m");
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
                return 5.1;
            case 2:
                serviceUsed = "First Aid Treatment";
                return 5.2;
            case 3:
                serviceUsed = "Minor Surgery";
                return 5.3;
            case 4:
                serviceUsed = "Ambulance Service";
                return 5.4;
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