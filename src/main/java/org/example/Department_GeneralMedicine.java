package org.example;

import java.sql.*;
import java.util.Scanner;

public class Department_GeneralMedicine extends ServicesUsed{
    @Override
    public double addServices() {


            String departmentName = "General Medicine";
            String serviceUsed;
            System.out.println("\u001B[31m" +
                    """
                            
                            +---------------------------------+
                            |        Add Services Used        |
                            +---------------------------------+""" + "\u001B[0m");
            System.out.println("""
                    [1] Consultation
                    [2] Blood Test
                    [3] Blood Pressure Check
                    [4] Back""");
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    serviceUsed = "Consultation";
                    return 1.1;
                case 2:
                    serviceUsed = "Blood Test";
                    return 1.2;
                case 3:
                    serviceUsed = "Blood Pressure Check";
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
