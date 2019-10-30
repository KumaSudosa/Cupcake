<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="logic.User"%>
<%@page import="presentation.FrontController"%>
<header>
    <div class="container">
        <logo>
            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="goToJsp" />
                <input type="hidden" name="goToJsp" value="index" />
                <button>
                    <img class="logoImg" src="decorations/CupcakeLogo.png" />
                </button>
            </form>
            <p>Gruppe 3's Cupcakes</p>
        </logo>
        <nav>
            <ul>

                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <c:if test="${sessionScope.user.getRole() eq 'c'}">
                            <li>
                                <form action="FrontController" method="POST">
                                    <input type="hidden" name="command" value="products"/>
                                    <input type="submit" value="Shopping cart"><b style="background: #fff; border: 1px solid #666; color: #666; text-align: center; border-radius: 50%; width: 15px; height: 15px; padding: 8px;">${sessionScope.user.getShoppingCart().getCupcakeAmount()}</b>                                   
                                </form>
                            </li>
                        </c:if>
                        <li>
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command" value="goToJsp" />
                                <input type="hidden" name="goToJsp" value="products" />
                                <hoverEffect><input type="submit" value="products"></hoverEffect>
                            </form>
                        </li>
                        <li>
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command" value="goToJsp" />
                                <c:if test="${sessionScope.user.getRole() eq 'c'}">
                                    <input type="hidden" name="goToJsp" value="shoppage" />
                                    <hoverEffect><input type="submit" value="Login  :  ${sessionScope.user.getUsername()}"/></hoverEffect>
                                    </c:if>
                                    <c:if test="${sessionScope.user.getRole() eq 'a'}">
                                    <input type="hidden" name="goToJsp" value="admin" />
                                    <hoverEffect><input type="submit" value="Admin:  ${sessionScope.user.getUsername()}"/></hoverEffect>
                                    </c:if>
                            </form>
                        </li>
                        <li>
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command" value="logout" />
                                <hoverEffect><input type="submit" value="logout"></hoverEffect>
                            </form>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command" value="goToJsp" />
                                <input type="hidden" name="goToJsp" value="login" />
                                <hoverEffect><input type="submit" value="login"></hoverEffect>
                            </form>
                        </li>
                        <li>
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command" value="goToJsp" />
                                <input type="hidden" name="goToJsp" value="registration" />
                                <hoverEffect><input type="submit" value="signup"></hoverEffect>
                            </form>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</header>
