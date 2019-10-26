<%-- 
    Document   : products
    Created on : 09-Oct-2019, 12:13:39
    Author     : Marcus
--%>

<%@page import="logic.ShoppingCart"%>
<%@page import="logic.User"%>
<%@page import="logic.CupcakeTopping"%>
<%@page import="logic.CupcakeBottom"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchasing cupcakes</title>
    </head>
    <body>

        <%
            User user = (User) session.getAttribute("user");
            ShoppingCart cart = user.getShoppingCart();
        %>
        <h5 align="right">
            You are logged in as:
            <br>
            <%=user.getUsername()%>
        </h5>
        <h5 align="right">
            Your balance is:
            <%=user.getBalance()%> DKK
            <br>
            Shopping cart:
            <%=cart.getCupcakeAmount()%> Cupcakes
            <br>
            Shopping cart Total Price:
            <%=cart.getTotalPrice()%> Kr.
        </h5>

        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="shoppage" />
            <table align="center" border = "1" width = "15%">
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
                        <td align="center"><input type="radio" name=bottomchoice value="<%=bottomID%>"></td>
                    </tr>
                </tbody>
                <% } %>
            </table>

            <br>

            <table align="center" border = "1" width = "15%">
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
                        <td align="center"><input type="radio" name=toppingchoice value="<%=toppingID%>"></td>
                    </tr>
                </tbody>
                <%}%>

            </table>

            <br>
            <br>
            <p align="center"> Insert your quantity here:<input type="text" name=AmountOf value="1" size="1" style="text-align:center;"/><input type="submit" value="Add"/></p>
        </form>
        <br>

        <%
            if (request.getAttribute("error") != null) {
                String errorMsg = (String) request.getAttribute("error");
        %>
        <h2 align="center"><%=errorMsg%></h2>
        <%}%>

        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="products" />
            <p align="center"> <input type="submit" value="Go to checkout"/></p>
        </form>

        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="goToShoppage" />
            <p align="center"> <input type="submit" value="Go back to shoppage"/></p>
        </form>
    </body>
</html>