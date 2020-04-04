/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.ui;

import com.abrams.soen387.repo.db.Book;
import com.abrams.soen387.repo.core.*;
import com.abrams.soen387.repo.db.Table;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class DeleteServlet extends HttpServlet {

    private static Connection conn;
    private static final IBookRepository repo = BookRepository.getInstance();
    private final Session sesh = new Session("user", "test");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, RepositoryException {
        response.setContentType("text/html;charset=UTF-8");
           
        Map<String, String[]> params = request.getParameterMap();
        String parameter;
        System.out.print(params);
        if( "1".equals(params.get("deleteBy")[0])){
            parameter = params.get("bookID")[0];
            int bookID = Integer.parseInt(parameter);
            deleteBook(request, response, bookID);
        }
        if("0".equals(params.get("deleteBy")[0])){
            deleteAllBooks(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
           processRequest(request, response);
        }
        catch(SQLException | RepositoryException e){
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
           processRequest(request, response);
        }
        catch(SQLException | RepositoryException e){
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void deleteBook(HttpServletRequest req, HttpServletResponse resp, int id) throws SQLException, RepositoryException, ServletException, IOException {
        conn = Table.getConn();
        try {
            repo.deleteBook(id, conn, sesh);
        } catch (RepositoryException e){
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        List<Book> currentRepo = repo.listAllBooks(conn, sesh);
        req.setAttribute("books", currentRepo);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Home.jsp");
        dispatcher.forward(req, resp);
    }
    
    public void deleteAllBooks(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        conn = Table.getConn();
        repo.deleteAllBooks(conn, sesh);
        
        List<Book> currentRepo = repo.listAllBooks(conn, sesh);
        req.setAttribute("books", currentRepo);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Home.jsp");
        dispatcher.forward(req, resp);  
    }
}
