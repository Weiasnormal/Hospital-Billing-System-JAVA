package org.example;

import org.example.Model.PatientDetailsTemplate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientDetails extends PatientDetailsTemplate {
    int selectedId;
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String successColor = "\033[0;92m";
    public static String loadingColor = "\033[0;37m";
    public static String titleborderColor = "\033[1;96m";
    public static String titleColor = "\033[1;93m";

    @Override
    public void DeptServiceMain() {
        selectedId = CheckIfPatientIdExists(); // check muna if patient id exists; no >> return main menu; yes >> hold ID and continue program
        AddPatientDetails();
    }

    private int CheckIfPatientIdExists() {
        DB db = new DB();
        db.SetSessionTimer();
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+"""+ titleColor + """
                
                ║         Add Patient Details         ║
                """ + titleborderColor +"""
                +=====================================+""");
        System.out.println(wColor + "Enter Patient ID");
        System.out.print("⪀⫸ ");

        Scanner scanner = new Scanner(System.in);
        int patientId = scanner.nextInt();
        scanner.nextLine();

        System.out.println();
        System.out.println(loadingColor + """                       
                        ········································
                        Please Wait. Checking for Patient ID...
                        ········································\n""");

        boolean exists = db.PatientIDExists(patientId);
        if (!exists) {
            System.out.println(errorColor + "Invalid Input or Patient ID does not exist");
            UserInterface.MainMenu();
        }
        return patientId;
    }

    private void AddPatientDetails() {
        DB db = new DB();
        db.SetSessionTimer();
        GenerateAndDisplayBill bill = new GenerateAndDisplayBill();
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+"""+ titleColor + """
                
                ║         Add Patient Details         ║
                """ + titleborderColor +"""
                +=====================================+""");
        System.out.println(wColor + "\f-------------------------------------\f");
        System.out.println("[Patient Information]");
        System.out.println("ID   : " + selectedId);
        System.out.println("Name : " + db.GetName(selectedId)); // yung pangalan ay manggagaling from database
        System.out.println(wColor + """
        \f-------------------------------------\f
        Please select an option:
        [1] Add Department and Services
        [2] Add Prescribed Medicine
        [3] Remove Medicine or Department entry
        [4] View All Entries
        [5] View Bill and Payment Finalization
        [6] Change Patient ID
        [7] Return to Main Menu
        \f-------------------------------------\f""");
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
                System.out.println();
                System.out.println(loadingColor + """                       
                        ········································
                        Please wait a moment...
                        ········································\n""");
                Deletion();
            case 4:
                System.out.println();
                System.out.println(loadingColor + """                       
                        ········································
                        Please Wait. Retrieving all entries...
                        ········································\n""");
                ViewAllEntries();
            case 5:
                System.out.println();
                System.out.println(loadingColor + """                       
                        ········································
                        Please Wait. Checking for expenses...
                        ········································\n""");
                bill.DisplayExpenses(selectedId);
//            case 6:
//                GenerateAndDisplayBill.PaymentBill(selectedId);
            case 6:
                System.out.println();
                System.out.println(loadingColor + """                       
                        ········································
                        Please wait a moment...
                        ········································\n""");
                DeptServiceMain();
            case 7:
                UserInterface.MainMenu();
            default:
                System.out.println(errorColor + "\n\nPlease enter a valid input");
                AddPatientDetails();
        }
    }

    @Override
    public void AddDepartmentAndServices() {
        DB db = new DB();
        db.SetSessionTimer();
        System.out.println(titleborderColor +
                """
                
                
                +=====================================+"""+ titleColor + """
                
                ║      Select Department Visited      ║
                """ + titleborderColor +"""
                +=====================================+""");
        System.out.println(wColor + """
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
                \f-------------------------------------\f""");
        System.out.print("⪀⫸ ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        System.out.println();
        System.out.println(loadingColor + """                       
                        ········································
                        Please wait a moment...
                        ········································""");

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
                System.out.println(errorColor + "\n\nPlease enter a valid input!");
                AddDepartmentAndServices();
                break;
        }

        if(selected == 0){
            System.out.println(errorColor + "\n\nAn error has occurred. Please try again!");
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
        db.SetSessionTimer();
        Scanner scanner = new Scanner(System.in);
        System.out.println(titleborderColor +
                """
                
                
                +===============================================+""" + titleColor + """
            
            ║           Add  Prescribed  Medicine           ║
            """ + titleborderColor + """
            +===============================================+""");
        int numberOfMedicine = 0;

        // Validate number of medicines input
        while (true) {
            try {
                System.out.println(wColor + """
                        How many prescribed medicine do you 
                        want to add? (Maximum of 10)""");
                System.out.print("⪀⫸ ");
                numberOfMedicine = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (numberOfMedicine <= 0 || numberOfMedicine > 10) {
                    System.out.println(errorColor + "Please enter a valid number of medicines (between 1 and 10).");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(errorColor + "Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        for (int i = 0; i < numberOfMedicine; i++) {
            System.out.println(wColor + "\f-----------------------------------------------\f");
            System.out.print("Medicine Name : ");
            String name = scanner.nextLine();

            int quantity = 0;
            while (true) {
                try {
                    System.out.print(wColor + "Quantity : ");
                    quantity = scanner.nextInt();
                    if (quantity > 0) {
                        break;
                    } else {
                        System.out.println(errorColor + "Please enter a positive number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(errorColor + "Invalid input. Please enter a valid quantity.");
                    scanner.nextLine(); // Clear invalid input
                }
            }

            int totalCost = 0;
            while (true) {
                try {
                    System.out.print(wColor + "Total Cost : ");
                    totalCost = scanner.nextInt();
                    if (totalCost > 0) {
                        break;
                    } else {
                        System.out.println(errorColor + "Please enter a positive amount.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(errorColor + "Invalid input. Please enter a valid cost.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
            scanner.nextLine(); // Consume newline
            System.out.println(wColor + "\f-----------------------------------------------\f");
            db.MedicineExpenses(name, selectedId, quantity, totalCost);
        }
        System.out.println(successColor + "\nPrescribed medicines successfully added!\n");
        AddPatientDetails();
    }

    @Override
    public void ViewAllEntries() {
        DB db = new DB();
        db.SetSessionTimer();
        System.out.println(titleborderColor +
                """
                
                
                +===============================================+""" + titleColor + """
            
            ║        Visit Details and Services Used        ║
            """ + titleborderColor + """
            +===============================================+""");
        System.out.println(wColor + "[Patient Information]");
        System.out.println("ID   : " + selectedId);
        System.out.println("Name : " + db.GetName(selectedId));
        System.out.println(wColor + "\f-----------------------------------------------\f");

        db.FetchServices(selectedId);
        db.FetchMedicine(selectedId);
        System.out.println(loadingColor + "\nPress enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        AddPatientDetails();
    }

    @Override
    public void Deletion() {
        DB db = new DB();
        db.SetSessionTimer();
        Scanner scanner = new Scanner(System.in);

        int selected = 0;
        while (true) {
            try {
                System.out.println(titleborderColor +
                        """
                        
                        
                        +=====================================+""" + titleColor + """
                    
                    ║        Select what to Delete        ║
                    """ + titleborderColor + """
                    +=====================================+""");
                System.out.println(wColor + """
                    Please select an option:
                    [1] Charged Departments and Services
                    [2] Charged Medicines
                    \f-------------------------------------\f""");
                System.out.print("⪀⫸ ");
                selected = scanner.nextInt();
                if (selected == 1 || selected == 2) {
                    break;
                } else {
                    System.out.println(errorColor + "Invalid option. Please select 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println(errorColor + "Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        switch (selected) {
            case 1 -> {
                db.FetchServices(selectedId);
                System.out.println(wColor + "\nPlease enter the service ID you want to delete");
                System.out.print("⪀⫸ ");
                while (true) {
                    try {
                        int mst_id = scanner.nextInt();
                        db.DepartmentServicesDelete(selectedId, mst_id);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(errorColor + "Invalid input. Please enter a valid service ID.\033[0m");
                        scanner.nextLine(); // Clear invalid input
                    }
                }
                AddPatientDetails();
            }
            case 2 -> {
                db.FetchMedicine(selectedId);
                System.out.println(wColor + "\nPlease enter the medicine ID you want to delete");
                System.out.print("⪀⫸ ");
                while (true) {
                    try {
                        int med_id = scanner.nextInt();
                        db.MedicineDelete(selectedId, med_id);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(errorColor + "Invalid input. Please enter a valid medicine ID.\033[0m");
                        scanner.nextLine(); // Clear invalid input
                    }
                }
                AddPatientDetails();
            }
        }
    }

}
