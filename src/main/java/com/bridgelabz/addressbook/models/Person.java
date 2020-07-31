package com.bridgelabz.addressbook.models;

public class Person {
    private String firstName;
    private String lastName;
    private String state;
    private String city;
    private int zip;
    private long mobileNumber;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setCity(String city) {
        this.city=city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state=state;
    }

    public String getState() {
        return state;
    }

    public void setZip(int zip) {
        this.zip=zip;
    }

    public int getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" +
                lastName + ", mobileNumber=" + mobileNumber+ ", city=" +
                city + ", state=" + state + ", zip=" + zip + "]";
    }
}
