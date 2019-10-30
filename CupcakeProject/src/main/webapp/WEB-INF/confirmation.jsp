<%-- 
    Document   : confirmation
    Created on : 09-Oct-2019, 12:14:35
    Author     : Marcus
--%>

<%@page import="logic.Customer"%>
<%@page import="logic.LineItem"%>
<%@page import="logic.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>confirmation</title>
        <link rel="stylesheet" type="text/css" href="css/styleHeader.css">
        <style>
            body {
                background-image: url("decorations/linesBackground.png");
                background-repeat: repeat;
                background-attachment: fixed;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/JSP-Parts/cupcake-Header.jsp"/>
        <%
            Customer customer = (Customer) session.getAttribute("user");
            ShoppingCart shoppingCart = customer.getShoppingCart();
        %>

        <h1 align="center">Please confirm your Order</h1>

        <br>

        <div align="center" style="float:left; width:10%; height:70%">
            <p> </p>
        </div>

        <!-- This div will hold the table for the shopping cart -->
        <div align="center" style="float:left; width:40%; height:70%">
            <table align="center" border = "1" style="float:right; width:65%" bgcolor="fffef2">
                <thead>
                    <tr bgcolor = "#87E187">
                        <td>Cupcake</td>
                        <td align="center">Quantity</td>
                        <td align="center">Subtotal Price</td>
                        <td align="center">Remove</td>
                    </tr>
                </thead>

                <tbody>
                    <%
                        for (LineItem lineItem : shoppingCart.getLineItems()) {
                            String lineItemTopping = Integer.toString(lineItem.getCupcakeTopping().getCupcakeToppingID());
                            String lineItemBottom = Integer.toString(lineItem.getCupcakeBottom().getCupcakeBottomID());
                            String lineItemToppingAndBottomID = lineItemBottom + ":" + lineItemTopping;
                            int qty = lineItem.getAmount();
                            double subtotalPrice = lineItem.getSubTotalPrice();

                    %>
                    <tr>
                        <td><%=lineItem.toString()%></td>
                        <td align="center"><%=qty%></td>
                        <td align="center"><%=subtotalPrice%>,-</td>
                        <td align="center">
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command" value="products" />
                                <input type="hidden" name="removeCupcakeTopAndBottomID" value="<%=lineItemToppingAndBottomID%>" />
                                <input type="submit" value="X" style="background-color:#EE000050"/>
                            </form>
                        </td>
                    </tr>
                    <% }%>
                </tbody>

            </table>
        </div>

        <!-- This div will hold the information -->
        <div align="center" style="float:left; width:25%; height:70%">
            <h2>Shoppingcart Info</h2><br>
            <b><%=shoppingCart.getCupcakeAmount()%> cupcakes</b> <br>
            <b>Totalpris: <%=shoppingCart.getTotalPrice()%>,-</b>
            <%
                String error = (String) request.getAttribute("error");
                if (error == null) {
            %>
            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="confirmation" />
                <p align="center"> <input type="submit" value="Confirm Order"/></p>
            </form>
            <%} else {%>
            <h3 style="color:Tomato"><%=error%></h3>
            <%}%>
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