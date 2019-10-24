<%-- 
    Document   : invoice
    Created on : 24-10-2019, 12:13:46
    Author     : Michael N. Korsgaard
--%>

<%@page import="logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoices</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            String username = user.getUsername();
        %>
        
        <h1>Hello <%=username%></h1>
        
        
    </body>
</html>
