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
public class BookGateway {
    public BookGateway(){};
    
    public static ResultSet listAllBooks(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM book";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
    public static ResultSet getBookIDs(Connection conn) throws SQLException {
        String idCheck = "SELECT BookID FROM book";
        Statement stmtTest = conn.createStatement();
        ResultSet rsTest = stmtTest.executeQuery(idCheck);
        return rsTest;
    }
    
    public static ResultSet getBookISBNs(Connection conn) throws SQLException {
        String idCheck = "SELECT ISBN FROM book";
        Statement stmtTest = conn.createStatement();
        ResultSet rsTest = stmtTest.executeQuery(idCheck);
        return rsTest;
    }
    
    public static ResultSet getBookByID(Connection conn, int id) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM book WHERE BookID=" + id;
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public static ResultSet getBookByISBN(Connection conn, String isbn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM book WHERE ISBN=" + isbn;
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
    public static void addBook(int id, String title, String desc, String ISBN, String authorFirst, String authorLast, String publisher, String pubAddress, Connection conn) throws SQLException{
        String sql = "INSERT INTO book (BookID, Title, BookDescription, ISBN, AuthorFirst, AuthorLast, Publisher, PubAddress) VALUES (?,?,?,?,?,?,?,?)";    
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, title);
        pstmt.setString(3, desc);
        pstmt.setString(4, ISBN);
        pstmt.setString(5, authorFirst);
        pstmt.setString(6, authorLast);
        pstmt.setString(7, publisher);
        pstmt.setString(8, pubAddress);
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    public static void updateBook(int id, String title, String desc, String ISBN, String authorFirst, String authorLast, String publisher, String pubAddress, Connection conn) throws SQLException{
        String sql = "UPDATE book SET Title=?, BookDescription=?, ISBN=?, AuthorFirst=?, AuthorLast=?, Publisher=?, PubAddress=? WHERE BookID = ?";    
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, title);
        pstmt.setString(2, desc);
        pstmt.setString(3, ISBN);
        pstmt.setString(4, authorFirst);
        pstmt.setString(5, authorLast);
        pstmt.setString(6, publisher);
        pstmt.setString(7, pubAddress);
        pstmt.setInt(8, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    public static void deleteBook(int id, Connection conn) throws SQLException{
       String sql = "DELETE FROM book WHERE BookID=?";
       PreparedStatement pstmt = conn.prepareStatement(sql);
       pstmt.setInt(1, id);
       pstmt.executeUpdate();
       pstmt.close();
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
       String sql = "DELETE FROM book";
       PreparedStatement pstmt = conn.prepareStatement(sql);
       pstmt.executeUpdate();
       pstmt.close();
    }
}
