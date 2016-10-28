<%@ taglib prefix="add" uri="application" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--<%@ taglib prefix="part" uri="partials" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%@ attribute name="event" type="fr.mines.event_manager.event.entity.Event" required="true" description="The event to display" rtexprvalue="true" %>


<tr>
    <td>${event.name}</td>
    <td>${event.description}</td>
    <td>${event.address.city}</td>
    <%--<td><fmt:formatDate pattern="'le' dd/MM/yyyy 'à' H'h'mm" value="${event.startDate}" /></td>--%>
    <%--<td><fmt:formatDate pattern="'le' dd/MM/yyyy 'à' H'h'mm" value="${event.endDate}" /></td>--%>
    <td>${event.startDate}</td>
    <td>${event.endDate}</td>
    <td><a href="<add:PathTag endpoint="/event/"/>${event.id}">Détail</a></td>
</tr>