package org.example;
import java.util.Scanner;

public class Department_Surgery extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Surgery";
        String serviceUsed;
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║          Add Services Used          ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
                [1] Minor Surgery
                [2] Major Surgery
                [3] Post-Surgery Care (per day)
                [4] Back
                \f-------------------------------------\f""" + "\u001B[0m");
        System.out.print("⪀⫸ ");
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
