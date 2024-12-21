// this class inherited the attributes from Person class
package org.example;

import java.sql.*;
import java.util.Scanner;

public class Patient extends Person {
    public Patient(int ID, String name, int age, String gender, String contact_number, String address) {
        Id = ID;
        Name = name;
        Age = age;
        Gender = gender;
        ContactNumber = contact_number;
        Address = address;
    }

    public void PatientDatabase(boolean check) {

    }

    public boolean Validation(){
        DB db = new DB();
        if(db.PatientIDExists(Id)){
            System.out.println("Patient ID " + Id + " already exists.");
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

//        if(check){ // this will check if the username/patient name already exists in the temporary database
//            System.out.println("This patient already exist!");
//            return false;
//        } else {
//            System.out.println("Adding...");
//            return true;
//        }

        return true;
    }
}
