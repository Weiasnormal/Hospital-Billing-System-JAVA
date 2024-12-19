package org.example;
import java.util.Scanner;

public class Department_Surgery extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Surgery";
        String serviceUsed;
        System.out.println("""
                
                +---------------------------------+
                |        Add Services Used        |
                +---------------------------------+""");
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
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "Major Surgery";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Post-Surgery Care (per day)";
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
}
