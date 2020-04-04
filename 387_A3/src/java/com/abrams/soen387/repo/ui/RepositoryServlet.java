/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.ui;

import com.abrams.soen387.repo.db.Book;
import com.abrams.soen387.repo.core.IBookRepository;
import com.abrams.soen387.repo.core.BookRepository;
import com.abrams.soen387.repo.core.Session;
import com.abrams.soen387.repo.db.Table;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
public class RepositoryServlet extends HttpServlet {
    private Connection conn;
    private static final IBookRepository repo = BookRepository.getInstance();
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        Session sesh = new Session();
        sesh.login("user", "test");      
        conn = Table.getConn();
        
        List<Book> currentRepo = repo.listAllBooks(conn, sesh);
        request.setAttribute("books", currentRepo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
        dispatcher.forward(request, response);
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
        catch(SQLException e){
            Logger.getLogger(RepositoryServlet.class.getName()).log(Level.SEVERE, null, e);
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
        catch(SQLException e){
            Logger.getLogger(RepositoryServlet.class.getName()).log(Level.SEVERE, null, e);
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

}
