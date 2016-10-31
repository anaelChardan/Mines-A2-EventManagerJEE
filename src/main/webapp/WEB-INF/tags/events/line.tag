<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="add" uri="application" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="event" type="fr.mines.event_manager.event.entity.Event" required="true" description="The event to display" rtexprvalue="true" %>


<tr>
    <td>${event.name}</td>
    <td>${event.description}</td>
    <td>${event.address.city}</td>
    <td><fmt:formatDate pattern="'le' dd/MM/yyyy 'à' H'h'mm" value="${event.startDate}" /></td>
    <td><fmt:formatDate pattern="'le' dd/MM/yyyy 'à' H'h'mm" value="${event.endDate}" /></td>
    <c:if test="${event.isAuthor(CURRENT_USER)}">
        <td>
        <c:choose>
            <c:when test="${event.published}">
                OUI
            </c:when>
            <c:otherwise>
                NON
            </c:otherwise>
        </c:choose>
        </td>
    </c:if>
    <td><a class="pull-right btn btn-info" href="<add:PathTag endpoint="/event/"/>${event.id}">Détail</a></td>
</tr>