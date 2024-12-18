package org.example;

import java.util.Scanner;

public class PatientDetails extends PatientDetailsTemplate {
    @Override
    void DeptServiceMain() {
        int selectedId = CheckIfPatientIdExists(); // check muna if patient id exists; no >> return main menu; yes >> hold ID and continue program

        System.out.println("=== Add Patient Details ===");
        System.out.println("""
        Please select an option:
        [1] Add Department and Services
        [2] Add Prescribed Medicine
        [3] View All Entries
        [4] Change Patient ID
        [5] Return to Main Menu""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        UserInterface.ConsoleClear();
        switch (option) {
            case 1:
                AddDepartmentAndServices();
                break;
            case 2:
                AddMedicine();
                break;
            case 3:
                ViewAllEntries();
                break;
            case 5:
                UserInterface.MainMenu();
            case 4:
            default:
                DeptServiceMain();
                break;
        }
    }

    private int CheckIfPatientIdExists() {
        System.out.println("=== Add Patient Details ===");
        System.out.println("Enter Patient ID");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Database database = new Database();
        boolean exists = database.GetUserInformation(patientId);
        if (!exists) {
            UserInterface.MainMenu();
        }
        return patientId;
    }

    @Override
    void AddDepartmentAndServices() {
        System.out.println("=== Select Deparment Visited ===");
        System.out.println("""
                Please select an option:
                [1]  General Medicine
                [2]  Cardiology
                [3]  Radiology
                [4]  Orthopedics
                [5]  Emergency
                [6]  Laboratory Services
                [7]  Surgery
                [8]  Pediatrics
                [9]  Maternity
                [10] Dental
                [11] Return""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        ServicesUsed servicesUsed;
        String selected;
        switch (option) {
            case 1:
                servicesUsed = new GeneralMedicine();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 2:
                servicesUsed = new Cardiology();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 3:
                servicesUsed = new Radiology();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 4:
                servicesUsed = new Orthopedics();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 5:
                servicesUsed = new Emergency();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 6:
                servicesUsed = new LaboratoryServices();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                break;
        }
        System.out.println("Deparment Visited and Services Used has been successfully added!"); // temporary only
    }

    @Override
    void AddMedicine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Add Prescribed Medicine ===");
        System.out.println("Name : ");
        String name = scanner.nextLine();
        System.out.println("Total Cost : ");
        int totalCost = scanner.nextInt();
        scanner.nextLine();

        // hindi pa ito tapos
    }

    @Override
    void ViewAllEntries() {
        // bele ang mangyayari rito ay ipapakita sa console/terminal yung personal information nung patient
        // pati na rin yung mga deparments visited, services, at prescribed medicine

        // ang magiging format ay personal info muna ni patient, then yung mga
        // * departments visited
        // * services
        // * prescribed medicine
        // ganan ang pagkakasunod-sunod nila
    }
}
