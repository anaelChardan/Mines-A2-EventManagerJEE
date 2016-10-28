<%@ taglib prefix="add" uri="application" %>
<%--<%@ taglib prefix="part" uri="partials" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%@ attribute name="event" type="fr.mines.event_manager.event.entity.Event" required="true" description="The event to display" rtexprvalue="true" %>


<tr>
    <td>${event.name}</td>
    <td>${event.description}</td>
    <td>${event.address.city}</td>
    <td>${event.startDate}</td>
    <td>${event.endDate}</td>
    <td><a href="<add:PathTag endpoint="/event/"/>${event.id}">Détail</a></td>
</tr>