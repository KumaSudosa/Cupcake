<%-- 
    Document   : invoice
    Created on : 24-10-2019, 12:13:46
    Author     : Michael N. Korsgaard
--%>

<%@page import="logic.LineItem"%>
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
            ArrayList<Invoice> invoiceList = null;
            int tableSize = 40;
            if (User.isUserCustomer(user)) {
                invoiceList = Invoice.createCustomerInvoicesFromDB(user.getEmail());
            } else if (User.isUserAdmin(user)) {
                invoiceList = Invoice.getInvoices();
                tableSize = 60;
            }
            String username = user.getUsername();
        %>

        <h1 align="center">Hello <%=username%></h1>
        <br>
        <table border = "1" align = "center" style="width:<%=tableSize%>%">
            <thead>
                <tr bgcolor = "#87E187">
                    <th style="width:15%">Invoices</th>
                    <th style="width:55%">Details</th>
                    <th style="width:15%">Order Date</th>
                    <th style="width:15%">Total Price</th>
                    <%if(User.isUserAdmin(user)) {%>
                    <th style="width:15%">Customer</th>
                    <%}%>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Invoice invoice : invoiceList) {
                        String id = Integer.toString(invoice.getInvoiceID());
                        String customer = invoice.getUser().getEmail();
                        String date = invoice.getDate();
                        String totalPrice = Double.toString(invoice.getTotalPrice());
                %>
                <tr>
                    <td align="center"><%=id%></td>
                    <td align="center">
                        <table style="width:100%">
                            <tbody>
                                <%
                                    for (LineItem lineItem : invoice.getLineItems()) {
                                        String cupcakeDescription = lineItem.toString();
                                        int qty = lineItem.getAmount();
                                        double price = lineItem.getSubTotalPrice();
                                %>
                                <tr>
                                    <td align="right" style="width:20%"><%=qty%>x  </td>
                                    <td align="left" style="width:60%"><%=cupcakeDescription%></td>
                                    <td align="left" style="width:20%"><%=price%>,-</td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </td>
                    <td align="center"><%=date%></td>
                    <td align="center"><%=totalPrice%>,-</td>
                    <%if(User.isUserAdmin(user)) {%>
                    <td align="center"><%=customer%></td>
                    <%}%>
                </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="goToJsp" />
            <input type="hidden" name="goToJsp" value="shoppage" />
            <p align="center"> <input type="submit" value="Go back to shoppage"/></p>
        </form>



    </body>
</html>
