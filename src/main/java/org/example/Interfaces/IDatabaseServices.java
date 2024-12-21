package org.example.Interfaces;

public interface IDatabaseServices {
    public boolean InsertToDatabase(String Name, int Age, int Gender, String ContactInformation);
    public boolean UpdateDatabase(String FirstName, String LastName, String Specialty);
    public boolean DeleteDatabase(String ID);

}
