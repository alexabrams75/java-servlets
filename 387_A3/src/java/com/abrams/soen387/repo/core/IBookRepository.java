/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.core;

import com.abrams.soen387.repo.db.Book;
import java.sql.*;
import java.util.List;
/**
 *
 * @author Alex
 */
public interface IBookRepository {
    List<Book> listAllBooks(Connection conn, Session session) throws SQLException;
    Book getBookByID(int id, Connection conn, Session session) throws SQLException, RepositoryException;
    Book getBook(String ISBN, Connection conn, Session session) throws SQLException, RepositoryException;
    int addBook(String title, String desc, String ISBN, String authorFirst, String authorLast, String publisher, String pubAddress, Connection conn, Session session) 
            throws SQLException, RepositoryException;  
    void updateBook(int id, String title, String desc, String ISBN, String authorFirst, String authorLast, String publisher, String pubAddress, Connection conn, Session session) 
            throws SQLException;
    void setCover();
    void deleteBook(int id, Connection conn, Session session) throws SQLException, RepositoryException;
    void deleteAllBooks(Connection conn, Session session) throws SQLException;
}



