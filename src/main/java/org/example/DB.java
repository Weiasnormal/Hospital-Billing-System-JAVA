package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DB {
    public void DBMain(){
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus",
                    "u936666569_Nimbus",
                    "Haduken@123456"
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MedicalServicesTags");

            while(resultSet.next()){
                System.out.println(resultSet.getString("department"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
