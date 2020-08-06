package com.bridgelabz.addressbook.dboperation;

import java.rmi.registry.LocateRegistry;
import java.sql.*;

public class DBOperation {
    String path = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/addressbook";
    String uid = "root";
    String password = "root";
    int result = 0;
    ResultSet resultSet = null;
    public void insertData(String firstName, String lastName, long mobileNumber, String state, String city, int zip) {
        try {
            Class.forName(path);
            Connection con = DriverManager.getConnection(url, uid, password);
            Statement statement = con.createStatement();
            String query =  "insert into person(firstName,lastName,MobileNumber,city,state,zip)" +
                    " values('"+firstName+"','"+lastName+"',"+mobileNumber+",'"+state+"','"+city+"',"+zip+")";
            statement.executeUpdate(query);
            if (result == 0)
                System.out.println("data not inserted");
            else {
                System.out.println("data inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void select(){
        try {
            Class.forName(path);
            Connection con = DriverManager.getConnection(url, uid, password);
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select * from person");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)+
                        " "+resultSet.getString(2)+
                        " "+resultSet.getString(3)+
                        " "+resultSet.getLong(4)+
                        " "+resultSet.getString(5)+
                        " "+resultSet.getString(6)+
                        " "+resultSet.getInt(7));
            }
            if (resultSet == null)
                System.out.println("record not found");
            else
                System.out.println("Record Displayed");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void editData(String name , long mobileNumber) {
        try {
            Class.forName(path);
            Connection con = DriverManager.getConnection(url, uid, password);
            Statement statement = con.createStatement();
            String Query = "update person set MobileNumber= "+mobileNumber+" where FirstName='"+name+"'";
            statement.executeUpdate(Query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void editData(String name , int zip) {
        try {
            Class.forName(path);
            Connection con = DriverManager.getConnection(url, uid, password);
            Statement statement = con.createStatement();
            String Query = "update person set Zip= "+zip+" where FirstName='"+name+"'";
            statement.executeUpdate(Query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void editData(String name , int field, String value) {
        try {
            Class.forName(path);
            Connection con = DriverManager.getConnection(url, uid, password);
            Statement statement = con.createStatement();
            switch (field) {
                case 2:
                    String Query = "update person set State= '"+value+"' where FirstName='"+name+"'";
                    statement.executeUpdate(Query);
                    break;
                case 3:
                    String Query2 = "update person set City= '"+value+"' where FirstName='"+name+"'";
                    statement.executeUpdate(Query2);
                    break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(String name) {
        try {
            Class.forName(path);
            Connection con = DriverManager.getConnection(url, uid, password);
            Statement statement = con.createStatement();
            String Query = "delete from person where FirstName='"+name+"'";
            statement.executeUpdate(Query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}