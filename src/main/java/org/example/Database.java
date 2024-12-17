package org.example;

public class Database {
    // serves as temporary database
    private static String[][] _userInformation = {
            {"ID", "Name", "Age", "Gender", "Contact Number"},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""}
    };

    public void GetUserInformation() {
        for(int i = 1; i < _userInformation.length; i++){
            System.out.println(_userInformation[0][0] + "             : " + _userInformation[i][0]);
            System.out.println(_userInformation[0][1] + "           : " + _userInformation[i][1]);
            System.out.println(_userInformation[0][2] + "            : " + _userInformation[i][2]);
            System.out.println(_userInformation[0][3] + "         : " + _userInformation[i][3]);
            System.out.println(_userInformation[0][4] + " : " + _userInformation[i][4]);
            System.out.println();
        }
    }

    public boolean GetUserInformation(String name) {
        for(int i = 1; i < _userInformation.length; i++){
            if(_userInformation[i][1].toLowerCase().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void SetUserInformation(int id, String name, int age, String gender, String contact_number) {
        _userInformation[id][0] = String.valueOf(id);
        _userInformation[id][1] = name;
        _userInformation[id][2] = String.valueOf(age);
        _userInformation[id][3] = gender;
        _userInformation[id][4] = String.valueOf(contact_number);
    }
}
