package org.example;
import java.util.Scanner;

public class Department_Dental extends ServicesUsed {
    @Override
    public double addServices() {
        String departmentName = "Dental";
        String serviceUsed;
        System.out.println("\u001B[31m"+
                """
                
                +---------------------------------+
                |        Add Services Used        |
                +---------------------------------+""" + "\u001B[0m");
        System.out.println("""
                [1] Dental Check-Up
                [2] Tooth Extraction
                [3] Teeth Cleaning
                [4] Root Canal Treatment
                [5] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Dental Check-Up";
                return 10.1;
            case 2:
                serviceUsed = "Tooth Extraction";
                return 10.2;
            case 3:
                serviceUsed = "Teeth Cleaning";
                return 10.3;
            case 4:
                serviceUsed = "Root Canal Treatment";
                return 10.4;
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
