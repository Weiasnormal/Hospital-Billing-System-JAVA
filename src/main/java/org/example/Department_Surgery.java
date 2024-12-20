package org.example;
import java.util.Scanner;

public class Department_Surgery extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Surgery";
        String serviceUsed;
        System.out.println("\u001B[31m"+
                """
                
                +---------------------------------+
                |        Add Services Used        |
                +---------------------------------+""" + "\u001B[0m");
        System.out.println("""
                [1] Minor Surgery
                [2] Major Surgery
                [3] Post-Surgery Care (per day)
                [4] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Minor Surgery";
                return 7.1;
            case 2:
                serviceUsed = "Major Surgery";
                return 7.2;
            case 3:
                serviceUsed = "Post-Surgery Care (per day)";
                return 7.3;
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
