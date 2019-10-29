<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="logic.User"%>
<%@page import="presentation.FrontController"%>
<header>
    <div class="container">
        <logo>
            <form action="FrontController">
                <input type="hidden" name="command" value="goToJsp" />
                <input type="hidden" name="goToJsp" value="index" />
                <button>
                    <img class="logoImg" src="decorations/CupcakeLogo.png" />
                </button>
            </form>
            <p>Gruppe 3's Cupcakes</p>
        </logo>
        <nav>
            <ul><c:choose>
                    <c:when test="${sessionScope.user != null}">

                        <c:if test="${sessionScope.user.getRole() eq 'c'}">
                            <li>
                                Balance: <c:out value="${sessionScope.user.getBalance()}"/>,-
                            </li>
                        </c:if>
                        <li>
                            <form action="FrontController">
                                <input type="hidden" name="command" value="goToJsp" />
                                <c:if test="${sessionScope.user.getRole() eq 'c'}">
                                    <input type="hidden" name="goToJsp" value="shoppage" />
                                    Logged in as: <input type="submit" value=<c:out value="${sessionScope.user.getUsername()}"/>>
                                </c:if>
                                <c:if test="${sessionScope.user.getRole() eq 'a'}">
                                    <input type="hidden" name="goToJsp" value="admin" />
                                    Admin: <input type="submit" value=<c:out value="${sessionScope.user.getUsername()}"/>>
                                </c:if>
                            </form>
                        </li>
                        <li>
                            <form action="FrontController">
                                <input type="hidden" name="command" value="logout" />
                                <hoverEffect><input type="submit" value="logout"></hoverEffect>
                            </form><hoverEffect
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
