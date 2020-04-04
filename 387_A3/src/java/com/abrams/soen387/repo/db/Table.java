/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.db;

import java.sql.*;

/**
 *
 * @author Alex
 */
public class Table {
    private static Connection conn;
    
    public Table(){}
    
    public static synchronized Connection getConn(){
       if(conn == null){
            try {
                String dbURL = "jdbc:mysql://localhost:3306/bookrepository";
                String username = "root";
                String password = "sesame";
                conn = DriverManager.getConnection(dbURL, username, password);
            }
            catch(SQLException e) {
                for (Throwable t : e)
                    t.printStackTrace();
            }
       }
       return conn;
    }
}
