// this class inherited the attributes from Person class
package org.example;

public class Patient extends Person {
    public Patient(String name, int age, String gender, int contact_number) {
        Id = 1;
        Name = name;
        Age = age;
        Gender = gender;
        ContactNumber = contact_number;
        Id++;
    }

    public void Validation(){
        Database database = new Database();
        boolean check = database.GetUserInformation(Name);
        if(check){ // this will check if the username/patient name already exists in the temporary database
            System.out.println("This patient already exist!");
        } else {
            database.SetUserInformation(Id, Name, Age, Gender, ContactNumber);
            System.out.println("Patient Registered Successfully!");
        }


    }
}
