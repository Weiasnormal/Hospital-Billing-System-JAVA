
package org.example;

import java.util.Scanner;

public class Department_Cardiology extends ServicesUsed{
    @Override
    public String addServices() {
        String departmentName = "Cardiology";
        StringBuilder servicesUsed = new StringBuilder(); // Initialize an empty string to accumulate services
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\u001B[31m"+
                """
                
                +---------------------------------+
                |        Add Services Used        |
                +---------------------------------+""" + "\u001B[0m");
            System.out.println("""
                [1] Cardiology Consultation
                [2] ECG (Electrocardiogram)
                [3] Echocardiogram
                [4] Stress Test
                [5] Back""");
            System.out.print("> ");
            int option = scanner.nextInt();
            String serviceUsed;

            switch (option) {
                case 1:
                    serviceUsed = "Cardiology Consultation";
                    break;
                case 2:
                    serviceUsed = "ECG (Electrocardiogram)";
                    break;
                case 3:
                    serviceUsed = "Echocardiogram";
                    break;
                case 4:
                    serviceUsed = "Stress Test";
                    break;
                case 5:
                    PatientDetails patientDetails = new PatientDetails();
                    patientDetails.AddDepartmentAndServices();
                    return servicesUsed.toString(); // Exit the current department
                default:
                    System.out.println("Invalid option. Please select again.");
                    continue; // Skip the rest of the loop and ask again
            }

            // Add the selected service to the list
            if (!servicesUsed.isEmpty()) {
                servicesUsed.append("\n"); // Add separator for multiple services
            }
            servicesUsed = new StringBuilder(departmentName + " , " + serviceUsed);

            // Ask the user if they want to add another service
            System.out.println("Do you want to add another service? [Y/N]");
            System.out.print("> ");
            scanner.nextLine(); // Consume newline character
        } while (scanner.nextLine().equalsIgnoreCase("Y"));

        return servicesUsed.toString();

    }
}
