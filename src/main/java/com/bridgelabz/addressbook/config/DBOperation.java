package com.bridgelabz.addressbook.config;

import java.sql.*;
public class DBOperation {
    int result = 0;
    ResultSet resultSet = null;
    java.sql.Connection connection = null;
    Statement statement = null;
    public void insertData(String firstName, String lastName, long mobileNumber,
                           String state, String city, int zip) {
        try {
            connection = Connections.getConnection();
            statement = connection.createStatement();
            String query =  "insert into person(firstName,lastName,MobileNumber,city,state,zip)" +
                    " values('"+firstName+"','"+lastName+"',"+mobileNumber+",'"+state+"','"+city+"',"+zip+")";
            result = statement.executeUpdate(query);
            if (result == 0)
                System.out.println("data not inserted");
            else {
                System.out.println("data inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void select(){
        try {
            connection = Connections.getConnection();
            statement = connection.createStatement();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void editData(String name , long mobileNumber) {
        try {
            connection = Connections.getConnection();
            statement = connection.createStatement();
            String Query = "update person set MobileNumber= "+mobileNumber+" where FirstName='"+name+"'";
            statement.executeUpdate(Query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void editData(String name , int zip) {
        try {
            connection = Connections.getConnection();
            statement = connection.createStatement();
            String Query = "update person set Zip= "+zip+" where FirstName='"+name+"'";
            statement.executeUpdate(Query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void editData(String name , int field, String value) {
        try {
            connection = Connections.getConnection();
            statement = connection.createStatement();
            switch (field) {
                case 2:
                    String query = "update person set State= '"+value+"' where FirstName='"+name+"'";
                    statement.executeUpdate(query);
                    break;
                case 3:
                    String query2 = "update person set City= '"+value+"' where FirstName='"+name+"'";
                    statement.executeUpdate(query2);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePerson(String name) {
        try {
            connection = Connections.getConnection();
            statement = connection.createStatement();
            String query = "delete from person where FirstName='"+name+"'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}