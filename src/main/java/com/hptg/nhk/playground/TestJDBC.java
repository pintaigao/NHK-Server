/*
package com.hptg.nhk.playground;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/NHK_News?useSSL=false";
        String user = "postgres";
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
*/
