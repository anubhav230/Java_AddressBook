package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.models.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONFileOperation {
    @SuppressWarnings("unchecked")
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
                file.write(personDetails.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFromJSON(List<Person> book, String filePath) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = jsonParser.parse(reader);
            JSONArray addressBook = (JSONArray) obj;
            addressBook.forEach(person -> book.add(parsePersonObject((JSONObject) person)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
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

    public List<Person> readFromSimpleGSON(String filePath) {
        List<Person> PersonList;
        try{
            Person[] personDetails = new Gson().fromJson(new FileReader(filePath), Person[].class);
            PersonList = new ArrayList<>(Arrays.asList(personDetails));
            return PersonList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeGSON(List<Person> list, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        try(FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
