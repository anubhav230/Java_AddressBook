package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.AddressBookInterface;
import com.bridgelabz.addressbook.models.Person;
import java.util.*;

public class AddressBook implements AddressBookInterface {
    static int count = 0;
    static Scanner scanner = new Scanner(System.in);
    static List<Person> book = new ArrayList<>();

    /**
     * method to add person full details
     */
    public void addPerson() {
        boolean flag = true;
        if (count > 0) {
            System.out.println("Enter First name of person");
            String name = scanner.next();
            for (Person person : book) {
                if (name.equals(person.getFirstName())) {
                    System.out.println("Person already exist");
                    flag = false;
                }
            }
        }
        if (flag) {
            Person person = new Person();
            System.out.println("Enter first name");
            String firstName = scanner.next();
            person.setFirstName(firstName);
            System.out.println("Enter last name");
            String lastName = scanner.next();
            person.setLastName(lastName);
            System.out.println("Enter mobile number");
            long mobileNumber = scanner.nextLong();
            person.setMobileNumber(mobileNumber);
            System.out.println("Enter State");
            String state = scanner.next();
            person.setState(state);
            System.out.println("Enter city");
            String city = scanner.next();
            person.setCity(city);
            System.out.println("Enter zip");
            int zip = scanner.nextInt();
            person.setZip(zip);
            book.add(person);
            count++;
        }
    }

    /**
     * method for deleting person from AddressBook
     */
    public void deletePerson() {
        if (count > 0) {
            System.out.println("Enter first name to delete from addressbook");
            String name = scanner.next();
            boolean isFound = false;
            int index = 0;
            for (int i = 0; i < book.size(); i++) {
                if (name.equals(book.get(i).getFirstName())) {
                    isFound = true;
                    index = i;

                    if (isFound) {
                        book.remove(index);
                        count--;
                        System.out.println("Person has been removed from book");
                    } else
                        System.out.println("No person exist by this name");
                }
            }


        }
    }

    /**
     * method for printing person details
     */
    public void print() {
        System.out.println(book);
    }

    /**
     * method for edit person details by his first name
     */
    public static void editPerson() {
        if (count > 0) {
            System.out.println("Enter first name of the person for updating details");
            String name = scanner.next();
            boolean isFound = false;
            int index = 0;
            for (int i = 0; i < book.size(); i++) {
                if (name.equals(book.get(i).getFirstName())) {
                    isFound = true;
                    index = i;
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

    /**
     * method for sorting data with first name by bubble short
     */
    public void sortByName() {
        for (int i = 0; i < book.size(); i++) {
            for (int j = 1; j < book.size() - i; j++) {
                if (book.get(j - 1).getFirstName().compareToIgnoreCase(book.get(j).getFirstName()) > 0) {
                    Collections.swap(book, j - 1, j);
                } else if (book.get(j - 1).getFirstName().compareToIgnoreCase(book.get(j).getFirstName()) == 0) {
                    if (book.get(j - 1).getFirstName().compareTo(book.get(j).getFirstName()) > 0) {
                        Collections.swap(book, j - 1, j);
                    }

                }
            }
        }
        System.out.println("---Details has been shorted---");

    }

    /**
     * method for sorting data by zip code with bubble sort
     */
    public void sortByZip() {
        for (int i = 0; i < book.size(); i++) {
            for (int j = 1; j < book.size() - i; j++) {
                if (book.get(j - 1).getZip() > book.get(j).getZip()) {
                    Collections.swap(book, j - 1, j);
                } else if (book.get(j - 1).getZip() == book.get(j).getZip()) {
                    if (book.get(j - 1).getFirstName().compareTo(book.get(j).getFirstName()) > 0) {
                        Collections.swap(book, j - 1, j);
                    }

                }
            }
        }
        System.out.println("---Details has been shorted---");
    }

    /**
     * method for sorting by collection sort
     */
    public void sortByState() {
        book.sort(Comparator.comparing(Person::getState));
        System.out.println("---Details has been shorted---");
    }

    /**
     * method for sorting by collection sort
     */
    public void sortByCity() {
        book.sort(Comparator.comparing(Person::getCity));
        System.out.println("---Details has been shorted---");
    }

    /**
     * Method for printing details if state and city both exist
     */
    public void viewByCityAndState() {
        if (count > 0) {
            System.out.println("Enter City name");
            String city = scanner.next();
            System.out.println("Enter State name");
            String state = scanner.next();
            int index = 0;
            for (int i = 0; i < book.size(); i++) {
                if (book.get(i).getState().equals(state) && book.get(i).getCity().equals(city)) {
                    index = i;
                    System.out.println(book.get(index).getFirstName() + " " + book.get(index).getLastName() + " " + book.get(index).getState() + " " + book.get(index).getMobileNumber() + " " + book.get(index).getCity() + " " + book.get(index).getZip());
                }
            }
        }
    }

    /**
     * Method for printing details if state or city exist
     */
    public void viewByCityOrState() {
        if (count > 0) {
            System.out.println("Enter City name");
            String city = scanner.next();
            System.out.println("Enter State name");
            String state = scanner.next();
            int index = 0;
            for (int i = 0; i < book.size(); i++) {
                if (book.get(i).getState().equals(state) || book.get(i).getCity().equals(city)) {
                    index = i;
                    System.out.println(book.get(index).getFirstName() + " " + book.get(index).getLastName() + " " + book.get(index).getState() + " " + book.get(index).getMobileNumber() + " " + book.get(index).getCity() + " " + book.get(index).getZip());
                }
            }
        }
    }
}