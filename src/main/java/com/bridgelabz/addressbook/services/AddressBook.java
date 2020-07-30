package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class AddressBook implements AddressBookInterface {

    static int count = 0;
    static Scanner scanner = new Scanner(System.in);
    static List<Person> book = new ArrayList<>();
    Person person = new Person();

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
        String firstName = scanner.next();
        if (patternValidation(firstName, NAME_PATTERN))
            addFirstName();
        person.setFirstName(firstName);
    }

    public void addLastName() {
        String NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}$";
        System.out.println("Enter Last name of person");
        String lastName = scanner.next();
        if (patternValidation(lastName, NAME_PATTERN))
            addLastName();
        person.setLastName(lastName);
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

    /**
     * method to add person full details
     */
    public void addPerson() {
        boolean flag = true;
        if (count > 0) {
            System.out.println("Enter First name of person");
            String firstName = scanner.next();
            System.out.println("Enter Last name of person");
            String lastName = scanner.next();
            for (Person person : book) {
                if (firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName())) {
                    System.out.println("Person already exist");
                    flag = false;
                }
            }
        }
        if (flag) {
            addFirstName();
            addLastName();
            addMobileNumber();
            addState();
            addCity();
            addZip();
            book.add(person);
            count++;
        }
    }

    /**
     * method for deleting person from AddressBook
     */
    public void deletePerson() {
        if (count > 0) {
            System.out.println("Enter first name to delete from addressBook");
            String firstName = scanner.next();
            System.out.println("Enter last name to delete from addressBook");
            String lastName = scanner.next();
            for (int i = 0; i < book.size(); i++) {
                if (firstName.equals(book.get(i).getFirstName()) &&
                        lastName.equals(book.get(i).getLastName())) {
                    book.remove(i);
                    count--;
                    System.out.println("Person has been removed from book");
                } else {
                    System.out.println("Person not exist");
                }
            }
        }
    }

    /**
     * method for printing person details
     */
    public void print() {
        book.forEach(System.out::println);
    }

    /**
     * method for edit person details by his first name
     */
    public void editPerson() {
        if (count > 0) {
            System.out.println("Enter first name of the person for updating details");
            String name = scanner.next();
            boolean isFound = false;
            int index = 0;
            for (int i = 0; i < book.size(); i++)
                if (name.equals(book.get(i).getFirstName())) {
                    isFound = true;
                    index = i;
                    break;
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
        if (count > 0) {
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
    }

    /**
     * Method for printing details if state or city exist
     */
    public void viewByCityOrState() {
        int index;
        if (count > 0) {
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
    }


    public void writeInJSON() {
        JSONObject personDetails = new JSONObject();

        personDetails.put("FirstName", person.getFirstName());
        personDetails.put("LastName", person.getLastName());
        personDetails.put("MobileNumber", person.getMobileNumber());
        personDetails.put("State", person.getState());
        personDetails.put("City", person.getCity());
        personDetails.put("Zip", person.getZip());
        JSONObject addressBookObject = new JSONObject();
        addressBookObject.put("addressBook", personDetails);
        JSONArray personJSON = new JSONArray();
        personJSON.add(addressBookObject);
        try (FileWriter file = new FileWriter("./src/main/resources/AddressBook.json")) {

            file.write(personJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromJSON() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("./src/main/resources/AddressBook.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray addressBook = (JSONArray) obj;
            System.out.println(addressBook);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}

