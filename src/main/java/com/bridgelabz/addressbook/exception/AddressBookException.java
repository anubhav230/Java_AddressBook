package com.bridgelabz.addressbook.exception;

public class AddressBookException extends Exception {
public enum ExceptionType {
    FILE_PROBLEM,

}
    ExceptionType type;
    public AddressBookException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

}
