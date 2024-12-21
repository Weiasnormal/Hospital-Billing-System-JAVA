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
        db.GetUserInformation(patientID);

        DisplayBill();
    }

    public void DisplayBill() {
        //
    }
}
