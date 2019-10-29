<%-- 
    Document   : administratorpage
    Created on : 25-Oct-2019, 09:15:18
    Author     : Marcus
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
    </head>
    <body>
        <jsp:include page="/JSP-Parts/cupcake-Header.jsp"/>
        <h1 align='center'>Invoice list</h1>

        <%
            Admin admin = (Admin) session.getAttribute("user");
        %>
        <h5 align="right">
            You are logged in as:
            <br>
            <%=admin.getUsername()%>
            <br> 
            Admin account
        </h5>

        <h5 align="right">
            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="frontpage" />

                <button onclick="myFunction()">Logout</button>
                <script>
                    function myFunction() {
                        alert("Logging you out now");
                    }
                </script>

            </form>
        </h5>
        <form action="FrontController">
            <table border = "1" style="float: left; width:40%; margin-left: 10%; margin-right: 10%">
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
            <input type="submit" value="Set Balance" style="width: 100px" name="setBalanceButton"/>
            <input type="text" name="setBalance" value="" style="width: 116px" placeholder="Add to balance"/>
            <br>
            <br>

            <input type="hidden" name="command" value="adminfunctions" />
            <input type="submit" value="See Invoice" style="width: 100px" name="singleInvoice"/>
            <input type="submit" value="See All Invoices" style="width: 120px" name="allInvoice"/>
        </form>

        <br>
        <br>
        <%if (request.getAttribute("error") != null) {
                String errorMessage = (String) request.getAttribute("error");
        %>
        <h2 color="red"><%=errorMessage%></h2>
        <%}%>
    </body>
</html>