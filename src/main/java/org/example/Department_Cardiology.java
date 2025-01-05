package org.example;

import java.util.Scanner;

public class Department_Cardiology extends ServicesUsed {
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String titleColor = "\033[1;93m";
    public static String titleborderColor = "\033[1;96m";

    @Override
    public double addServices() {
        String serviceUsed;
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+""" + titleColor + """
                
                ║          Add Services Used          ║
                """ + titleborderColor + """
                +=====================================+""");
        System.out.println(wColor + """
                [1] Cardiology Consultation
                [2] ECG (Electrocardiogram)
                [3] Echocardiogram
                [4] Stress Test
                [5] Back
                \f-------------------------------------\f""");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                serviceUsed = "Cardiology Consultation";
                return 2.1;
            case 2:
                serviceUsed = "ECG (Electrocardiogram)";
                return 2.2;
            case 3:
                serviceUsed = "Echocardiogram";
                return 2.3;
            case 4:
                serviceUsed = "Stress Test";
                return 2.4;
            case 5:
                PatientDetails patientDetails = new PatientDetails();
                patientDetails.AddDepartmentAndServices();
            default:
                System.out.println(errorColor + "\n\nPlease enter a valid input!");
                addServices();
                break;
        }
        return 0;
    }
}
