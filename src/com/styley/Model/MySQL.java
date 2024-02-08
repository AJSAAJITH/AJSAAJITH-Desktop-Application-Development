package com.styley.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {

    private static Connection connection;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "A/L2001s";
    private static final String DATABASE = "styley"; //styley
    
    private static Statement Connection()throws Exception{
    
       if(connection == null){
           Class.forName("com.mysql.cj.jdbc.Driver");
           connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DATABASE, USERNAME, PASSWORD);
       }  
       return connection.createStatement();
    }

    public static void iud(String Query) {

        try {
            Connection().executeUpdate(Query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet search(String Query) throws Exception{

        return Connection().executeQuery(Query);
    }
}
