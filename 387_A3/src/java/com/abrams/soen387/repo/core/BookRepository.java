/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.core;

import com.abrams.soen387.repo.db.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class BookRepository implements IBookRepository {
   private static BookRepository instance;
   private BookRepository() {};
   
   public static synchronized IBookRepository getInstance(){
       if(instance == null){
           instance = new BookRepository(); 
       }
       return instance;
   }
   
   @Override
   public List<Book> listAllBooks(Connection conn, Session session) throws SQLException{
      if(!session.isUserLoggedIn()){return null;}
      List<Book> bookList = new ArrayList<>();
      
      ResultSet rs = BookGateway.listAllBooks(conn);
      while(rs.next()){
          Book nextBook = new Book();
          nextBook.setBookID(rs.getInt("BookID"));
          nextBook.setTitle(rs.getString("Title"));
          nextBook.setAuthorFirst(rs.getString("AuthorFirst"));
          nextBook.setAuthorLast(rs.getString("AuthorLast"));
          nextBook.setBookDescription(rs.getString("BookDescription"));
          nextBook.setIsbn(rs.getString("ISBN"));
          nextBook.setPubAddress(rs.getString("PubAddress"));
          nextBook.setPublisher(rs.getString("Publisher"));
          bookList.add(nextBook);
      }
      return bookList;
      
   }

    @Override
    public Book getBookByID(int id, Connection conn, Session session) throws SQLException, RepositoryException {
        if(!session.isUserLoggedIn()){return null;}
        
        //check if book exists
        boolean isBook = false;
        ResultSet rsTest = BookGateway.getBookIDs(conn);
        while(rsTest.next()){
            if(rsTest.getInt("BookID") == id){
                isBook = true;
            }
        }
        if(!isBook) {throw new RepositoryException("This book does not exist");}
       
        ResultSet rs = BookGateway.getBookByID(conn, id);
        Book book = new Book();
        while(rs.next()){
            book.setBookID(rs.getInt("BookID"));
            book.setTitle(rs.getString("Title"));
            book.setAuthorFirst(rs.getString("AuthorFirst"));
            book.setAuthorLast(rs.getString("AuthorLast"));
            book.setBookDescription(rs.getString("BookDescription"));
            book.setIsbn(rs.getString("ISBN"));
            book.setPubAddress(rs.getString("PubAddress"));
            book.setPublisher(rs.getString("Publisher"));
        }
        return book;
    }

    @Override
    public Book getBook(String ISBN, Connection conn, Session session) throws SQLException, RepositoryException{
        if(!session.isUserLoggedIn()){return null;}
        boolean isBook = false;
        
        //check if ISBN exists
        ResultSet rsTest = BookGateway.getBookISBNs(conn);
        while(rsTest.next()){
            if(rsTest.getString("ISBN").equals(ISBN)){
                isBook = true;
            }
        }
        if(!isBook) {throw new RepositoryException("This book does not exist");}
        
        Book book = new Book();
        ResultSet rs = BookGateway.getBookByISBN(conn, ISBN);        
        while(rs.next()){
            book.setBookID(rs.getInt("BookID"));
            book.setTitle(rs.getString("Title"));
            book.setAuthorFirst(rs.getString("AuthorFirst"));
            book.setAuthorLast(rs.getString("AuthorLast"));
            book.setBookDescription(rs.getString("BookDescription"));
            book.setIsbn(rs.getString("ISBN"));
            book.setPubAddress(rs.getString("PubAddress"));
            book.setPublisher(rs.getString("Publisher"));
        }    
        return book;
    }
    

    @Override
    public int addBook(String title, String desc, String ISBN, String authorFirst, String authorLast, String publisher, String pubAddress, Connection conn, Session session) throws SQLException, RepositoryException {
        if(!session.isUserLoggedIn()){return 0;}
        
        //gets last ID and adds 1 to create new ID
        ResultSet rs = BookGateway.getBookIDs(conn);
        int id = 1;
        while(rs.next()){
            rs.last();
            int lastID = rs.getInt("BookID");      
            id = lastID + 1;
        }
        
        //checks if Book already exists
        ResultSet rs1 = BookGateway.getBookISBNs(conn);
        while(rs1.next()){
            if(rs1.getString("ISBN").equals(ISBN)) {
                throw new RepositoryException("This book already exists");
            }
        }

        BookGateway.addBook(id, title, desc, ISBN, authorFirst, authorLast, publisher, pubAddress, conn);
        return id;
    }

    @Override
    public void updateBook(int id, String title, String desc, String ISBN, String authorFirst, String authorLast, String publisher, String pubAddress, Connection conn, Session session) throws SQLException {
        if(!session.isUserLoggedIn()){return;}
        BookGateway.updateBook(id, title, desc, ISBN, authorFirst, authorLast, publisher, pubAddress, conn);
    }

    @Override
    public void setCover() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBook(int id, Connection conn, Session session) throws SQLException, RepositoryException {
        if(!session.isUserLoggedIn()){return;}
       
        //Checks if book exists
        boolean isBook = false;
        ResultSet rs = BookGateway.getBookIDs(conn);
        while(rs.next()){
            if(rs.getInt("BookID") == id){
                isBook = true;
            }
        }
        if(!isBook) {throw new RepositoryException("This book does not exist");}
        
        BookGateway.deleteBook(id, conn);
    }
    
    @Override
    public void deleteAllBooks(Connection conn, Session session) throws SQLException{
        if(!session.isUserLoggedIn()){return;}
        BookGateway.deleteAll(conn);
    }
}
