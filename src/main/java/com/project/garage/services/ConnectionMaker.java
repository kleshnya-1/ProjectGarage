package com.project.garage.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker {
    private static String URL = "jdbc:postgresql://localhost:5432/project_garage_database";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "1";
    private static Connection connection;

    public Connection makeConnection(){


            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return  connection;

    }
}
