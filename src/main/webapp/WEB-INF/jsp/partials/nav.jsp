<%@ taglib prefix="add" uri="application" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<div class="container-fluid" style="padding:0">
    <nav class="navbar-inverse navbar-embossed" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Event Manager</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">Les événements</a></li>
                <li><a href="<add:PathTag endpoint="/event/new"/>">Nouvel événement</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not IS_LOGGED}">
                        <li><a href="#">Se connecter <span class="fui-exit"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    ${CURRENT_USER.firstName} ${CURRENT_USER.lastName}
                                <b class="caret"></b>
                            </a>
                            <span class="dropdown-arrow"></span>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="<add:PathTag endpoint="/logout"/>">COUCOU</a>
                                    <a href="#">COUCOU</a>
                                </li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/flat-ui.min.js"></script>

