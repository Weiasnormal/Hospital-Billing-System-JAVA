package org.example;

import org.example.Model.PatientDetailsTemplate;

import java.util.Scanner;

public class PatientDetails extends PatientDetailsTemplate {
    int selectedId;
    @Override
    public void DeptServiceMain() {
        selectedId = CheckIfPatientIdExists(); // check muna if patient id exists; no >> return main menu; yes >> hold ID and continue program
        AddPatientDetails();
    }

    private int CheckIfPatientIdExists() {
        DB db = new DB();
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║         Add Patient Details         ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("Enter Patient ID");
        System.out.print("⪀⫸ ");

        Scanner scanner = new Scanner(System.in);
        int patientId = scanner.nextInt();
        scanner.nextLine();

        boolean exists = db.PatientIDExists(patientId);
        if (!exists) {
            UserInterface.MainMenu();
        }
        return patientId;
    }

    private void AddPatientDetails() {
        DB db = new DB();

        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║         Add Patient Details         ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("ID   : " + selectedId);
        System.out.println("Name : " + db.GetName(selectedId)); // yung pangalan ay manggagaling from database
        System.out.println("\033[1;97m" + """
        \f-------------------------------------\f
        Please select an option:
        [1] Add Department and Services
        [2] Add Prescribed Medicine
        [3] View All Entries
        [4] Change Patient ID
        [5] Return to Main Menu
        \f-------------------------------------\f""" + "\u001B[0m");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        UserInterface.ConsoleClear();
        switch (option) {
            case 1:
                AddDepartmentAndServices();
            case 2:
                AddMedicine();
            case 3:
                ViewAllEntries();
            case 4:
                DeptServiceMain();
            case 5:
                UserInterface.MainMenu();
            default:
                AddPatientDetails();
        }
    }

    @Override
    public void AddDepartmentAndServices() {
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║      Select Department Visited      ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m" + """
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
                [11] Back
                \f-------------------------------------\f""" + "\u001B[0m");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        DB db = new DB();
        scanner.nextLine();
        ServicesUsed servicesUsed;
        double selected = 0;
        switch (option) {
            case 1:
                servicesUsed = new Department_GeneralMedicine();
                selected = servicesUsed.addServices();
                break;
            case 2:
                servicesUsed = new Department_Cardiology();
                selected = servicesUsed.addServices();
                break;
            case 3:
                servicesUsed = new Department_Radiology();
                selected = servicesUsed.addServices();
                break;
            case 4:
                servicesUsed = new Department_Orthopedics();
                selected = servicesUsed.addServices();
                break;
            case 5:
                servicesUsed = new Department_Emergency();
                selected = servicesUsed.addServices();
                break;
            case 6:
                servicesUsed = new Department_LaboratoryServices();
                selected = servicesUsed.addServices();
                break;
            case 7:
                servicesUsed = new Department_Surgery();
                selected = servicesUsed.addServices();
                break;
            case 8:
                servicesUsed = new Department_Pediatrics();
                selected = servicesUsed.addServices();
                break;
            case 9:
                servicesUsed = new Department_Maternity();
                selected = servicesUsed.addServices();
                break;
            case 10:
                servicesUsed = new Department_Dental();
                selected = servicesUsed.addServices();
                break;
            case 11:
                AddPatientDetails();
                break;
            default:
                break;
        }
        if(db.DepartmentServicesExists(selected,selectedId)){
            AddDepartmentAndServices();
        }
        db.InsertToDatabase(selected, selectedId);
        AddPatientDetails();
    }

    @Override
    public void AddMedicine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[1;96m" +
                """
                
                
                +===============================================+"""+ "\033[1;33m" + """
                
                ║           Add  Prescribed  Medicine           ║
                """ + "\033[1;96m" +"""
                +===============================================+""" + "\u001B[0m");
        System.out.println("How many prescribed medicine do you want to add?");
        System.out.print("⪀⫸ ");
        int numberOfMedicine = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < numberOfMedicine; i++){
            System.out.println("\f-----------------------------------------------\f");
            System.out.print("Medicine Name : ");
            String name = scanner.nextLine();
            System.out.print("Quantity : ");
            int quantity = scanner.nextInt();
            System.out.print("Total Cost : ");
            int totalCost = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\f-----------------------------------------------\f");
            // dito na yung pag-add nung name of medicine at total cost sa database
        }
        System.out.println("\n" + "\033[1;32m" + "Prescribed medicines successfully added!\n");
        AddPatientDetails();
    }

    @Override
    public void ViewAllEntries() {
        DB db = new DB();
        db.FetchServices(selectedId);

        // magkakaroon pa rito ng option si user kung may gusto ba siyang tanggalin sa list of entries

        AddPatientDetails();
    }
}
