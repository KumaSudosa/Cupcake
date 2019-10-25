<%-- 
    Document   : confirmation
    Created on : 09-Oct-2019, 12:14:35
    Author     : Marcus
--%>

<%@page import="logic.LineItem"%>
<%@page import="logic.ShoppingCart"%>
<%@page import="logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>confirmation</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            ShoppingCart shoppingCart = user.getShoppingCart();
        %>

        <h1 align="center">Please confirm your Order</h1>

        <br>

        <div align="center" style="float:left; width:25%; height:70%">
            <p> </p>
        </div>

        <!-- This div will hold the table for the shopping cart -->
        <div align="center" style="float:left; width:25%; height:70%">
            <table align="center" border = "1" width = "15%">
                <thead>
                    <tr bgcolor = "#87E187">
                        <td>Toppings</td>
                        <td>Price</td>
                    </tr>
                </thead>

                <tbody>
                    <%
                        for (LineItem lineItem : shoppingCart.getLineItems()) {

                    %>
                    <tr>
                        <td></td>
                        <td align="center"></td>
                        <td align="center"><input type="radio" name=toppingchoice value=""></td>
                    </tr>
                    <% }%>
                </tbody>
                
            </table>
        </div>

        <!-- This div will hold the information -->
        <div align="center" style="float:left; width:25%; height:70%">
            <h1>Right</h1>
            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="confirmation" />
                <p align="center"> <input type="submit" value="Confirm Order"/></p>
            </form>
        </div>

        <div align="center" style="float:left; width:25%; height:70%">
            <p> </p>
        </div>
        <div style="position:fixed; bottom:40px; left: 50%; width:30%; margin-left:-15%;">

            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="shoppage" />
                <p align="center"> <input type="submit" value="Go back to the products page"/></p>
            </form>
        </div>
    </body>
</html>