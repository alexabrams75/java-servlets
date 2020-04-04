/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abrams.soen387.repo.core;

import java.sql.*;

/**
 *
 * @author Alex
 */
public class Connector {
    
    public static void main(String[] args) throws SQLException, RepositoryException {
        Connection conn = null;
        try{
            String dbURL = "jdbc:mysql://localhost:3306/bookrepository";
            String username = "root";
            String password = "sesame";
            
            conn = DriverManager.getConnection(dbURL, username, password);
            System.out.println("success");
        }
        catch(SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        Session test = new Session();
        test.login("user", "test");
        
        IBookRepository repo = BookRepository.getInstance();
        
        repo.addBook("Lord of the Rings", "A long journey for the ring", "12345", "JR", "Tolkien", "Old Tyme Publishers", "888 Road Lane", conn, test);
        repo.addBook("Harry Potter and the Philosophers Stone", "A boy discovers he is a wizard", "56789", "JK", "Rowling", "Penguin Publishers", "123 Main Street", conn, test);
        //repo.getBook("99999", conn, test);
        //repo.deleteBook(2200, conn, test);
        //System.out.println(repo.addBook("LOTR777", "Journey for the ring", "99999", "JR", "Tolkien", "Old Tyme Publishers", "888 Road Lane", conn, test));
        //repo.getBookByID(1, conn, test);
                
       //System.out.println("Book id:"+id);
    }
    
}
