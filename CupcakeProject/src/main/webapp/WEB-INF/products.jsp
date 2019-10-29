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
        <% if (userLoggedIn) {%>
        <h5 align="right">
            You are logged in as:
            <br>
            <%=user.getUsername()%>
        </h5>
        <% if (userIsCustomer) {%>
        <h5 align="right">
            Your balance is:
            <%=customer.getBalance()%> DKK
            <br>
            Shopping cart:
            <%=cart.getCupcakeAmount()%> Cupcakes
            <br>
            Shopping cart Total Price:
            <%=cart.getTotalPrice()%> Kr.
        </h5>
        <%}%>
        <%}%>

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
                        <%if (userIsCustomer) {%>
                        <td align="center"><input type="radio" name=bottomchoice value="<%=bottomID%>"></td>
                        <%}%>
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
            <p align="center"> Insert your quantity here:<input type="text" name=AmountOf value="1" size="1" style="text-align:center;"/><input type="submit" value="Add"/></p>
            <%}%>
        </form>
        <br>

        <%if (userIsCustomer) {%>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="products" />
            <p align="center"> <input type="submit" value="Go to checkout"/></p>
        </form>
        <%}%>
        
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="goToJsp" />
            <input type="hidden" name="goToJsp" value="shoppage" />
            <p align="center"> <input type="submit" value="Go back to shoppage"/></p>
        </form>
    </body>
</html>