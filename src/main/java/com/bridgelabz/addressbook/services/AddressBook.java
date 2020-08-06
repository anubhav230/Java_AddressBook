package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.config.DBOperation;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utility.CSVFileOperation;
import com.bridgelabz.addressbook.utility.JSONFileOperation;

import java.util.*;
import java.util.regex.Pattern;

public class AddressBook extends Thread implements AddressBookInterface {
    DBOperation dbOperation = new DBOperation();
    JSONFileOperation readFromJSON = new JSONFileOperation();
    CSVFileOperation csv = new CSVFileOperation();
    String JSON_FILE_PATH = "./src/main/resources/AddressBook.json";
    String CSV_FILE_PATH = "./src/main/resources/AddressBook.csv";
    String GSON_FILE_PATH = "./src/main/resources/AddressBookGson.json";
    static Scanner scanner = new Scanner(System.in);
    List<Person> book = new ArrayList<>();
    Person person = new Person();
    private String firstName;
    private String lastName;

    public boolean patternValidation(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(value).matches())
            return false;
        else {
            System.out.println("---------please enter correct input--------");
            return true;
        }
    }

    public void addFirstName() {
        String NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}$";
        System.out.println("Enter First name of person");
        firstName = scanner.next();
        if (patternValidation(firstName, NAME_PATTERN))
            addFirstName();
    }

    public void addLastName() {
        String NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}$";
        System.out.println("Enter Last name of person");
        lastName = scanner.next();
        if (patternValidation(lastName, NAME_PATTERN))
            addLastName();
    }

    public void addMobileNumber() {
        String MOBILE_NUMBER_PATTERN = "^[1-9][0-9]{9}";
        System.out.println("Enter Mobile number of person");
        long mobileNumber = scanner.nextLong();
        if (patternValidation(String.valueOf(mobileNumber), MOBILE_NUMBER_PATTERN))
            addMobileNumber();
        person.setMobileNumber(mobileNumber);
    }

    public void addState() {
        String ADDRESS_PATTERN = "[a-z]{2,}";
        System.out.println("Enter State of person");
        String State = scanner.next();
        if (patternValidation(State, ADDRESS_PATTERN))
            addState();
        person.setState(State);
    }

    public void addCity() {
        String ADDRESS_PATTERN = "[a-z]{2,}";
        System.out.println("Enter City of person");
        String city = scanner.next();
        if (patternValidation(city, ADDRESS_PATTERN))
            addCity();
        person.setCity(city);
    }

    public void addZip() {
        String ZIP_PATTERN = "[0-9]{6}";
        System.out.println("Enter zip");
        int zip = scanner.nextInt();
        if (patternValidation(String.valueOf(zip), ZIP_PATTERN))
            addZip();
        person.setZip(zip);
    }

    public boolean duplicateCheck(String firstName, String lastName) {
        for (Person person : book) {
            if (firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName())) {
                System.out.println("Person already exist");
                return true;
            }
        }
        return false;
    }


    /**
     * method to add person full details
     */
    public void addPerson() {
        addFirstName();
        addLastName();
        boolean result = duplicateCheck(firstName, lastName);
        if (!result) {
            person.setFirstName(firstName);
            person.setLastName(lastName);
            addMobileNumber();
            addState();
            addCity();
            addZip();
            book.add(person);
            dbOperation.insertData(person.getFirstName(), person.getLastName(), person.getMobileNumber(),
                    person.getState(), person.getCity(), person.getZip());
        }
    }

    /**
     * method for deleting person from AddressBook
     */
    public void deletePerson() {
        System.out.println("Enter first name to delete from addressBook");
        String firstName = scanner.next();
        System.out.println("Enter last name to delete from addressBook");
        String lastName = scanner.next();
        for (int i = 0; i < book.size(); i++) {
            if (firstName.equals(book.get(i).getFirstName()) &&
                    lastName.equals(book.get(i).getLastName())) {
                book.remove(i);
                System.out.println("Person has been removed from book");
            } else {
                System.out.println("Person not exist");
            }
        }
    }

    /**
     * method for printing person details
     */
    public void print() {
        book.forEach(System.out::println);
        dbOperation.select();
    }

    /**
     * method for edit person details by his first name
     */
    public void editPerson() {
        System.out.println("Enter first name of the person for updating details");
        String firstName = scanner.next();
        System.out.println("Enter last name of the person for updating details");
        String lastName = scanner.next();
        boolean isFound = false;
        int index = 0;
        for (int i = 0; i < book.size(); i++)
            if (firstName.equals(book.get(i).getFirstName()) && lastName.equals(book.get(i).getLastName())) {
                isFound = true;
                index = i;
                break;
            }
        if (isFound) {
            System.out.println("Choose option: \n" +
                    " 1 = Edit mobile number\n" +
                    " 2 = Edit State\n" +
                    " 3 = Edit city\n" +
                    " 4 = Edit zip");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Enter mobile number");
                    book.get(index).setMobileNumber(scanner.nextLong());
                    break;
                case 2:
                    System.out.println("Enter State");
                    book.get(index).setState(scanner.next());
                    break;
                case 3:
                    System.out.println("Enter city");
                    book.get(index).setCity(scanner.next());
                    break;
                case 4:
                    System.out.println("Enter zip");
                    book.get(index).setZip(scanner.nextInt());
                    break;
            }
        } else
            System.out.println("person not exist");
    }

    /**
     * method for sorting data with first name by bubble short
     */
    public void sortByName() {
        book.sort(Comparator.comparing(Person::getFirstName));
        System.out.println("---Details has been shorted---");
    }

    /**
     * method for sorting data by zip code with bubble sort
     */
    public void sortByZip() {
        book.sort(Comparator.comparing(Person::getZip));
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
        int index;
        System.out.println("Enter City name");
        String city = scanner.next();
        System.out.println("Enter State name");
        String state = scanner.next();
        for (int i = 0; i < book.size(); i++)
            if (book.get(i).getState().equals(state) && book.get(i).getCity().equals(city)) {
                index = i;
                System.out.println(book.get(index).getFirstName() +
                        " " + book.get(index).getLastName() +
                        " " + book.get(index).getState() +
                        " " + book.get(index).getMobileNumber() +
                        " " + book.get(index).getCity() +
                        " " + book.get(index).getZip());
            }

    }

    /**
     * Method for printing details if state or city exist
     */
    public void viewByCityOrState() {
        int index;
        System.out.println("Enter City name");
        String city = scanner.next();
        System.out.println("Enter State name");
        String state = scanner.next();
        for (int i = 0; i < book.size(); i++)
            if (book.get(i).getState().equals(state) || book.get(i).getCity().equals(city)) {
                index = i;
                System.out.println(book.get(index).getFirstName() +
                        " " + book.get(index).getLastName() +
                        " " + book.get(index).getState() +
                        " " + book.get(index).getMobileNumber() +
                        " " + book.get(index).getCity() +
                        " " + book.get(index).getZip());
            }
    }

    /**
     * method for read data in list
     */
    public void readJsonDataInList() {
        readFromJSON.readFromJSON(book, JSON_FILE_PATH);
    }

    /**
     * method for write in json file
     */
    public void writeInJSON() {
        readFromJSON.writeJson(book, JSON_FILE_PATH);
    }

    public void writeInCSVFile() {
        csv.writeFile(book, CSV_FILE_PATH);
    }

    public void readFromCSVFile() {
        book = csv.loadDataFromFile(CSV_FILE_PATH);
    }

    public void readFromJSON() {
        book = readFromJSON.readFromSimpleGSON(GSON_FILE_PATH);
    }

    public void writeInGSON() {
        readFromJSON.writeGSON(book, GSON_FILE_PATH);
    }

    public void editInDB() {
        System.out.println("Enter first name of the person for updating details");
        String firstName = scanner.next();
        System.out.println("Choose option: \n" +
                " 1 = Edit mobile number\n" +
                " 2 = Edit State\n" +
                " 3 = Edit city\n" +
                " 4 = Edit zip");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("Enter mobile number");
                Long mobileNumber = scanner.nextLong();
                dbOperation.editData(firstName, mobileNumber);
                break;
            case 2:
                System.out.println("Enter State");
                String state = scanner.next();
                dbOperation.editData(firstName, 2, state);
                break;
            case 3:
                System.out.println("Enter city");
                String city = scanner.next();
                dbOperation.editData(firstName, 3, city);
                break;
            case 4:
                System.out.println("Enter zip");
                int zip = scanner.nextInt();
                dbOperation.editData(firstName, zip);
                break;
        }
    }

    public void deletePersonData() {
        System.out.println("Enter first name of the person for updating details");
        String firstName = scanner.next();
        dbOperation.deletePerson(firstName);
    }
}