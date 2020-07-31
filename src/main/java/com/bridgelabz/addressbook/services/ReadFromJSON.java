package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReadFromJSON {
    public void writeJson(List<Person> book, String filePath) {
        JSONArray personDetails = new JSONArray();
        for (Person person : book) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("FirstName", person.getFirstName());
            jsonObject.put("LastName", person.getLastName());
            jsonObject.put("MobileNumber", person.getMobileNumber());
            jsonObject.put("State", person.getState());
            jsonObject.put("City", person.getCity());
            jsonObject.put("Zip", person.getZip());

            JSONObject addressBookObject = new JSONObject();
            addressBookObject.put("addressBook", jsonObject);
            personDetails.add(addressBookObject);

            try (FileWriter file = new FileWriter(filePath)) {
                file.write(personDetails.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public List<Person> readFromJSON(List<Person> book, String filePath) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = jsonParser.parse(reader);
            JSONArray addressBook = (JSONArray) obj;
            addressBook.forEach( person -> book.add(parsePersonObject((JSONObject) person )));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return book;
    }

    private Person parsePersonObject(JSONObject personJson) {
        Person person = new Person();
        JSONObject personObj = (JSONObject) personJson.get("addressBook");
        String firstName = (String) personObj.get("FirstName");
        person.setFirstName(firstName);
        String lastName = (String) personObj.get("LastName");
        person.setLastName(lastName);
        Long mobileNumber = (Long) personObj.get("MobileNumber");
        person.setMobileNumber(mobileNumber);
        String state = (String) personObj.get("State");
        person.setState(state);
        String city = (String) personObj.get("City");
        person.setCity(city);
        Long zip = (Long) personObj.get("Zip");
        person.setZip(zip.intValue());
        return person;

    }



}
