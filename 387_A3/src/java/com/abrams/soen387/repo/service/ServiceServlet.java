/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.service;

import com.abrams.soen387.repo.core.*;
import com.abrams.soen387.repo.db.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
@WebServlet(name = "ServiceServlet", urlPatterns = {"/ServiceServlet"})
public class ServiceServlet extends HttpServlet {
    private static Connection conn;
    private static final IBookRepository repo = BookRepository.getInstance();
    private final Session sesh = new Session("user", "test");
    private final Gson gson = new Gson();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, RepositoryException {
        response.setContentType("text/html;charset=UTF-8");
        
        Map<String, String[]> p = request.getParameterMap(); //parameters
        int id = parseInt(p.get("id")[0]);
        if(id == 0){
            getBooks(request, response, conn);
        }
        if(id > 0){
            getABook(request, response, conn, id);
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
        } catch (SQLException ex) {
            Logger.getLogger(ServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RepositoryException ex) {
            Logger.getLogger(ServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RepositoryException ex) {
            Logger.getLogger(ServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    protected void getABook(HttpServletRequest req, HttpServletResponse resp, Connection conn, int id) throws SQLException, RepositoryException, IOException{
        conn = Table.getConn();
        Book book = repo.getBookByID(id, conn, sesh);
        String bookJson = this.gson.toJson(book);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(bookJson);
        out.flush();
    }
    
    protected void getBooks(HttpServletRequest req, HttpServletResponse resp, Connection conn) throws SQLException, IOException {
        conn = Table.getConn();
        List<Book> bookList = repo.listAllBooks(conn, sesh);
        String json = this.gson.toJson(bookList);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
