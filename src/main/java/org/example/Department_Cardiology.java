
package org.example;

import java.util.Scanner;

public class Department_Cardiology extends ServicesUsed{
    @Override
    public double addServices() {
        String departmentName = "Cardiology";
        StringBuilder servicesUsed = new StringBuilder(); // Initialize an empty string to accumulate services
        Scanner scanner = new Scanner(System.in);

//        do {
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║          Add Services Used          ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
            System.out.println("\033[1;97m" + """
                    [1] Cardiology Consultation
                    [2] ECG (Electrocardiogram)
                    [3] Echocardiogram
                    [4] Stress Test
                    [5] Back
                    \f-------------------------------------\f""" + "\u001B[0m");
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

//                case 5:
//                    PatientDetails patientDetails = new PatientDetails();
//                    patientDetails.AddDepartmentAndServices();
//                    return servicesUsed.toString(); // Exit the current department
                default:
                    System.out.println("Invalid option. Please select again.");
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

