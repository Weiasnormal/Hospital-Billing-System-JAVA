package org.example;

import java.util.Scanner;

public class PatientDetails extends PatientDetailsTemplate {
    int selectedId;
    @Override
    void DeptServiceMain() {
        Patient patient = new Patient();
        selectedId = patient.CheckIfPatientIdExists(); // check muna if patient id exists; no >> return main menu; yes >> hold ID and continue program
        AddPatientDetails();
    }

    private void AddPatientDetails() {
        System.out.println("=== Add Patient Details ===");
        System.out.println("ID   : " + selectedId);
        System.out.println("Name : " + "(patient name)");
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

    @Override
    void AddDepartmentAndServices() {
        System.out.println("=== Select Department Visited ===");
        System.out.println("""
                Please select an option:
                [01] General Medicine
                [02] Cardiology
                [03] Radiology
                [04] Orthopedics
                [05] Emergency
                [06] Laboratory Services
                [07] Surgery
                [08] Pediatrics
                [09] Maternity
                [10] Dental
                [11] Back""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        ServicesUsed servicesUsed;
        String selected;
        switch (option) {
            case 1:
                servicesUsed = new Department_GeneralMedicine();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 2:
                servicesUsed = new Department_Cardiology();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 3:
                servicesUsed = new Department_Radiology();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 4:
                servicesUsed = new Department_Orthopedics();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 5:
                servicesUsed = new Department_Emergency();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 6:
                servicesUsed = new Department_LaboratoryServices();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 7:
                servicesUsed = new Department_Surgery();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 8:
                servicesUsed = new Department_Pediatrics();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 9:
                servicesUsed = new Department_Maternity();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 10:
                servicesUsed = new Department_Dental();
                selected = servicesUsed.addServices();
                servicesUsed.InsertToDatabase(selected);
                break;
            case 11:
                AddPatientDetails();
                break;
            default:
                break;
        }

        System.out.println("Department and Service successfully added to patient");
        AddPatientDetails();
    }

    @Override
    void AddMedicine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Add Prescribed Medicine ===");
        System.out.println("How many prescribed medicine do you want to add?");
        System.out.print("> ");
        int numberOfMedicine = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < numberOfMedicine; i++){
            System.out.println("=== Add Prescribed Medicine ===");
            System.out.println("Name : ");
            String name = scanner.nextLine();
            System.out.println("Total Cost : ");
            int totalCost = scanner.nextInt();
            scanner.nextLine();
            // add yung name nung medicine at total cost sa database
        }
        System.out.println("Prescribed medicines successfully added!");
        AddDepartmentAndServices();
    }

    @Override
    void ViewAllEntries() {
        System.out.println(selectedId);
        // bele ang mangyayari rito ay ipapakita sa console/terminal yung personal information nung patient
        // pati na rin yung mga deparments visited, services, at prescribed medicine

        // ang magiging format ay personal info muna ni patient, then yung mga
        // * departments visited
        // * services
        // * prescribed medicine
        // ganan ang pagkakasunod-sunod nila
    }
}
