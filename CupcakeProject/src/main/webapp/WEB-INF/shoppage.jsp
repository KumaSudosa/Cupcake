<%-- 
    Document   : shoppage
    Created on : 18-10-2019, 08:27:08
    Author     : 
--%>

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
        
        
        <table border = "1">
                <thead>
                    <tr bgcolor = "#87E187">
                        <td>Bottoms</td>
                        <td>Price</td>
                    </tr>
                </thead>
                
                <tbody>
                    <tr>
                        <td>Chocolate</td>
                        <td>5.0</td>
                        <td align="center"><input type="text" name=AmountOf value="1" size="1" style="text-align:center;"></td>
                        <td align="center"><input type="checkbox" name=id value=""></td>
                    </tr>
                </tbody>
                
        </table>
        <br>
        <table border = "1">
                <thead>
                    <tr bgcolor = "#87E187">
                        <td>Toppings</td>
                        <td>Price</td>
                    </tr>
                </thead>
                
                <tbody>
                    <tr>
                        <td>Chocolate</td>
                        <td>5.0</td>
                        <td align="center"><input type="text" name=AmountOf value="1" size="1" style="text-align:center;"></td>
                        <td align="center"><input type="checkbox" name=id value=""></td>
                    </tr>
                </tbody>
        </table>
        
        
        
        
        
    </body>
</html>