package com.bridgelabz.addressbook.main;

import com.bridgelabz.addressbook.services.AddressBook;

import java.util.Scanner;

public class AddressBookApp {
    static int flag = 1;
    static Scanner scanner = new Scanner(System.in);
    public static AddressBook addPerson = new AddressBook();
    public static void main(String[] args) {
        System.out.println("Choose option: \n" +
                " 1 = JSON\n" +
                " 2 = CSV\n");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addPerson.readJsonDataInList();
                break;
            case 2:
                addPerson.readFromCSVFile();
                break;
        }


        while (flag == 1) {
            //Scanner scanner2 = new Scanner(System.in);
            System.out.println("Choose option: \n" +
                    " 1 = Add person\n" +
                    " 2 = Display\n" +
                    " 3 = Edit person\n" +
                    " 4 = Delete person\n" +
                    " 5 = Sort data\n" +
                    " 6 = View By City And State\n" +
                    " 7 = search person By City Or State\n" +
                    " 8 = Read from JSON\n" +
                    " 9 = Write in JSON\n" +
                    " 10 = Write in CSV\n" +
                    " 11 = Read from CSV\n" +
                    " 12 = Quit");
            int choice2 = scanner.nextInt();
            switch (choice2) {
                case 1:
                    addPerson.addPerson();
                    break;
                case 2:
                    addPerson.print();
                    break;
                case 3:
                    addPerson.editPerson();
                    break;
                case 4:
                    addPerson.deletePerson();
                    break;
                case 5:
                    System.out.println("Choose option: \n" +
                            " 1 = Sort by name\n" +
                            " 2 = Sort by Zip\n" +
                            " 3 = Sort by state\n" +
                            " 4 = Sort by city");
                    int choice3 = scanner.nextInt();
                    switch (choice3) {
                        case 1:
                            addPerson.sortByName();
                            break;
                        case 2:
                            addPerson.sortByZip();
                            break;
                        case 3:
                            addPerson.sortByState();
                            break;
                        case 4:
                            addPerson.sortByCity();
                            break;
                    }
                    break;
                case 6:
                    addPerson.viewByCityAndState();
                    break;
                case 7:
                    addPerson.viewByCityOrState();
                    break;
//                case 8:
//                    addPerson.readJsonDataInList();
//                    break;
                case 9:
                    addPerson.writeInJSON();
                    break;
                case 10:
                    addPerson.writeInCSVFile();
                    break;
//                case 11:
//                    addPerson.readFromCSVFile();
//                    break;
                case 12:
                    flag = 2;
                    break;
            }
        }
    }
}

