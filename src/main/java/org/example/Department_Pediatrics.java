package org.example;
import java.util.Scanner;

public class Department_Pediatrics extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Pediatrics";
        String serviceUsed;
        System.out.println("\u001B[31m"+
                """
                
                +---------------------------------+
                |        Add Services Used        |
                +---------------------------------+""" + "\u001B[0m");
        System.out.println("""
                [1] Pediatric Consultation
                [2] Immunization (per vaccine)
                [3] Growth Check-Up
                [4] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Pediatric Consultation";
                return 8.1;
            case 2:
                serviceUsed = "Immunization (per vaccine)";
                return 8.2;
            case 3:
                serviceUsed = "Growth Check-Up";
                return 8.3;
            case 4:
                PatientDetails patientDetails = new PatientDetails();
                patientDetails.AddDepartmentAndServices();
            default:
                addServices();
                break;
        }
        return 0;
    }
}
