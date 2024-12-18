
package org.example;

import java.util.Scanner;

public class Department_Cardiology extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Cardiology";
        String serviceUsed;
        System.out.println("=== Add Services Used ===");
        System.out.println("""
                [1] Cardiology Consultation
                [2] ECG (Electrocardiogram)
                [3] Echocardiogram
                [4] Stress Test
                [5] Back""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Cardiology Consultation";
                return departmentName + " , " + serviceUsed;
            case 2:
                serviceUsed = "ECG (Electrocardiogram)";
                return departmentName + " , " + serviceUsed;
            case 3:
                serviceUsed = "Echocardiogram";
                return departmentName + " , " + serviceUsed;
            case 4:
                serviceUsed = "Stress Test";
                return departmentName + " , " + serviceUsed;
            case 5:
                PatientDetails patientDetails = new PatientDetails();
                patientDetails.AddDepartmentAndServices();
            default:
                addServices();
                break;
        }
        return "";
    }
}
