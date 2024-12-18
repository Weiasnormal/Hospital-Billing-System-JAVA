package org.example;

public class Database {
    // serves as temporary database
    private static String[][] _patientInformation = {
            {"ID", "Name", "Age", "Gender", "Contact Number"},
            {"1", "Hacihman", "17", "Male", "09123456789"},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""}
    };

    public void GetUserInformation() {
        for(int i = 1; i < _patientInformation.length; i++){
            System.out.println(_patientInformation[0][0] + "             : " + _patientInformation[i][0]);
            System.out.println(_patientInformation[0][1] + "           : " + _patientInformation[i][1]);
            System.out.println(_patientInformation[0][2] + "            : " + _patientInformation[i][2]);
            System.out.println(_patientInformation[0][3] + "         : " + _patientInformation[i][3]);
            System.out.println(_patientInformation[0][4] + " : " + _patientInformation[i][4]);
            System.out.println();
        }
    }

    public boolean GetUserInformation(int id) {
        for(int i = 1; i < _patientInformation.length; i++){
            if(_patientInformation[i][0].equals(String.valueOf(id))){
                return true;
            }
        }
        System.out.println("Patient not found!");
        return false;
    }

    public void GetUserInformation(int id, boolean exists) {
        if(exists){
            System.out.println(_patientInformation[0][0] + "             : " + _patientInformation[id][0]);
            System.out.println(_patientInformation[0][1] + "           : " + _patientInformation[id][1]);
            System.out.println(_patientInformation[0][2] + "            : " + _patientInformation[id][2]);
            System.out.println(_patientInformation[0][3] + "         : " + _patientInformation[id][3]);
            System.out.println(_patientInformation[0][4] + " : " + _patientInformation[id][4]);
        }
    }

    public boolean GetUserInformation(String name) {
        for(int i = 1; i < _patientInformation.length; i++){
            if(_patientInformation[i][1].toLowerCase().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void SetUserInformation(int id, String name, int age, String gender, String contact_number) {
        _patientInformation[id][0] = String.valueOf(id);
        _patientInformation[id][1] = name;
        _patientInformation[id][2] = String.valueOf(age);
        _patientInformation[id][3] = gender;
        _patientInformation[id][4] = String.valueOf(contact_number);
    }

    private static String[][] _patientDetails_PrescribedMedicine = {
        {"ID", "Name", "Total Cost", "Foreign Key"},
        {"", "", "", ""},
        {"", "", "", ""},
        {"", "", "", ""},
        {"", "", "", ""},
        {"", "", "", ""}
    };

    private static String[][] _patientDetails_DepartmentVisited = {
            {"ID", "Name", "Total Cost", "Foreign Key"},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""}
    };

    private static String[][] _patientDetails_Services = {
            {"ID", "Name", "Total Cost", "Foreign Key"},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""}
    };
}
