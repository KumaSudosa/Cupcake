<%-- 
    Document   : shoppage
    Created on : 18-10-2019, 08:27:08
    Author     : Marcus & Andreas
--%>

<%@page import="logic.CupcakeTopping"%>
<%@page import="java.util.HashMap"%>
<%@page import="persistence.mappers.CupcakeMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.CupcakeBottom"%>
<%@page import="logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop page</title>
    </head>
    <body>
        
        <%
            User user = (User) session.getAttribute("user");
        %>
        <h5 align="right">
            You are logged in as:
            <br>
            <%=user.getUsername()%>
        </h5>
        <h5 align="right">
            Your balance is:
            <%=user.getBalance()%> DKK
        </h5>
        <h5 align="right">
            <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="frontpage" />
            
            <button onclick="myFunction()">Logout</button>
            <script>
            function myFunction() {
                alert("Logging you out now");
            }
            </script>

            </form>
        </h5>
        
        <br>
        <br>
        
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="shoppage" />
            <p align="center"><input type="submit" value="Go to products page" style="height:50px; width:150px" /></p>
        </form>
        
        <br>
        <br>
        
        <!-- Ved godt den ikke virker korrekt! -->
        <form action="FrontController" method="POST">
        <input type="hidden" name="command" value="invoice" />
        <p align="center"><input type="submit" value="Go to purchase history" style="height:50px; width:150px" /></p>
        </form>
    </body>
</html>