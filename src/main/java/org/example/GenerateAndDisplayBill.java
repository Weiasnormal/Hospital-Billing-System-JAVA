package org.example;

import java.util.Scanner;

public class GenerateAndDisplayBill {
    private int patientID;

    public void Main(){
        Scanner sc = new Scanner(System.in);
        DB db = new DB();
        System.out.println("=== Generate and Display Bill ===");
        System.out.println("Enter Patient ID");
        System.out.print("⪀⫸ ");
        patientID = sc.nextInt();
        DisplayBill(patientID);
    }

    public void DisplayBill(int patientID) {
        DB db = new DB();
        double total = db.Billing(patientID); // Call the method with a patient ID
        if (total != -1) {
            System.out.println("Total expenses: " + total);
        } else {
            System.out.println("Failed to calculate or no expenses found.");
        }

        UserInterface.MainMenu();
    }


    public void PaymentBill() {
        DB db = new DB();
        Scanner sc = new Scanner(System.in);
        System.out.println("What Patient ID?");
        int ID = sc.nextInt();
        sc.nextLine();
        System.out.println("What Method of Payment are you using?");
        String method = sc.nextLine();
        System.out.println("Enter Payment Amount");
        double amount = sc.nextDouble();
        db.FinalBill(ID, method, amount, db.Billing(ID));
        UserInterface.MainMenu();
    }
}
