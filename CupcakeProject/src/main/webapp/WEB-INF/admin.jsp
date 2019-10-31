<%-- 
    Document   : administratorpage
    Created on : 25-Oct-2019, 09:15:18
    Author     : Marcus & Andreas
--%>

<%@page import="logic.Customer"%>
<%@page import="logic.User"%>
<%@page import="logic.Admin"%>
<%@page import="persistence.mappers.InvoiceMapper"%>
<%@page import="logic.Invoice"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin page</title>
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
        <div class="circle" style="margin: 0 auto; width: 350px">
            <div class="circleFrame">
                <h1 align='center'>Admin Control Panel</h1>
            </div>
        </div>

        <%
            Admin admin = (Admin) session.getAttribute("user");
        %>
        <br>
        <br>
        <h5 align="right">
            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="frontpage" />

            </form>
        </h5>
        <form action="FrontController">
            <table border = "1" style="float: left; width:40%; margin-left: 10%; margin-right: 10%" bgcolor="fffef2">
                <thead>
                    <tr bgcolor = "#87E187">
                        <td>E-mail</td>
                        <td>Balance</td>
                        <td>Select</td>
                </thead>    
                <tbody>
                    <%
                        ArrayList<User> userList = (ArrayList<User>) Customer.getUserList();
                        for (User user : userList) {
                            if (User.isUserCustomer(user)) {
                                Customer customer = (Customer) user;
                                String email = customer.getEmail();
                                double balance = customer.getBalance();


                    %>
                    <tr>
                        <td><%=email%></td>
                        <td><%=balance%></td>
                        <td><input type="radio" name="selectedUser" value="<%=email%>" /></td>
                    </tr>
                    <%}%>
                    <%}%>
                </tbody>
            </table>
            <div style="position: absolute; right: 20%; background-color: #fffef2; width:250px; height: 100px; border: solid #aaaaa0 2px; ">
                <div style="padding-top: 20px; padding-left: 14px">
                    <input type="submit" value="Set Balance" style="width: 100px" name="setBalanceButton"/>
                    <input type="text" name="setBalance" value="" style="width: 116px" placeholder="Add to balance"/>
                </div>
                <div style="padding-top: 15px; padding-left: 14px">
                    <input type="hidden" name="command" value="adminfunctions" />
                    <input type="submit" value="See Invoice" style="width: 100px" name="singleInvoice"/>
                    <input type="submit" value="See All Invoices" style="width: 120px" name="allInvoice"/>
                </div>
            </div>
        </form>

        <br>
        <br>
        <%if (request.getAttribute("error") != null) {
                String errorMessage = (String) request.getAttribute("error");
        %>
        <div align="center" style="position: absolute; right: 20%; top: 400px; padding-bottom: 20px; background-color: #fffef2; width:350px; height: 50px; margin: 0 auto; border: solid #aaaaa0 2px; ">
                <h2 style="color:Tomato"><%=errorMessage%></h2>
        </div>
        <%}%>
    </body>
</html>