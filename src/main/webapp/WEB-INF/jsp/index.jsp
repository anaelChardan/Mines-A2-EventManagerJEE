<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/assets/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/flat-ui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    <title>Event Manager</title>
</head>
<body>
<div class="palette-turquoise">
    <%@include file="partials/nav.jsp" %>
    <c:if test="${not empty errorMessage}">
        <div class="container-fluid">
            <div class="alert alert-danger">
                    ${errorMessage}
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>
        </div>
    </c:if>
    <jsp:include page="/WEB-INF/jsp/partials/${jspPage}" flush="true"/>
    <%--<%@include file="partials/login.jsp" %>--%>
    <%@include file="partials/footer.jsp" %>
</div>

</body>
</html>
