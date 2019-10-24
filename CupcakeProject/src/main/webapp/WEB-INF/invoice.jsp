<%-- 
    Document   : invoice
    Created on : 24-10-2019, 12:13:46
    Author     : Michael N. Korsgaard
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="logic.Invoice"%>
<%@page import="logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoices</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            ArrayList<Invoice> invoiceList = Invoice.createCustomerInvoicesFromDB(user.getEmail());
            String username = user.getUsername();
        %>

        <h1>Hello <%=username%></h1>
        <br>
        <table style="width:100%">
            <tr>
                <th>Firstname</th>
            </tr>
            <%
                for (Invoice invoice : invoiceList) {
                    String id = Integer.toString(invoice.getInvoiceID());
            %>
            <tr>
                <td><%=id%></td>
            </tr>
            <%}%>
        </table> 


    </body>
</html>
