// this class inherited the attributes from Person class
package org.example.Model;

import org.example.DB;
import org.example.Database;

import java.sql.*;

public class Patient extends Person {
    public Patient(int ID, String name, int age, String gender, String contact_number, String address) {
        Id = ID;
        Name = name;
        Age = age;
        Gender = gender;
        ContactNumber = contact_number;
        Address = address;
    }

    public void PatientDatabase(boolean check) {
        // ang ginagawa nitong if statement ay kapag false yung argument passed sa method,
        // return agad at hindi na ieexecute yung mga code sa ibaba
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
            INSERT INTO Patient (patient_ID, age, gender, patient_name, contact_number, address)
            VALUES (?, ?, ?, ?, ?, ?); """;

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Set the variables in the query
            preparedStatement.setInt(1, Id);
            preparedStatement.setInt(2, Age);
            preparedStatement.setString(3, Gender);
            preparedStatement.setString(4, Name);
            preparedStatement.setString(5, ContactNumber);
            preparedStatement.setString(6, Address);

//            Execute the insert
            preparedStatement.executeUpdate();
            System.out.println("Patient Registered Successfully!");
        }
        catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }
    }





    public boolean Validation(){
        DB db = new DB();
        try {
            String query = "SELECT * FROM Patient WHERE patient_ID = ?";
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Id);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the ID exists
            if (resultSet.next()) {
                System.out.println("Patient ID " + Id + " exists in the database.");
                return false;
            }

        if(!(Gender.equalsIgnoreCase("male") || Gender.equalsIgnoreCase("female"))) {
            System.out.println("Invalid Gender!");
            return false;
        }

        if(ContactNumber.length() != 11){
            System.out.println("Invalid Contact Number!");
            return false;
        }


        System.out.println("Adding...");
        return true;


        }
        catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }
}
