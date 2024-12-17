package org.example;

public class Database {
    // serves as temporary database
    private static String[][] _userInformation = {
            {"id", "name", "age", "gender", "contact_number"},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""}
    };

    public boolean GetUserInformation(String name) {
        for(int i = 1; i < _userInformation.length; i++){
            if(_userInformation[i][1].toLowerCase().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void SetUserInformation(int id, String name, int age, String gender, int contact_number) {
        _userInformation[id][0] = String.valueOf(id);
        _userInformation[id][1] = name;
        _userInformation[id][2] = String.valueOf(age);
        _userInformation[id][3] = gender;
        _userInformation[id][4] = String.valueOf(contact_number);
    }
}
