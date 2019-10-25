<%-- 
    Document   : administratorpage
    Created on : 25-Oct-2019, 09:15:18
    Author     : Marcus
--%>

<%@page import="logic.User"%>
<%@page import="persistence.mappers.InvoiceMapper"%>
<%@page import="logic.Invoice"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin page</title>
    </head>
    <body>
        <h1 align='center'>Invoice list</h1>
        
        <%
            User user = (User) session.getAttribute("user");
        %>
        <h5 align="right">
            You are logged in as:
            <br>
            <%=user.getUsername()%>
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
                

        <table align="center" border = "1" width = "15%">

            <thead>
                <tr bgcolor = "#87E187">
                    <td align='center'>all invoices</td>
                </tr>
            </thead>

            <tbody>
                <%
                    ArrayList<Invoice> invoiceList = (ArrayList<Invoice>)request.getAttribute("invoices");
                    for (Invoice invoice : invoiceList) {
                        int ID = invoice.getInvoiceID();
                %>
                <tr>
                    <td align="center"> <%= ID%> </td>
                </tr>
                <%}%>
                
            </tbody> 
        </table>        
    </body>
</html>