package com.bridgelabz.addressbook;

public class Person {
    private String firstName;
    private String lastName;
    private String state;
    private String city;
    private int zip;
    private long mobileNumber;

    public void setFristName(String firstName) {
        this.firstName = firstName;
    }//setFristName()
    public String getFristName() {
        return firstName;

    }//getFristNamev()

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }//setLastName()

    public String getLastName() {
        return lastName;
    }//getLastName()

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;

    }//setMobileNumber()

    public long getMobileNumber() {
        return mobileNumber;
    }//getMobileNumber()

    public void setCity(String city) {
        this.city=city;

    }//setCity()

    public String getCity() {
        return city;
    }//getCity()

    public void setState(String state) {
        this.state=state;
    }//setState()

    public String getState() {
        return state;
    }//getState()

    public void setZip(int zip) {
        this.zip=zip;
    }//setZip()

    public int getZip() {
        return zip;
    }//getZip()

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber+
                ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
    }

}
