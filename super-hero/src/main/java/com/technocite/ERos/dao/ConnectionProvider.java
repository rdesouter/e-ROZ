package com.technocite.ERos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private final static String URL =  "jdbc:postgresql://localhost:5432/e-ROZ";
    private final static String USER =  "postgres";
    private final static String PASSWORD =  "admin";
    private static Connection connection;

    public static Connection provide() throws SQLException{
        if(connection==null){
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connection established");
        }
        return connection;
    }
}
