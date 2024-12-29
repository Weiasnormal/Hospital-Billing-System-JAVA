
package org.example;

import java.util.Scanner;

public class Department_Cardiology extends ServicesUsed{
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String titleColor = "\033[1;93m";
    public static String titleborderColor = "\033[1;96m";

    @Override
    public double addServices() {
        String departmentName = "Cardiology";
        StringBuilder servicesUsed = new StringBuilder(); // Initialize an empty string to accumulate services
        Scanner scanner = new Scanner(System.in);

//        do {
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+"""+ titleColor + """
                
                ║          Add Services Used          ║
                """ + titleborderColor +"""
                +=====================================+""");
            System.out.println(wColor + """
                    [1] Cardiology Consultation
                    [2] ECG (Electrocardiogram)
                    [3] Echocardiogram
                    [4] Stress Test
                    [5] Back
                    \f-------------------------------------\f""");
            System.out.print("⪀⫸ ");
            int option = scanner.nextInt();
            String serviceUsed;


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
//                    return servicesUsed.toString(); // Exit the current department
                default:
                    System.out.println(errorColor + "\n\nPlease enter a valid input!");
                    addServices();

//                    continue; // Skip the rest of the loop and ask again
            }

            // Add the selected service to the list
//            if (!servicesUsed.isEmpty()) {
//                servicesUsed.append("\n"); // Add separator for multiple services
//            }
//            servicesUsed = new StringBuilder(departmentName + " , " + serviceUsed);
//
//            // Ask the user if they want to add another service
//            System.out.println("Do you want to add another service? [Y/N]");
//            System.out.print("> ");
//            scanner.nextLine(); // Consume newline character
//        } while (scanner.nextLine().equalsIgnoreCase("Y"));

            return 0;


        }
    }

