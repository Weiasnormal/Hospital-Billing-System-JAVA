package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DB {
    public void DBMain() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus",
                    "u936666569_Nimbus",
                    "Haduken@123456"
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MedicalServicesTags");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertToDatabase(boolean availableToInsert , int patientID, String name, int age, String gender, String contactNumber, String address){
        // this method inserts the user information to the database
        // ang ginagawa nitong if statement ay kapag false yung argument passed sa method,
        // return agad at hindi na ieexecute yung mga code sa ibaba
        if(!availableToInsert) {
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
            preparedStatement.setInt(1, patientID);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, contactNumber);
            preparedStatement.setString(6, address);

//            Execute the insert
            preparedStatement.executeUpdate();
            System.out.println("Patient Registered Successfully!");
        }
        catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void InsertToDatabase(double Services, int Id) {
        // this overload method inserts the department and service to the database
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus",
                    "u936666569_Nimbus",
                    "Haduken@123456"
            );
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            String insertQuery = """
            
                    INSERT INTO MedicalServices (patient_ID, mst_ID)
            VALUES (?, ?); """;

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Set the variables in the query
            preparedStatement.setInt(1, Id);
            preparedStatement.setDouble(2, Services);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean PatientIDExists(int PatientID) {
        // checks if the patient id already exists in the database
        boolean exists = false;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus",
                    "u936666569_Nimbus",
                    "Haduken@123456"
            );
            String query = "SELECT * FROM Patient WHERE patient_ID = ?";

            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, PatientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the ID exists
            if (resultSet.next()) {
                exists = true;
            }
        }
        catch (SQLException e) {
                System.out.println("Error during database insert: " + e.getMessage());
                e.printStackTrace();
        }
        return exists;
    }

    public boolean DepartmentServicesExists(double Services, int Id){
        // this method checks if department and service already exsits for the selected
        // patient
        boolean exists = false;
        String ewan = "\n" + "\033[1;32m" + "Department and Service successfully added to patient\n";
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus",
                    "u936666569_Nimbus",
                    "Haduken@123456"
            );

            String query = "SELECT * FROM MedicalServices WHERE patient_ID = ? AND mst_ID = ?";

            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Id);
            preparedStatement.setDouble(2, Services);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ewan = "Department and service already exists for this patient.";
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(ewan);
        return exists;
    }

    public void FetchServices(int Id) {
        // view all entries of department & services and medicine added to a patient
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus",
                    "u936666569_Nimbus",
                    "Haduken@123456"
            );
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            String fetchings = "SELECT \n" +
                    "    ms.services_ID, \n" +
                    "    ms.patient_ID, \n" +
                    "    mst.Department, \n" +
                    "    mst.ServiceName, \n" +
                    "    mst.Price\n" +
                    "FROM \n" +
                    "    MedicalServices ms\n" +
                    "JOIN \n" +
                    "    MedicalServicesTags mst ON ms.mst_ID = mst.mst_ID\n" +
                    "JOIN \n" +
                    "    Patient p ON ms.patient_ID = p.patient_ID\n" +
                    "WHERE \n" +
                    "    ms.patient_ID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(fetchings);

            // Set the patient ID
            preparedStatement.setInt(1, Id); // 'Id' should be a variable with the patient ID to search for.

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if there are results
            boolean hasResults = false;

            System.out.println("Patient ID: " + Id);
            System.out.println("---------------");

            while (resultSet.next()) {
                hasResults = true;
                String serviceID = resultSet.getString("services_ID");
                String department = resultSet.getString("Department");
                String serviceName = resultSet.getString("ServiceName");
                double price = resultSet.getDouble("Price");

                // Print the fetched service details
                System.out.println("Service ID: " + serviceID);
                System.out.println("Department: " + department);
                System.out.println("Service Name: " + serviceName);
                System.out.println("Price: " + price);
                System.out.println("---------------");
            }

            if (!hasResults) {
                System.out.println("No services found for patient ID: " + Id);
            }

        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }
    }
}