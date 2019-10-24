<%-- 
    Document   : products
    Created on : 09-Oct-2019, 12:13:39
    Author     : Marcus
--%>

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
                double price = cupcakeBottom.getPriceBottom();
                %>        
                    <tr>
                        <td> <%=description%> </td>
                        <td align="center"> <%=price+",-"%> </td>
                      <td align="center"><input type="radio" name=bottomchoice value=""></td>
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
                double price = cupcakeTopping.getPriceTopping();
                %>
                    <tr>
                        <td><%=description%></td>
                        <td align="center"><%=price+",-"%></td>
                       <td align="center"><input type="radio" name=toppingchoice value=""></td>
                    </tr>
                </tbody>
                <% } %>
                
        </table>
                <br>
                <p align="center"> Insert your quantity here: <td align="center"><input type="text" name=AmountOf value="1" size="1" style="text-align:center;"><input type="submit" value="Add"/></p></td></p>
                
                <br>
                
                <form action="FrontController" method="POST">
                    <input type="hidden" name="command" value="products" />
                    <p align="center"> <input type="submit" value="Go to checkout"/></p>
                </form>
                
                <form action="FrontController" method="POST">
                    <input type="hidden" name="command" value="confirmation" />
                    <p align="center"> <input type="submit" value="Go back to shoppage"/></p>
                </form>
    </body>
</html>