package org.example;

import org.example.Model.PatientDetailsTemplate;
import java.util.InputMismatchException;
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
            System.out.println("Invalid Input or Patient ID does not exist");
            UserInterface.MainMenu();
        }
        return patientId;
    }

    private void AddPatientDetails() {
        DB db = new DB();
        GenerateAndDisplayBill bill = new GenerateAndDisplayBill();
        System.out.println("\033[1;96m" +
                """
                
                
                +=====================================+"""+ "\033[1;33m" + """
                
                ║         Add Patient Details         ║
                """ + "\033[1;96m" +"""
                +=====================================+""" + "\u001B[0m");
        System.out.println("\033[1;97m-------------------------------------");
        System.out.println("[Patient Information]");
        System.out.println("ID   : " + selectedId);
        System.out.println("Name : " + db.GetName(selectedId)); // yung pangalan ay manggagaling from database
        System.out.println("\033[1;97m" + """
        \f-------------------------------------\f
        Please select an option:
        [1] Add Department and Services
        [2] Add Prescribed Medicine
        [3] Remove Medicine or Department entry
        [4] View All Entries
        [5] View Bill and Payment Finalization
        [6] Change Patient ID
        [7] Return to Main Menu
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
                Deletion();
            case 4:
                ViewAllEntries();
            case 5:
                bill.DisplayExpenses(selectedId);
//            case 6:
//                GenerateAndDisplayBill.PaymentBill(selectedId);
            case 6:
                DeptServiceMain();
            case 7:
                UserInterface.MainMenu();
            default:
                System.out.println("\n\nPlease enter a valid input!");
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
                System.out.println("\n\nPlease enter a valid input!");
                AddDepartmentAndServices();
                break;
        }

        if(selected == 0){
            System.out.println("\n\nAn error has occurred. Please try again!");
            AddPatientDetails();
        }

        if(db.DepartmentServicesExists(selected,selectedId)){
            AddDepartmentAndServices();
        }
        db.InsertToDatabase(selected, selectedId);
        AddPatientDetails();
    }

    @Override
    public void AddMedicine() {
        DB db = new DB();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[1;96m" +
                """
                
                
                +===============================================+""" + "\033[1;33m" + """
            
            ║           Add  Prescribed  Medicine           ║
            """ + "\033[1;96m" + """
            +===============================================+""" + "\u001B[0m");
        int numberOfMedicine = 0;

        // Validate number of medicines input
        while (true) {
            try {
                System.out.println("How many prescribed medicine do you want to add? (Maximum of 10)");
                System.out.print("⪀⫸ ");
                numberOfMedicine = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (numberOfMedicine <= 0 || numberOfMedicine > 10) {
                    System.out.println("\033[1;31mPlease enter a valid number of medicines (between 1 and 10).\033[0m");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[1;31mInvalid input. Please enter a number.\033[0m");
                scanner.nextLine(); // Clear invalid input
            }
        }

        for (int i = 0; i < numberOfMedicine; i++) {
            System.out.println("\f-----------------------------------------------\f");
            System.out.print("Medicine Name : ");
            String name = scanner.nextLine();

            int quantity = 0;
            while (true) {
                try {
                    System.out.print("Quantity : ");
                    quantity = scanner.nextInt();
                    if (quantity > 0) {
                        break;
                    } else {
                        System.out.println("\033[1;31mPlease enter a positive number.\033[0m");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\033[1;31mInvalid input. Please enter a valid quantity.\033[0m");
                    scanner.nextLine(); // Clear invalid input
                }
            }

            int totalCost = 0;
            while (true) {
                try {
                    System.out.print("Total Cost : ");
                    totalCost = scanner.nextInt();
                    if (totalCost > 0) {
                        break;
                    } else {
                        System.out.println("\033[1;31mPlease enter a positive amount.\033[0m");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\033[1;31mInvalid input. Please enter a valid cost.\033[0m");
                    scanner.nextLine(); // Clear invalid input
                }
            }
            scanner.nextLine(); // Consume newline
            System.out.println("\f-----------------------------------------------\f");
            db.MedicineExpenses(name, selectedId, quantity, totalCost);
        }
        System.out.println("\n" + "\033[1;32m" + "Prescribed medicines successfully added!\n");
        AddPatientDetails();
    }

    @Override
    public void ViewAllEntries() {
        DB db = new DB();
        System.out.println("\033[1;96m" +
            """
            
            
            +===============================================+""" + "\033[1;33m" + """
            
            ║        Visit Details and Services Used        ║
            """ + "\033[1;96m" + """
            +===============================================+""" + "\u001B[0m");
        System.out.println("[Patient Information]");
        System.out.println("ID   : " + selectedId);
        System.out.println("Name : " + db.GetName(selectedId));
        System.out.println("---------------");

        db.FetchServices(selectedId);
        db.FetchMedicine(selectedId);
        System.out.println("\nPress enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        AddPatientDetails();
    }

    @Override
    public void Deletion() {
        DB db = new DB();
        Scanner scanner = new Scanner(System.in);

        int selected = 0;
        while (true) {
            try {
                System.out.println("\033[1;96m" +
                    """
                    
                    
                    +=====================================+""" + "\033[1;33m" + """
                    
                    ║        Select what to Delete        ║
                    """ + "\033[1;96m" + """
                    +=====================================+""" + "\u001B[0m");
                System.out.println("\033[1;97m" + """
                    Please select an option:
                    [1] Charged Departments and Services
                    [2] Charged Medicines
                    \f-------------------------------------\f""" + "\u001B[0m");
                System.out.print("⪀⫸ ");
                selected = scanner.nextInt();
                if (selected == 1 || selected == 2) {
                    break;
                } else {
                    System.out.println("\033[1;31mInvalid option. Please select 1 or 2.\033[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[1;31mInvalid input. Please enter a valid number.\033[0m");
                scanner.nextLine(); // Clear invalid input
            }
        }

        switch (selected) {
            case 1 -> {
                db.FetchServices(selectedId);
                System.out.println("\nPlease enter the service ID you want to delete");
                System.out.print("⪀⫸ ");
                while (true) {
                    try {
                        int mst_id = scanner.nextInt();
                        db.DepartmentServicesDelete(selectedId, mst_id);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\033[1;31mInvalid input. Please enter a valid service ID.\033[0m");
                        scanner.nextLine(); // Clear invalid input
                    }
                }
                AddPatientDetails();
            }
            case 2 -> {
                db.FetchMedicine(selectedId);
                System.out.println("\nPlease enter the medicine ID you want to delete");
                System.out.print("⪀⫸ ");
                while (true) {
                    try {
                        int med_id = scanner.nextInt();
                        db.MedicineDelete(selectedId, med_id);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\033[1;31mInvalid input. Please enter a valid medicine ID.\033[0m");
                        scanner.nextLine(); // Clear invalid input
                    }
                }
                AddPatientDetails();
            }
        }
    }

}
