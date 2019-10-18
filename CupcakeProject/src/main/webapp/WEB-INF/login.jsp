<%-- 
    Document   : login
    Created on : 09-Oct-2019, 12:08:57
    Author     : Gruppe 3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login </title>
    </head>
    <body>
        
        <h1 align="center"> Login page</h1>
        
        <br>
        <p align="center"> Name:      <input type="text" name="name" value="" /></p>
        <p align="center"> Email:     <input type="text" name="email" value="" /></p>
        <p align="center"> Phone:     <input type="text" name="phone" value="" /></p>
        <br>
        
            <form action="FrontController" method="POST">
            <input type="hidden" name="cmd" value="complete" />
            <p align="center"> <input type="submit" value="Finish Login" /></p>
            </form>
                
    </body>
</html>