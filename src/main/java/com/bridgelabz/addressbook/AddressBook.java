package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    static List<Person> book=new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        System.out.println("Enter first name");
        String firstName = scanner.next();
        person.setFristName(firstName);
        System.out.println("Enter last name");
        String lastName = scanner.next();
        person.setLastName(lastName);
        System.out.println("Enter mobile number");
        long mobileNumber = scanner.nextLong();
        person.setMobileNumber(mobileNumber);
        System.out.println("Enter State");
        String stete = scanner.next();
        person.setState(stete);
        System.out.println("Enter city");
        String city = scanner.next();
        person.setCity(city);
        System.out.println("Enter zip");
        int zip = scanner.nextInt();
        person.setZip(zip);
        book.add(person);
        System.out.println(book);
    }
}
