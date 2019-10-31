<%-- 
Document   : login
Created on : 09-Oct-2019, 12:08:57
Author     : Michael
--%>

<%@page import="javax.security.auth.login.LoginException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login </title>
        <link rel="stylesheet" type="text/css" href="css/styleHeader.css">
        <style>
            body {
                background-image: url("decorations/linesBackground.png");
                background-repeat: repeat;
                background-attachment: fixed;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/JSP-Parts/cupcake-Header.jsp"/>

        <div class="circle" style="margin: 0 auto; width: 220px">
            <div class="circleFrame">
                <h1 align="center"> Login page</h1>
            </div>
        </div>

        <br>
        <div style="background-color: #fffef2; width: 225px; margin: 0 auto; border: solid #aaaaa0 2px; ">
            <form action="FrontController" method="POST">
                <p align="center"> 
                    Email
                    <br>
                    <input type="text" name="email" value="" /></p>
                <p align="center"> 
                    Password 
                    <br>
                    <input type="password" name="password" value="" /></p>

                <br>

                <input type="hidden" name="command" value="login" />
                <p align="center"> <input type="submit" value="Login" /></p>

            </form>
        </div>

        <%if (request.getAttribute("LoginError") != null) {
                LoginException loginError = (LoginException) request.getAttribute("LoginError");
                String errorMessage = loginError.getMessage();
        %>
        <br>
        <h2 align="center"><%=errorMessage%></h2>
        <% }%>

    </body>
</html>