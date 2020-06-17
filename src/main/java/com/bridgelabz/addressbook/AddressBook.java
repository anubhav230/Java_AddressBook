package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    static int count=0;
    static int flag=1;
    static Person person = new Person();
    static Scanner scanner = new Scanner(System.in);
    static List<Person> book=new ArrayList<>();
    //main methos to start the execution of program
    public static void main(String[] args) {
        while(flag==1) {
            Scanner scanner2 = new Scanner(System.in);

            System.out.println("Select option: \n" +
                    " 1 = Add person\n" +
                    " 2 = Display\n" +
                    " 3 = Edit person\n" +
                    " 4 = Delete aperson\n" +
                    " 5 = Quit");

            int choice = scanner2.nextInt();
            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    print();
                    break;
                case 3:
                    editPerson();
                    break;
                case 4:
                    deletePerson();
                    break;
                case 5:
                    flag = 2;
                    break;
            }
        }
    }

    //method to add person full details
    public static void addPerson() {

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
        count++;

    }
    //method for deleting person from addressbook
    public static void deletePerson() {
        if (count>0) {
            System.out.println("Enter first name to delete from addressbook");
            String name = scanner.next();
            boolean isFound=false;
            int index=0;
            for (int i=0; i<book.size(); i++) {
                if (name.equals(book.get(i).getFristName())) {
                    isFound=true;
                    index=i;
                }
                if (isFound) {
                    book.remove(index);
                    count--;
                    System.out.println("Person has been removed from book");
                } else
                    System.out.println("No person exist by this name");
            }


        }
    }
    //method for printing person details
    public static void print() {
        System.out.println(book);
    }

    //method for edit person details by his first name
    public static void editPerson(){
        if (count>0) {
            System.out.println("Enter first name of the person for updating details");
            String name=scanner.next();
            boolean isFound=false;
            int index=0;
            for (int i=0; i<book.size(); i++) {
                if (name.equals(book.get(i).getFristName())) {
                    isFound=true;
                    index=i;
                    break;
                }

            }
            if (isFound) {
                System.out.println("Enter mobile number");
                book.get(index).setMobileNumber(scanner.nextLong());
                System.out.println("Enter State");
                book.get(index).setState(scanner.next());
                System.out.println("Enter city");
                book.get(index).setCity(scanner.next());
                System.out.println("Enter zip");
                book.get(index).setZip(scanner.nextInt());

            } else
                System.out.println("person not exist");

        }

    }
}
