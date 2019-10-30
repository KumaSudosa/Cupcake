<%-- 
    Document   : products
    Created on : 09-Oct-2019, 12:13:39
    Author     : Marcus
--%>

<%@page import="logic.Customer"%>
<%@page import="logic.ShoppingCart"%>
<%@page import="logic.User"%>
<%@page import="logic.CupcakeTopping"%>
<%@page import="logic.CupcakeBottom"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .square {
                height: 50px;
                width: 230px;
                background-color: #b9ffb9;
                text-align: left;
                margin-bottom: 20px;
                margin-top: 50px;
                border: 1px solid #444;
            }
            body {
                background-image: url("decorations/linesBackground.png");
                background-repeat: repeat;
                background-attachment: fixed;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchasing cupcakes</title>
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

        <% if (userLoggedIn) {
                if (userIsCustomer) {%>

        <div  style="float:right; width: 15%; padding-bottom: 20px; padding-right: 50px">
            <h5 class="square" align="right" border="">
                Your balance:
                <%=customer.getBalance()%> Kr.
                <br>
                Shopping cart:
                <%=cart.getCupcakeAmount()%> Cupcakes
                <br>
                Shopping cart Total Price:
                <%=cart.getTotalPrice()%> Kr.
            </h5>
            <%}%>
            <%}%>
        </div>

        <form action="FrontController" method="POST"> 
            <input type="hidden" name="command" value="shoppage" /> 
            <table align="center" border = "1" width = "15%" style="margin-left: 653px" bgcolor="fffef2">
                <thead>
                    <tr bgcolor = "#87E187">
                        <td>Bottoms</td>
                        <td>Price</td>
                    </tr>
                </thead>

                <tbody>
                    <%
                        ArrayList<CupcakeBottom> cupcakeBottomList = (ArrayList<CupcakeBottom>) CupcakeBottom.getCupcakeBottomsList();
                        for (CupcakeBottom cupcakeBottom : cupcakeBottomList) {
                            String description = cupcakeBottom.getCupcakeBottomDescription();
                            int bottomID = cupcakeBottom.getCupcakeBottomID();
                            double price = cupcakeBottom.getPriceBottom();
                    %>        
                    <tr>
                        <td> <%=description%> </td>
                        <td align="center"> <%=price + ",-"%> </td>
                        <%if (userIsCustomer) {%>
                        <td align="center"><input type="radio" name=bottomchoice value="<%=bottomID%>"></td>
                            <%}%>
                    </tr>
                </tbody>
                <% } %>
            </table>

            <br>

            <table align="center" border = "1" width = "15%" bgcolor="fffef2">
                <thead>
                    <tr bgcolor = "#87E187">
                        <td>Toppings</td>
                        <td>Price</td>
                    </tr>
                </thead>

                <tbody>
                    <%
                        ArrayList<CupcakeTopping> cupcakeToppingList = (ArrayList<CupcakeTopping>) CupcakeTopping.getCupcakeToppingsList();
                        for (CupcakeTopping cupcakeTopping : cupcakeToppingList) {
                            String description = cupcakeTopping.getCupcakeToppingDescription();
                            int toppingID = cupcakeTopping.getCupcakeToppingID();
                            double price = cupcakeTopping.getPriceTopping();
                    %>
                    <tr>
                        <td><%=description%></td>
                        <td align="center"><%=price + ",-"%></td>
                        <%if (userIsCustomer) {%>
                        <td align="center"><input type="radio" name=toppingchoice value="<%=toppingID%>"></td>
                            <%}%>
                    </tr>
                </tbody>
                <%}%>

            </table>

            <br>
            <br>
            <%if (userIsCustomer) {%>
            <div class="circle" style="margin: 0 auto; width: 300px">
                <div class="circleFrame">
                    <p align="center"> Insert your quantity here:<input type="text" name=AmountOf value="1" size="1" style="text-align:center;"/><input type="submit" value="Add"/></p>
                </div>
            </div>
            <%}%>
        </form>
        <%if (request.getAttribute("error") != null) {
                String errorMsg = (String) request.getAttribute("error");
        %>
        <br>
        <div class="circle" style="margin: 0 auto; width: 25%">
            <div class="circleFrame">
                <h3 align="center"><%=errorMsg%></h3>
            </div>
        </div>
        <%}%>
        <br>

    </body>
</html>