// this class inherited the attributes from Person class
package org.example.Model;

import org.example.DB;

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

    public boolean Validation(){
        DB db = new DB();
        db.SetSessionTimer();
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
            return true;
        }
        catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }
}
