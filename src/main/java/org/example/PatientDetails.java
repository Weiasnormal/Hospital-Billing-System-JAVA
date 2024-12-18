package org.example;

import java.util.Scanner;

public class PatientDetails extends PatientDetailsTemplate {
    @Override
    void DeptServiceMain() {
        System.out.println("=== Add Patient Details ===");
        System.out.println("""
        Please select an option:
        [1] Continue?
        [2] Return to Main Menu""");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                ContinueOptions();
                break;
            case 2:
                UserInterface.MainMenu();
                break;
            default:
                UserInterface.ConsoleClear();
                DeptServiceMain();
                break;
        }
    }

    private void ContinueOptions() {
        System.out.println("=== Add Patient Details ===");
        System.out.println("""
        Please select an option:
        [1] Add Visited Department
        [2] Add Service Used
        [3] Add Prescribed Medicine
        [4] View All Entries""");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:
                UserInterface.MainMenu();
                break;
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

    }
}
