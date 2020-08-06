package com.bridgelabz.addressbook.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306/addressbook?autoReconnect=true&useSSL=false";
    private final static String uid = "root";
    private final static String password = "root";
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, uid, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
