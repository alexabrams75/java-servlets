<%-- 
    Document   : BookUpdateTemplate
    Created on : 1-Dec-2019, 12:15:18 PM
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
        <title>View Book</title>
    </head>
    <body class="ui container">
        <h1>Selected Book</h1>
        <table class="ui celled striped table">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>ISBN</th>
                <th>Author</th>
                <th>Publisher</th>
                <th>Publisher Address</th>
            </tr>
            <c:set value = "${singleBook}" var="bookBean" />
            <tr>
                <td>${bookBean.getBookID()}</td>
                <td>${bookBean.getTitle()}</td>
                <td>${bookBean.getBookDescription()}</td>
                <td>${bookBean.getIsbn()}</td>
                <td>${bookBean.getAuthorFirst()}&nbsp;${bookBean.getAuthorLast()}</td>
                <td>${bookBean.getPublisher()}</td>
                <td>${bookBean.getPubAddress()}</td>
            </tr>
        <h2><a id="update" href="#update" onclick='updateBook()'>Update a book</a></h2>
        </table>
    </body>
</html>
