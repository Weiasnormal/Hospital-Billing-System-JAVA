// this class inherited the attributes from Person class
package org.example;

public class Patient extends Person {
    public Patient(String name, int age, String gender, String contact_number) {
        Id = 0;
        Name = name;
        Age = age;
        Gender = gender;
        ContactNumber = contact_number;
        Id++;
    }

    public void Validation(){
        Database database = new Database();
        boolean check = database.GetUserInformation(Name);
        if(!(Gender.equalsIgnoreCase("male") || Gender.equalsIgnoreCase("female"))) {
            System.out.println("Invalid Gender!");
            return;
        }

        if(ContactNumber.length() != 11){
            System.out.println("Invalid Contact Number!");
            return;
        }

        if(check){ // this will check if the username/patient name already exists in the temporary database
            System.out.println("This patient already exist!");
        } else {
            database.SetUserInformation(Id, Name, Age, Gender, ContactNumber);
            System.out.println("Patient Registered Successfully!");
        }


    }
}
