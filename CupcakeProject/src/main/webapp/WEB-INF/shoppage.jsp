<%-- 
    Document   : shoppage
    Created on : 18-10-2019, 08:27:08
    Author     : Michael N. Korsgaard
--%>

<%@page import="logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">Welcome</h1>
        <br>
        <%
            User user = (User) session.getAttribute("user");
        %>

        <h2 align="center">
            You are logged in as:
            <br>
            <%=user.getUsername()%>
        </h2>

    </body>
</html>
