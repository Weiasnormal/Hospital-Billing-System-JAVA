package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DB {
    Connection con;

    public DB() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus",
                    "u936666569_Nimbus",
                    "Haduken@123456"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return con;
    }
    public void IInsertToDatabase(double Services, int Id) {

        try {
            con.setAutoCommit(true);
            Statement statement = con.createStatement();
            String insertQuery = """
            
                    INSERT INTO MedicalServices (patient_ID, mst_ID)
                                VALUES (?, ?); """;

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

            // Set the variables in the query
            preparedStatement.setInt(1, Id);
            preparedStatement.setDouble(2, Services);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }


    }


    public boolean ValidationToDatabase(double Services, int Id) {

        boolean flag = true;


        try {

            String query = "SELECT * FROM MedicalServices WHERE patient_ID = ? AND mst_ID = ?";
            con.setAutoCommit(true);
            Statement statement = con.createStatement();

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, Id);
            preparedStatement.setDouble(2, Services);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Patient service already added");
                flag = false;
            }


        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }

        return flag;

    }


    public void FetchServices(int Id) {

        try {
            con.setAutoCommit(true);
            Statement statement = con.createStatement();
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

            PreparedStatement preparedStatement = con.prepareStatement(fetchings);

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

    public void GetUserInformation()
    {
        try {
            String query = "SELECT * FROM Patient";
            con.setAutoCommit(true);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            displayUserInformation(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void GetUserInformation(int id)
    {
        try
        {
            String query = "SELECT * FROM Patient WHERE patient_ID = ?";
            con.setAutoCommit(true);
            Statement statement = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            if(!resultSet.isLast())
                System.out.println("No User with this ID found");
            else
                displayUserInformation(resultSet);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void displayUserInformation(ResultSet resultSet)
    {
        try {
            do
            {
                int patientId = resultSet.getInt("patient_ID");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String patientName = resultSet.getString("patient_name");
                String contactNumber = resultSet.getString("contact_number");
                String address = resultSet.getString("address");

                // Print the fetched service details

                System.out.println("\n---------------");
                System.out.println("Patient ID     : " + patientId);
                System.out.println("Patient Name   : " + patientName);
                System.out.println("Age            : " + age);
                System.out.println("Gender         : " + gender);
                System.out.println("Contact Number : " + contactNumber);
                System.out.println("Address        : " + address);
                System.out.println("\n---------------");
            }while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}






