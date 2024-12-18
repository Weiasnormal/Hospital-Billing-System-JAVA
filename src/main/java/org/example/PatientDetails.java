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
        [4] Return to Main Menu""");
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
            case 4:
                UserInterface.MainMenu();
            default:
                DeptServiceMain();
                break;
        }
    }

    private int CheckIfPatientIdExists() {
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
        return patientId;
    }

    @Override
    void AddDepartmentAndServices() {

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
