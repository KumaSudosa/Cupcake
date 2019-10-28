<%-- 
    Document   : index
    Created on : 24-10-2019, 09:10:00
    Author     : andre
--%>

<%@page import="presentation.FrontController"%>
<%@page import="logic.CupcakeBottom"%>
<%@page import="logic.CupcakeTopping"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
    FrontController.setup();
    %>
    <head>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine">

   <style>
      body {
        background-image: url("decorations/linesBackground.png");
        background-repeat: repeat;
        background-attachment: fixed;
      }
      h1 {
        font-family: 'Tangerine', serif;
        text-shadow: 4px 10px 4px #808080;
        font-size: 100px;
      }
    </style>
    
        <title>Cupcake</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
    <body bgcolor="#fffef2">
        <h1 align="center"><font face="Tangerine" color="#00000">Cupcake shop</font></h1>
    <div align="center">
        <div>
                <br>
        <div>
        <div style="float: top; margin-bottom: -350px">
        <p><img src="decorations\\sifbaslfdbfd.png" alt="Bookstore Icon Stolen From The Internet" width="250" height="281"></p>
        </div>
            
        <div style="float:left; width: 20%; padding-bottom: 20px; padding-left: 50px">
        <table border = "1" bgcolor = "#fffef2">
            <thead>
                <tr>
                    <td bgcolor="ffb6c1" style="width: 15%;font-size: 24px" align="center">Bottoms</td>
                    <td bgcolor="ffb6c1" style="width: 5%;font-size: 24px" align="center">Price</td>
                </tr>
            </thead>
            <tbody>
                <%
                ArrayList<CupcakeBottom> cupcakeBottomList = (ArrayList<CupcakeBottom>) CupcakeBottom.getCupcakeBottomsList();
                for (CupcakeBottom cupcakeBottom : cupcakeBottomList) {
                String description = cupcakeBottom.getCupcakeBottomDescription();
                double price = cupcakeBottom.getPriceBottom();
                %>        
                    <tr>
                        <td align="center" style="font-size: 18px"> <%=description%> </td>
                        <td align="center" style="font-size: 18px"> <%=price+",-"%> </td>
                    </tr>
                </tbody>
                <% } %>
        </table>
        </div>
        
        <div style="float:right; width: 20%; padding-bottom: 20px; padding-right: 50px">
        <table border = "1" bgcolor = "#fffef2">
            <thead>
                <tr >
                    <td bgcolor="ffb6c1" style="width:5%;font-size: 24px" align="center">Price</td>
                    <td bgcolor="ffb6c1" style="width:15%;font-size: 24px" align="center">Topping</td>
                </tr>
            </thead>
                <tbody>
                <%
                ArrayList<CupcakeTopping> cupcakeToppingList = (ArrayList<CupcakeTopping>) CupcakeTopping.getCupcakeToppingsList();
                for (CupcakeTopping cupcakeTopping : cupcakeToppingList) {
                String description = cupcakeTopping.getCupcakeToppingDescription();
                double price = cupcakeTopping.getPriceTopping();
                %>
                    <tr>
                        <td align="center" style="font-size: 18px"><%=price+",-"%></td>
                        <td align="center" style="font-size: 18px"> <%=description%></td>
                    </tr>
                </tbody>
                <% } %>
        </table>
            </div>
        
        </div>
        
        <div style="margin-top: 335px">
           <br>
        <form action="FrontController">
            <p align="center">
            <input type="hidden" name="command" value="goToJsp" />
            <input type="hidden" name="goToJsp" value="login" />
            <input type="submit" value="Login" />
            </p>
        </form>
        <form action="FrontController">
            <p align="center">
            <input type="hidden" name="command" value="goToJsp" />
            <input type="hidden" name="goToJsp" value="registration" />
            <input align="center" type="submit" value="Create user" />
            </p>
        </form>
            </div>
        </div>
    </div>
    </body>
</html>
