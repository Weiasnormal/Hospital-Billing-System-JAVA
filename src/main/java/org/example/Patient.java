// this class inherited the attributes from Person class
package org.example;

import java.sql.*;
import java.util.Scanner;

public class Patient extends Person {
    public Patient(){}



    public Patient(String name, int age, String gender, String contact_number, String address) {

        Name = name;
        Age = age;
        Gender = gender;
        ContactNumber = contact_number;
        Address = address;
    }

    public void PatientDatabase(boolean check) {

        if(!check) {
            return;
        }

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus",
                    "u936666569_Nimbus",
                    "Haduken@123456"
            );
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            String insertQuery = """
            INSERT INTO Patient (age, gender, patient_name, contact_number, address)
            VALUES (?, ?, ?, ?, ?);
        """;


            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Set the variables in the query
            preparedStatement.setInt(1, Age);
            preparedStatement.setString(2, Gender);
            preparedStatement.setString(3, Name);
            preparedStatement.setString(4, ContactNumber);
            preparedStatement.setString(5, Address);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);


        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }



    }




    public boolean Validation(){
        Database database = new Database();
        boolean check = database.GetUserInformation(Name);
        if(!(Gender.equalsIgnoreCase("male") || Gender.equalsIgnoreCase("female"))) {
            System.out.println("Invalid Gender!");
            return false;
        }

        if(ContactNumber.length() != 11){
            System.out.println("Invalid Contact Number!");
            return false;
        }

        if(check){ // this will check if the username/patient name already exists in the temporary database
            System.out.println("This patient already exist!");
            return false;
        } else {
            database.SetUserInformation(Id, Name, Age, Gender, ContactNumber);
            System.out.println("Patient Registered Successfully!");

        }

        return true;
    }

    public int CheckIfPatientIdExists() {
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
}
