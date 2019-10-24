<%-- 
    Document   : registration
    Created on : 09-Oct-2019, 12:08:44
    Author     : Marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <h1 align="center">Registration page</h1>
        
        <br>
        <form action="FrontController" method="POST">
            <h3 align="center"> username:</h3>
                <p align="center">
                <input type="text" name="username" value="" placeholder="choose username"
                /></p>
            
            <h3 align="center"> password:</h3>
                <p align="center">
                <input type="password" name="password" value="" placeholder="choose password"
                /></p>
                
                <p align="center">
                <input type="password" name="passwordRepeat" value="" placeholder="write password again"
                /></p>
                
            <h3 align="center"> email:</h3>
                <p align="center">
                <input type="text" name="email" value="" placeholder="choose email"
                /></p>
                
            <input type="hidden" name="command" value="registration" />
            <p align="center"> <input type="submit" value="Register" /></p>
        </form>
        <%if (request.getAttribute("RegistrationError") != null) {
        IllegalArgumentException registrationError = (IllegalArgumentException) request.getAttribute("RegistrationError");
        String errorMessage = registrationError.getMessage();
        %>
        <h2 align="center"><%=errorMessage%> </h2>
        <%}%>
    </body>
</html>