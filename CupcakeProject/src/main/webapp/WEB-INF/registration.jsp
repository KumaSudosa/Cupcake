<%-- 
    Document   : registration
    Created on : 09-Oct-2019, 12:08:44
    Author     : Marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
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

        <div style="float: left">
            <p><img src="decorations/large_happy-birthday-cupcake.png" alt="Bookstore Icon Stolen From The Internet" width="250" height="281"></p>
        </div>

        <div class="circle" style="margin: 0 auto; width: 275px">
            <div class="circleFrame">
                <h1 align="center">Registration page</h1>
            </div>
        </div>
        <br>


        <div style="background-color: #fffef2; width:275px; height: 350px; margin: 0 auto; border: solid #aaaaa0 2px; ">


            <form action="FrontController" method="POST">
                <h3 align="center"> username:</h3>
                <p align="center">
                    <input type="text" name="username" value="" placeholder="choose username" style="text-align: center;"/></p>

                <h3 align="center"> password:</h3>
                <p align="center">
                    <input type="password" name="password" value="" placeholder="choose password" style="text-align: center;"/></p>

                <p align="center">
                    <input type="password" name="passwordRepeat" value="" placeholder="write password again" style="text-align: center;"/></p>

                <h3 align="center"> email:</h3>
                <p align="center">
                    <input type="text" name="email" value="" placeholder="choose email" style="text-align: center;"/></p>

                <input type="hidden" name="command" value="registration" />
                <p align="center"> <input type="submit" value="Register" /></p>
            </form>
        </div>
        <%if (request.getAttribute("RegistrationError") != null) {
                IllegalArgumentException registrationError = (IllegalArgumentException) request.getAttribute("RegistrationError");
                String errorMessage = registrationError.getMessage();
        %>
        <br><br>
        <div style="background-color: #fffef2; width:400px; height: 50px; margin: 0 auto; border: solid #aaaaa0 2px; padding-bottom: 15px">
            <h2 align="center"><%=errorMessage%> </h2>
        </div>
        <%}%>

    </body>
</html>