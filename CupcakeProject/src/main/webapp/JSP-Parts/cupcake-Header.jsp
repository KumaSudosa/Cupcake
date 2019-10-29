<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="logic.User"%>
<%@page import="presentation.FrontController"%>
<header>
    <div class="container">
        <logo>
            <img class="logoImg" src="decorations/CupcakeLogo.png" />
            <p>Gruppe 3's Cupcakes</p>
        </logo>
        <nav>
            <ul><c:choose>
                    <c:when test="${sessionScope.user != null}">
                        
                        <li>
                            Logged in as: <c:out value="${sessionScope.user.getUsername()}"/>
                        </li>
                        <c:if test="${sessionScope.user.getRole() eq 'c'}">
                            <li>
                                Balance: <c:out value="${sessionScope.user.getBalance()}"/>,-
                            </li>
                        </c:if>
                        <li>
                            <form action="FrontController">
                                <input type="hidden" name="command" value="logout" />
                                <hoverEffect><input type="submit" value="logout"></hoverEffect>
                            </form>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <form action="FrontController">
                                <input type="hidden" name="command" value="goToJsp" />
                                <input type="hidden" name="goToJsp" value="login" />
                                <hoverEffect><input type="submit" value="login"></hoverEffect>
                            </form>
                        </li>
                        <li>
                            <form action="FrontController">
                                <input type="hidden" name="command" value="goToJsp" />
                                <input type="hidden" name="goToJsp" value="registration" />
                                <hoverEffect><input type="submit" value="signup"></hoverEffect>
                            </form>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li>
                    <form action="FrontController">
                        <input type="hidden" name="command" value="goToJsp" />
                        <input type="hidden" name="goToJsp" value="products" />
                        <hoverEffect><input type="submit" value="products"></hoverEffect>
                    </form>
                </li>
                <li>
                    <form action="FrontController">
                        <input type="hidden" name="command" value="goToJsp" />
                        <input type="hidden" name="goToJsp" value="index" />
                        <hoverEffect><input type="submit" value="homepage"></hoverEffect>
                    </form>
                </li>
            </ul>
        </nav>
    </div>
</header>
