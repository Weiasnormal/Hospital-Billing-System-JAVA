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


    public void InsertToDatabase(boolean availableToInsert , int patientID, String name, int age, String gender, String contactNumber, String address){
        // this method inserts the user information to the database
        // ang ginagawa nitong if statement ay kapag false yung argument passed sa method,
        // return agad at hindi na ieexecute yung mga code sa ibaba
        if(!availableToInsert) {
            return;
        }

        try {
            con.setAutoCommit(true);
            Statement statement = con.createStatement();
            String insertQuery = """
            INSERT INTO Patient (patient_ID, age, gender, patient_name, contact_number, address)
            VALUES (?, ?, ?, ?, ?, ?); """;

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

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

    public boolean PatientIDExists(int PatientID) {
        // checks if the patient id already exists in the database
        boolean exists = false;
        try {
            
            String query = "SELECT * FROM Patient WHERE patient_ID = ?";

            con.setAutoCommit(true);
            Statement statement = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(query);
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

            String query = "SELECT * FROM MedicalServices WHERE patient_ID = ? AND mst_ID = ?";

            con.setAutoCommit(true);
            Statement statement = con.createStatement();

            PreparedStatement preparedStatement = con.prepareStatement(query);
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

    public void DepartmentServicesDelete(int patientId, int mstId) {
        // Message to show the result of the operation
        String message = "\n\033[1;32mDepartment and Service successfully removed\n";

        try {
            // Check if the department and service exist for the patient
            String query = "SELECT * FROM MedicalServices WHERE patient_ID = ? AND services_ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setDouble(2, mstId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                message = "Department and service do not exist for this patient.";
            } else {
                // Delete the record if it exists
                String deleteQuery = "DELETE FROM MedicalServices WHERE patient_ID = ? AND services_ID = ?";
                PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, patientId);
                deleteStatement.setDouble(2, mstId);
                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            message = "Error during database deletion: " + e.getMessage();
            e.printStackTrace();
        }

        // Print the result message
        System.out.println(message);
    }



    public void FetchServices(int Id) {
        // view all entries of department & services and medicine added to a patient
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

//            System.out.println("Patient ID: " + Id);
            System.out.println("\n=== Department Visited and Services Used ===");
            System.out.println("---------------");

            while (resultSet.next()) {
                hasResults = true;
                String serviceID = resultSet.getString("services_ID");
                String department = resultSet.getString("Department");
                String serviceName = resultSet.getString("ServiceName");
                double price = resultSet.getDouble("Price");

                // Print the fetched service details
                System.out.println("Service ID   : " + serviceID);
                System.out.println("Department   : " + department);
                System.out.println("Service Name : " + serviceName);
                System.out.println("Price        : " + price);
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


    public void FetchMedicine(int Id){try {
        con.setAutoCommit(true);
        Statement statement = con.createStatement();
        String fetchings = "SELECT * FROM MedicineExpenses WHERE patient_id = ?";

        PreparedStatement preparedStatement = con.prepareStatement(fetchings);

        // Set the patient ID
        preparedStatement.setInt(1, Id); // 'Id' should be a variable with the patient ID to search for.

        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if there are results
        boolean hasResults = false;

//        System.out.println("Patient ID: " + Id);
        System.out.println("\n=== Medicine ===");
        System.out.println("---------------");

        while (resultSet.next()) {
            hasResults = true;
            int med_id = resultSet.getInt("med_id");
            String medicine_name = resultSet.getString("medicine_name");
            int quantity = resultSet.getInt("quantity");
            int total_cost = resultSet.getInt("total_cost");

            // Print the fetched service details
            System.out.println("Medicine ID   : " + med_id);
            System.out.println("Medicine Name : " + medicine_name);
            System.out.println("Quantity      : " + quantity);
            System.out.println("price         : " + total_cost);
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
            resultSet.next();
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
            System.out.println("-------------------------------------");
            do
            {
                int patientId = resultSet.getInt("patient_ID");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String patientName = resultSet.getString("patient_name");
                String contactNumber = resultSet.getString("contact_number");
                String address = resultSet.getString("address");

                // Print the fetched service details
                System.out.println("Patient ID     : " + patientId);
                System.out.println("Patient Name   : " + patientName);
                System.out.println("Age            : " + age);
                System.out.println("Gender         : " + gender);
                System.out.println("Contact Number : " + contactNumber);
                System.out.println("Address        : " + address);
                System.out.println("-------------------------------------");
            }while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String GetName(int id)
    {
        try {
            String query = "SELECT patient_name FROM Patient WHERE patient_ID = ?";
            con.setAutoCommit(true);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            String patientName = null;
            if (resultSet.next()) { // Move the cursor to the first row
                patientName = resultSet.getString("patient_name");
            }

            return patientName; // Will return null if no patient is found
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null; // Han
        }
    }


    public void MedicineExpenses(String name, int SelectedID, int quantity, int totalcost){

        try {
            con.setAutoCommit(true);
            Statement statement = con.createStatement();
            String insertQuery = """
            
                    INSERT INTO MedicineExpenses (patient_id, medicine_name, quantity, total_cost)
                                VALUES (?, ?, ?, ?); """;

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

            // Set the variables in the query
            preparedStatement.setInt(1, SelectedID);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, totalcost);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        }


    }


    public double Billing(int patientID) {
        double totalExpenses = -1; // Initialize to -1 to indicate failure by default
        try {
            con.setAutoCommit(true);

            // Query to calculate the total price
            String totalQuery = """
                SELECT 
                    COALESCE(ms_expenses.total_services, 0) + COALESCE(me_expenses.total_medicines, 0) AS total_expenses
                FROM 
                    Patient p
                LEFT JOIN (
                    SELECT 
                        ms.patient_ID,
                        SUM(mst.Price) AS total_services
                    FROM 
                        MedicalServices ms
                    JOIN MedicalServicesTags mst ON ms.mst_ID = mst.mst_ID
                    GROUP BY 
                        ms.patient_ID
                ) ms_expenses ON p.patient_ID = ms_expenses.patient_ID
                LEFT JOIN (
                    SELECT 
                        patient_ID,
                        SUM(total_cost) AS total_medicines
                    FROM 
                        MedicineExpenses
                    GROUP BY 
                        patient_ID
                ) me_expenses ON p.patient_ID = me_expenses.patient_ID
                WHERE 
                    p.patient_ID = ?;
                """;

            PreparedStatement totalStatement = con.prepareStatement(totalQuery);
            totalStatement.setInt(1, patientID);
            ResultSet totalResult = totalStatement.executeQuery();

            if (totalResult.next()) {
                totalExpenses = totalResult.getDouble("total_expenses");
                if (totalExpenses == 0) {
                    System.out.println("No services or medicines detected for patient ID: " + patientID);
                    return -1; // Return -1 if no expenses are found
                }

                System.out.println("Total expenses for patient ID " + patientID + ": " + totalExpenses);

                // Insert the total into the Billing table
                String insertQuery = """
                INSERT INTO Billing (patient_id, expenses)
                VALUES (?, ?);
                """;
                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                insertStatement.setInt(1, patientID);
                insertStatement.setDouble(2, totalExpenses);
                insertStatement.executeUpdate();
            } else {
                System.out.println("No patient found with ID: " + patientID);
                return -1; // Return -1 if no patient found
            }
        } catch (SQLException e) {
            System.out.println("Error during database operation: " + e.getMessage());
            e.printStackTrace();
        }

        return totalExpenses; // Return the total expenses (can be 0 or any valid amount)
    }




    public void FinalBill(int patientID, String method, double amount, double totalcost) {
        try {
            // Insert the total cost into the Billing table
            String insertBillQuery = """
        INSERT INTO Billing (patient_id, expenses)
        VALUES (?, ?);
        """;
            PreparedStatement insertBillStatement = con.prepareStatement(insertBillQuery);
            insertBillStatement.setInt(1, patientID);
            insertBillStatement.setDouble(2, totalcost);
            insertBillStatement.executeUpdate();

            // Check if the amount paid matches the total cost
            boolean paymentStatus = false;
            double remainingBalance = totalcost;

            if (amount >= totalcost) {
                paymentStatus = true;
                remainingBalance = 0;  // Payment is equal to total cost
            } else {
                remainingBalance -= amount;  // Deduct paid amount from total cost
            }

            // Insert or update the PaidCustomers table based on the payment status
            String updatePaymentQuery = """
        INSERT INTO PaidCustomers (patient_id, payment_status, payment_method, remaining_balance)
        VALUES (?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE payment_status = ?, remaining_balance = ?;
        """;
            PreparedStatement updatePaymentStatement = con.prepareStatement(updatePaymentQuery);
            updatePaymentStatement.setInt(1, patientID);
            updatePaymentStatement.setBoolean(2, paymentStatus);
            updatePaymentStatement.setString(3, method);
            updatePaymentStatement.setDouble(4, remainingBalance);

            // Provide values for the ON DUPLICATE KEY UPDATE clause (parameters 5 and 6)
            updatePaymentStatement.setBoolean(5, paymentStatus); // For payment_status
            updatePaymentStatement.setDouble(6, remainingBalance); // For remaining_balance

            updatePaymentStatement.executeUpdate();

            // Print the result for verification
            System.out.println("Bill processed for Patient ID: " + patientID);
            System.out.println("Total cost: " + totalcost);
            System.out.println("Amount paid: " + amount);
            System.out.println("Remaining balance: " + remainingBalance);
            System.out.println("Payment status: " + (paymentStatus ? "Paid" : "Not Paid"));

        } catch (SQLException e) {
            System.out.println("Error during database operation: " + e.getMessage());
            e.printStackTrace();
        }
    }






}






