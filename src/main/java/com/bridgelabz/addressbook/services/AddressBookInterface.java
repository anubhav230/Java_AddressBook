package com.bridgelabz.addressbook.services;

public interface AddressBookInterface {
    void addPerson();
    void deletePerson();
    void print();
    void sortByName();
    void sortByZip();
    void sortByState();
    void sortByCity();
    void viewByCityAndState();
    void viewByCityOrState();
    void editPerson();
    void readJsonDataInList();
    void writeInCSVFile();
    void readFromCSVFile();
    void writeInJSON();
}
