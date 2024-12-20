package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void IInsertToDatabase(double Services, int Id) {

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



    public boolean ValidationToDatabase(double Services, int Id){

        boolean flag = true;


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
                System.out.println("Patient service already added");
                flag = false;
            }



        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }

        return flag;

    }



    }




