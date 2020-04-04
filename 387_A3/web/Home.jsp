<%-- 
    Document   : Home.jsp
    Created on : 29-Nov-2019, 1:35:52 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.abrams.soen387.repo.core.*"%>
<%@page import="com.abrams.soen387.repo.ui.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">
        <script type="text/javascript" src="js/functions.js"></script>
        <title>Repository Home page</title>
    </head>
    <body class="ui container">
        <h1>Current Library:</h1>
        <table class="ui celled striped table">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>ISBN</th>
                <th>Description</th>
                <th>Author</th>
            </tr>
            
            <c:forEach items = "${books}" var="bookBean">
                <tr>
                    <td>${bookBean.getBookID()}</td>
                    <td>${bookBean.getTitle()}</td>
                    <TD>${bookBean.getIsbn()}</td>
                    <td>${bookBean.getBookDescription()}</td>
                    <td>${bookBean.getAuthorFirst()}&nbsp;${bookBean.getAuthorLast()}</td>
                </tr>
            </c:forEach>
        </table>
        <h2><a id="view" href="#view" onclick='viewBook()'>View book details</a></h2>
        <h2><a id="add" href="#add" onclick='addBook()'>Add a new book</a></h2>
        <h2><a id="update" href="#update" onclick='updateBook()'>Update a book</a></h2>
        <h2><a id="delete" href="#delete" onclick='deleteBook()'>Delete a book</a></h2>
        <h2><a id="deleteAll" href="#deleteAll" onclick='deleteAll()'>Delete all books</a></h2>
        <h2><a id="getBook" href="#getBook" onclick='getBook()'>Get a Book JSON</a></h2>
        <h2><a id="getAll" href="#getAll" onclick='getAllBooks()'>Get a all books as JSON</a></h2>
    </body>
</html>
