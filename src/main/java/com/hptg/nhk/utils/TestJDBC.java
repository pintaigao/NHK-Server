package com.hptg.nhk.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://nhk-db.cfkajnkfgjxp.us-east-1.rds.amazonaws.com";
        String user = "hptg1994";
        String pass = "Hptg19940215";
        Connection myConn;
        try {
            System.out.println("Connecting to database:" + jdbcUrl);
            myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
