package com.bridgelabz.addressbook.models;

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
}
