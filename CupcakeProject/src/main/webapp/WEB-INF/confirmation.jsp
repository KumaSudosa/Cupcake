<%-- 
    Document   : confirmation
    Created on : 09-Oct-2019, 12:14:35
    Author     : Marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>confirmation</title>
    </head>
    <body>
        
        <h1 align="center">Congratulations with your order. We're looking forward to seeing you in the store! </h1>
    
                <br>
        
                <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="confirmation" />
                <p align="center"> <input type="submit" value="Go back to shop page"/></p>
                </form>
    </body>
</html>