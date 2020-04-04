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
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    private Connection conn;
    private static final IBookRepository repo = BookRepository.getInstance();
    //private final Table db = new Table();
    private final Session sesh = new Session("user", "test");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException, RepositoryException {
        response.setContentType("text/html;charset=UTF-8");
        
        Map<String, String[]> p = request.getParameterMap(); //parameters
        if("0".equals(p.get("func")[0])){
            addBook(request, response, p.get("title")[0], p.get("desc")[0], p.get("isbn")[0], p.get("first")[0], p.get("last")[0], p.get("pub")[0], p.get("pubAdd")[0]);
        }
        if("1".equals(p.get("func")[0])){
            updateBook(request, response, parseInt(p.get("id")[0]), p.get("title")[0], p.get("desc")[0], p.get("isbn")[0], p.get("first")[0], p.get("last")[0], p.get("pub")[0], p.get("pubAdd")[0]);
        }
        if("2".equals(p.get("func")[0])){
            viewBook(request, response, String.valueOf(p.get("isbn")[0]), parseInt(p.get("update")[0]));
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
        } catch (SQLException | RepositoryException ex) {
            Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            response.sendRedirect(request.getContextPath() + "/Book.jsp");

        } catch (SQLException | RepositoryException ex) {
            Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void addBook(HttpServletRequest req, HttpServletResponse resp, String title, String desc, String ISBN, String authorFirst, String authorLast, String publisher, String pubAddress) throws SQLException, ServletException, IOException{
        conn = Table.getConn();
        try{
            repo.addBook(title, desc, ISBN, authorFirst, authorLast, publisher, pubAddress, conn, sesh);
        } catch(RepositoryException | SQLException e){
            Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        List<Book> currentRepo = repo.listAllBooks(conn, sesh);
        req.setAttribute("books", currentRepo);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Home.jsp");
        dispatcher.forward(req, resp);
    }
    
    public void updateBook(HttpServletRequest req, HttpServletResponse resp, int id, String title, String desc, String ISBN, String authorFirst, String authorLast, String publisher, String pubAddress) throws SQLException, ServletException, IOException{
        conn = Table.getConn();
        repo.updateBook(id, title, desc, ISBN, authorFirst, authorLast, publisher, pubAddress, conn, sesh);
        
        List<Book> currentRepo = repo.listAllBooks(conn, sesh);
        req.setAttribute("books", currentRepo);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Home.jsp");
        dispatcher.forward(req, resp);
    }
    
    public void viewBook(HttpServletRequest req, HttpServletResponse resp, String isbn, int update) throws SQLException, RepositoryException, ServletException, IOException{
        conn = Table.getConn();
        Book book = repo.getBook(isbn, conn, sesh);
      
        RequestDispatcher dispatcher;
        req.setAttribute("singleBook", book);
        if(update == 1){ 
            dispatcher = req.getRequestDispatcher("/BookUpdateTemplate.jsp");
        }
        else{
            dispatcher = req.getRequestDispatcher("/BookTemplate.jsp");
        }
        dispatcher.forward(req, resp);
    } 
}
 