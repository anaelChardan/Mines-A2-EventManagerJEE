<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/assets/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/flat-ui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    <title>Event Manager</title>
</head>
<body>
    <div>
        <%@include file="partials/nav.jsp" %>
        <jsp:include page="/WEB-INF/jsp/partials/${jspPage}" flush="true"/>
        <%--<%@include file="partials/login.jsp" %>--%>
        <%@include file="partials/footer.jsp" %>
    </div>

</body>
</html>
