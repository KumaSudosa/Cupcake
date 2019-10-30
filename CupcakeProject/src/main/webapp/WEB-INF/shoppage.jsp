<%-- 
    Document   : shoppage
    Created on : 18-10-2019, 08:27:08
    Author     : Marcus & Andreas
--%>

<%@page import="logic.Customer"%>
<%@page import="logic.ShoppingCart"%>
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
        <style>
            body {
                background-image: url("decorations/linesBackground.png");
                background-repeat: repeat;
                background-attachment: fixed;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="css/styleHeader.css">
    </head>
    <body>
        <jsp:include page="/JSP-Parts/cupcake-Header.jsp"/>
        <%
            User user = (User) session.getAttribute("user");
            boolean userLoggedIn = true;
            boolean userIsCustomer = false;
            boolean userIsAdmin = false;
            Customer customer = null;
            ShoppingCart cart = null;
            if (user == null) {
                userLoggedIn = false;
            } else if (User.isUserCustomer(user)) {
                userIsCustomer = true;
                customer = (Customer) user;
                cart = customer.getShoppingCart();
            } else if (User.isUserAdmin(user)) {
                userIsAdmin = true;
            }

        %>
        <% if (userLoggedIn) {%>

        <br><br><br>
        <div style="float: left; height: 30%; width:20%; margin-left: 25%; margin-right: 5%">

            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="invoice" />
                <p align="center"><input type="submit" value="Go to purchase history" style="height:50px; width:150px" /></p>
            </form>
        </div>
        <div style="text-align: center; float: left; height: 30%; width:25%; margin-left: 0%; margin-right: 25%; background-color: #fffef2; border-radius: 50%">
            <div style="border: solid #aaaaa0 2px; border-radius: 50%;">
                <br>
                <b style="font-size: 24px; font-family: Lucida Bright">Username</b>
                <p style="font-size: 18px"><%=customer.getUsername()%></p>
                <br><br>
                <b style="font-size: 24px; font-family: Lucida Bright">Email</b>
                <p style="font-size: 18px"><%=customer.getEmail()%></p>
                <br><br>
                <b style="font-size: 24px; font-family: Lucida Bright">Balance</b>
                <p style="font-size: 18px"><%=customer.getBalance()%>,-</p> <br>
            </div>
        </div>
        <%}%>
    </body>
</html>