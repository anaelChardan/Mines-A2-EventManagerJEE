<%@ taglib prefix="add" uri="application" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<div class="container-fluid" style="padding:0">
    <nav class="navbar-inverse navbar-embossed" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="<add:PathTag endpoint="/event/"/>">Event Manager</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <c:if test="${IS_LOGGED}">
                    <li><a href="<add:PathTag endpoint="/event/"/>">Les événements</a></li>
                    <li><a href="<add:PathTag endpoint="/event/new"/>">Nouvel événement</a></li>
                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not IS_LOGGED}">
                        <li><a href="<add:PathTag endpoint="/app/login"/>">Se connecter <span class="fui-exit"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li role="presentation" class="dropdown">
                            <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${CURRENT_USER.firstName} ${CURRENT_USER.lastName}
                                <span class="caret"></span>
                            </a>
                            <!--<span class="dropdown-arrow"></span>-->
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="<add:PathTag endpoint="/user/profile"/>">Mon profil</a>
                                </li>
                                <li>
                                    <a href="<add:PathTag endpoint="/app/logout"/>">Déconnexion</a>
                                </li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</div>

