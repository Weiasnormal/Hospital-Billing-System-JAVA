package org.example;

import java.util.Scanner;

public class PatientDetails extends PatientDetailsTemplate {
    @Override
    void DeptServiceMain() {
        CheckIfPatientIdExists(); // check muna if patient id exists; no >> return main menu; yes >> continue program
        System.out.println("=== Add Patient Details ===");
        System.out.println("""
        Please select an option:
        [1] Add Visited Department
        [2] Add Service Used
        [3] Add Prescribed Medicine
        [4] View All Entries
        [5] Return to Main Menu""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        UserInterface.ConsoleClear();
        switch (option) {
            case 1:
                AddDepartment();
                break;
            case 2:
                AddService();
                break;
            case 3:
                AddMedicine();
                break;
            case 4:
                ViewAllEntries();
                break;
            case 5:
                UserInterface.MainMenu();
            default:
                DeptServiceMain();
                break;
        }
    }

    private void CheckIfPatientIdExists() {
        // ito yung magchecheck kung existing yung patient id

        // pag hindi, magrereturn ng "patient does not exist"(makikita tong part na to sa database.java)
        // tapos babalik ng main menu

        // kapag naman true, idederetso yung code don sa DeptServiceMain
        // parang ampangit nga ng gawa ko rito, HAHAHA

        // baghuin niyo na lang kung talagang ansama ng code ko rito, hahaha
        System.out.println("=== Add Patient Details ===");
        System.out.print("Enter Patient ID");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Database database = new Database();
        boolean exists = database.GetUserInformation(patientId);
        if (!exists) {
            UserInterface.MainMenu();
        }
    }


    @Override
    void AddDepartment() {

    }

    @Override
    void AddService() {

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
