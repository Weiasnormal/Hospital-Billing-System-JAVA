package org.example;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Properties;

public class DB {
    Connection con;
    public static String wColor = "\033[1;97m";
    public static String errorColor = "\033[0;91m";
    public static String successColor = "\033[0;92m";
    public static String loadingColor = "\033[0;37m";
    public static String titleColor = "\033[1;93m";

    public DB() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", "u936666569_Nimbus");
            properties.setProperty("password", "Haduken@123456");
            properties.setProperty("autoReconnect", "true");
            properties.setProperty("useSSL", "false");
            properties.setProperty("tcpKeepAlive", "true");

            con = DriverManager.getConnection(
                    "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus", properties);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return con;
    }


    public void openConnection() {
        try {
            if (con == null || con.isClosed()) {
                Properties properties = new Properties();
                properties.setProperty("user", "u936666569_Nimbus");
                properties.setProperty("password", "Haduken@123456");
                properties.setProperty("autoReconnect", "true");
                properties.setProperty("useSSL", "false");
                properties.setProperty("tcpKeepAlive", "true");

                con = DriverManager.getConnection(
                        "jdbc:mysql://153.92.15.21:3306/u936666569_Nimbus", properties);

            }
        } catch (SQLException e) {
            System.out.println("Error while opening the connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to close the connection
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error while closing the connection: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void SetSessionTimer() {
        openConnection(); // Ensure the connection is open
        try {
            con.setAutoCommit(true); // Enable auto-commit

            // Execute the first query
            String waitTimeoutQuery = "SET SESSION wait_timeout = 3600";
            try (PreparedStatement preparedStatement = con.prepareStatement(waitTimeoutQuery)) {
                preparedStatement.executeUpdate();
            }

            // Execute the second query
            String interactiveTimeoutQuery = "SET SESSION interactive_timeout = 4200";
            try (PreparedStatement preparedStatement = con.prepareStatement(interactiveTimeoutQuery)) {
                preparedStatement.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println(errorColor + "Error setting session timeouts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void InsertToDatabase(boolean availableToInsert, int patientID, String name, int age, String gender, String contactNumber, String address) {
        if (!availableToInsert) {
            return;
        }
        SetSessionTimer();
        openConnection();
        try {
            String insertQuery = """
        INSERT INTO Patient (patient_ID, age, gender, patient_name, contact_number, address)
        VALUES (?, ?, ?, ?, ?, ?);
        """;

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1, patientID);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, contactNumber);
            preparedStatement.setString(6, address);

            preparedStatement.executeUpdate();
            System.out.println(successColor + "Patient Registered Successfully!");
        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void InsertToDatabase(double Services, int Id) {
        SetSessionTimer();
        openConnection();
        try {
            String insertQuery = """
        INSERT INTO MedicalServices (patient_ID, mst_ID)
        VALUES (?, ?);
        """;

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1, Id);
            preparedStatement.setDouble(2, Services);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public boolean PatientIDExists(int PatientID) {
        boolean exists = false;

        openConnection();
        try {
            String query = "SELECT * FROM Patient WHERE patient_ID = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, PatientID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println("Error during database query: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return exists;
    }

    public boolean DepartmentServicesExists(double Services, int Id) {
        boolean exists = false;
        String message = successColor + "\nDepartment and Service successfully added to patient\n";

        openConnection();
        try {
            String query = "SELECT * FROM MedicalServices WHERE patient_ID = ? AND mst_ID = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, Id);
            preparedStatement.setDouble(2, Services);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                message = wColor + "\n\nDepartment and service already exists for this patient.";
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println("Error during database query: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        System.out.println(message);
        return exists;
    }

    public void DepartmentServicesDelete(int patientId, int mstId) {
        String message = successColor + "\nDepartment and Service successfully removed\n";
        SetSessionTimer();
        openConnection();
        try {
            String query = "SELECT * FROM MedicalServices WHERE patient_ID = ? AND mst_ID = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setDouble(2, mstId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                message = errorColor + "Department and service do not exist for this patient.";
            } else {
                String deleteQuery = "DELETE FROM MedicalServices WHERE patient_ID = ? AND mst_ID = ?";
                PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, patientId);
                deleteStatement.setDouble(2, mstId);

                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            message = "Error during database deletion: " + e.getMessage();
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        System.out.println(message);
    }

    public void MedicineDelete(int patientId, int medID) {
        String message = successColor + "\nMedicine successfully removed\n";
        SetSessionTimer();
        openConnection();
        try {
            String query = "SELECT * FROM MedicineExpenses WHERE patient_ID = ? AND med_id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setDouble(2, medID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                message = errorColor + "Medicine does not exist for this patient.";
            } else {
                String deleteQuery = "DELETE FROM MedicineExpenses WHERE patient_ID = ? AND med_id = ?";
                PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, patientId);
                deleteStatement.setDouble(2, medID);

                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            message = "Error during database deletion: " + e.getMessage();
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        System.out.println(message);
    }




    public void FetchServices(int Id) {
        try {
            SetSessionTimer();
            openConnection();
            con.setAutoCommit(true);
            String fetchings = """
            SELECT 
                ms.services_ID, 
                ms.patient_ID, 
                mst.Department, 
                mst.ServiceName, 
                mst.Price
            FROM 
                MedicalServices ms
            JOIN 
                MedicalServicesTags mst ON ms.mst_ID = mst.mst_ID
            JOIN 
                Patient p ON ms.patient_ID = p.patient_ID
            WHERE 
                ms.patient_ID = ?;""";

            PreparedStatement preparedStatement = con.prepareStatement(fetchings);
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n" + titleColor + "⫍⫍ Department Visited and Services Used ⫎⫎" + wColor);
            System.out.println("\f-------------------------------------------\f");
            boolean hasResults = false;

            while (resultSet.next()) {
                hasResults = true;
                System.out.println("Service ID   : " + resultSet.getString("services_ID"));
                System.out.println("Department   : " + resultSet.getString("Department"));
                System.out.println("Service Name : " + resultSet.getString("ServiceName"));
                System.out.println("Price        : " + resultSet.getDouble("Price"));
                System.out.println("\f-------------------------------------------\f");
            }

            if (!hasResults) {
                System.out.println(errorColor + "No services found for patient ID: " + Id);
            }

            System.out.println(loadingColor + "\nPress enter to continue...");
            new Scanner(System.in).nextLine();

        } catch (SQLException e) {
            System.out.println("Error during service fetching: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void FetchMedicine(int Id) {
        try {
            SetSessionTimer();
            openConnection();
            con.setAutoCommit(true);
            String fetchings = "SELECT * FROM MedicineExpenses WHERE patient_id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(fetchings);
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n" + titleColor + "⫍⫍ Medicine ⫎⫎" + wColor);
            System.out.println("\f-------------------------------------------\f");
            boolean hasResults = false;

            while (resultSet.next()) {
                hasResults = true;
                System.out.println("Medicine ID   : " + resultSet.getInt("med_id"));
                System.out.println("Medicine Name : " + resultSet.getString("medicine_name"));
                System.out.println("Quantity      : " + resultSet.getInt("quantity"));
                System.out.println("Price         : " + resultSet.getInt("total_cost"));
                System.out.println("\f-------------------------------------------\f");
            }

            if (!hasResults) {
                System.out.println(errorColor + "No medicines found for patient ID: " + Id);
            }

            System.out.println(loadingColor + "\nPress enter to continue...");
            new Scanner(System.in).nextLine();

        } catch (SQLException e) {
            System.out.println("Error during medicine fetching: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void GetUserInformation() {
        try {
            SetSessionTimer();
            openConnection();
            con.setAutoCommit(true);
            String query = "SELECT * FROM Patient";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            displayUserInformation(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    public void GetUserInformation(int id) {
        try {
            SetSessionTimer();
            openConnection();
            con.setAutoCommit(true);
            String query = "SELECT * FROM Patient WHERE patient_ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println(wColor + "No user with this ID found");
            } else {
                displayUserInformation(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    public void displayUserInformation(ResultSet resultSet) {
        try {
            SetSessionTimer();
            System.out.println(wColor + "----------------------------------------");
            do {
                System.out.println("Patient ID     : " + resultSet.getInt("patient_ID"));
                System.out.println("Patient Name   : " + resultSet.getString("patient_name"));
                System.out.println("Age            : " + resultSet.getInt("age"));
                System.out.println("Gender         : " + resultSet.getString("gender"));
                System.out.println("Contact Number : " + resultSet.getString("contact_number"));
                System.out.println("Address        : " + resultSet.getString("address"));
                System.out.println("----------------------------------------");
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String GetName(int id) {
        try {
            SetSessionTimer();
            openConnection();
            con.setAutoCommit(true);
            String query = "SELECT patient_name FROM Patient WHERE patient_ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getString("patient_name") : null;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public void MedicineExpenses(String name, int SelectedID, int quantity, int totalcost) {
        try {
            SetSessionTimer();
            openConnection();
            con.setAutoCommit(true);
            String insertQuery = """
            INSERT INTO MedicineExpenses (patient_id, medicine_name, quantity, total_cost)
            VALUES (?, ?, ?, ?);""";

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1, SelectedID);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, totalcost);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error during database insert: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }



    public double Billing(int patientID) {
        double totalExpenses = -1; // Initialize to -1 for failure by default
        openConnection();
        try {
            con.setAutoCommit(true);

            // Step 1: Delete from Billing table
            String deleteQuery = "DELETE FROM Billing WHERE patient_ID = ?;";
            try (PreparedStatement deleteStatement = con.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, patientID);
                deleteStatement.executeUpdate();
            }

            // Step 2: Calculate total expenses
            String totalQuery = """
            SELECT
                COALESCE(ms_expenses.total_services, 0) + COALESCE(me_expenses.total_medicines, 0) AS total_expenses
            FROM
                Patient p
            LEFT JOIN (
                SELECT ms.patient_ID, SUM(mst.Price) AS total_services
                FROM MedicalServices ms
                JOIN MedicalServicesTags mst ON ms.mst_ID = mst.mst_ID
                GROUP BY ms.patient_ID
            ) ms_expenses ON p.patient_ID = ms_expenses.patient_ID
            LEFT JOIN (
                SELECT patient_ID, SUM(total_cost) AS total_medicines
                FROM MedicineExpenses
                GROUP BY patient_ID
            ) me_expenses ON p.patient_ID = me_expenses.patient_ID
            WHERE p.patient_ID = ?;
        """;

            try (PreparedStatement totalStatement = con.prepareStatement(totalQuery)) {
                totalStatement.setInt(1, patientID);
                try (ResultSet totalResult = totalStatement.executeQuery()) {
                    if (totalResult.next()) {
                        totalExpenses = totalResult.getDouble("total_expenses");
                        if (totalExpenses == 0) {
                            System.out.println("No services or medicines detected for patient ID: " + patientID);
                            return -1;
                        }

                        // Step 3: Insert into Billing table
                        String insertQuery = "INSERT INTO Billing (patient_id, expenses) VALUES (?, ?);";
                        try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
                            insertStatement.setInt(1, patientID);
                            insertStatement.setDouble(2, totalExpenses);
                            insertStatement.executeUpdate();
                        }
                    } else {
                        System.out.println("No patient found with ID: " + patientID);
                        return -1;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during database operation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return totalExpenses;
    }

    public void FinalBill(int patientID, double amount, double totalcost) {
        try {
            openConnection();

            boolean paymentStatus = false;
            double remainingBalance = totalcost;

            if (amount >= totalcost) {
                paymentStatus = true;
                remainingBalance = 0;
            } else {
                remainingBalance -= amount;
            }

            // Remove existing entry
            String deleteQuery = "DELETE FROM PaidCustomers WHERE patient_id = ?;";
            try (PreparedStatement deleteStatement = con.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, patientID);
                deleteStatement.executeUpdate();
            }

            // Insert payment details
            String insertQuery = """
            INSERT INTO PaidCustomers (patient_id, payment_status, remaining_balance)
            VALUES (?, ?, ?);
        """;
            try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
                insertStatement.setInt(1, patientID);
                insertStatement.setBoolean(2, paymentStatus);
                insertStatement.setDouble(3, remainingBalance);
                insertStatement.executeUpdate();
            }

            System.out.println("Bill processed for Patient ID: " + patientID);
            System.out.println("Total cost: " + totalcost);
            System.out.println("Amount paid: " + amount);
            System.out.println("Remaining balance: " + remainingBalance);
            System.out.println("Payment status: " + (paymentStatus ? "Paid" : "Not Paid"));
        } catch (SQLException e) {
            System.out.println("Error during database operation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public double CheckBalance(int patientID) {
        double remainingBalance = -1;
        openConnection();
        try {
            String query = "SELECT expenses FROM Billing WHERE patient_id = ?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, patientID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        remainingBalance = resultSet.getDouble("expenses");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during database query: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return remainingBalance;
    }

    public double CheckBill(int patientID) {
        double remainingBalance = -1;
        openConnection();
        try {
            String query = """
            SELECT COALESCE(b.expenses, 0) AS expenses
            FROM Billing b
            WHERE b.patient_ID = ?;
        """;
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, patientID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        remainingBalance = resultSet.getDouble("expenses");
                        System.out.println("Expenses for Patient ID " + patientID + ": " + remainingBalance);
                    } else {
                        System.out.println("No data found for Patient ID " + patientID);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return remainingBalance;
    }







}






