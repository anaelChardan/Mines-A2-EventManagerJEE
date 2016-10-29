<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/assets/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/flat-ui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    <meta charset="utf-8">
    <title>Event Manager</title>
</head>
<body>

<%@include file="partials/nav.jsp" %>
<div class="container-fluid">

    <c:if test="${not empty errorMessages}">
        <div class="container-fluid" style="margin-top:10px">
            <div class="alert alert-danger">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <lu>
                    <c:forEach items="${errorMessages}" var="errorMessage">
                        <li>${errorMessage.value}</li>
                    </c:forEach>
                </lu>
            </div>
        </div>
    </c:if>
    <jsp:include page="/WEB-INF/jsp/partials/${jspPage}" flush="true"/>
</div>
<%@include file="partials/footer.jsp" %>


<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/flat-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/respond.min.js"></script>

</body>
</html>
