// this is a superclass for all departments

package org.example;

import java.util.Scanner;

public class ServicesUsed {
    public double addServices() {
        String departmentName = "Department Name";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Service 1
                [2] Service 2
                [3] Service 3
                [4] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Service 1";
                return 1.1;
            case 2:
                serviceUsed = "Service 2";
                return 1.2;
            case 3:
                serviceUsed = "Service 3";
                return 1.3;
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
