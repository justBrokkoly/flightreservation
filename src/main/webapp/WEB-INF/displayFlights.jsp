<%--
  Created by IntelliJ IDEA.
  User: кирилл
  Date: 07.11.2018
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Flights</title>
</head>
<body>
<h2>Flights:</h2>
<table>
    <tr><th>Airlines</th>
        <th>Departure City</th>
        <th>Arrival City</th>
        <th>Departure Time</th>
    </tr>

<c:forEach items="${flights}" var="flight">
<tr>
    <td>${flight.operatingAirlines}</td>
    <td>${flight.departureCity}</td>
    <td>${flight.arrivalCity}</td>
    <td>${flight.estimatedDepartureTime}</td>
    <td><a href="showCompleteReservation?flightId=${flight.id}">Select</a> </td>
</tr>
</c:forEach>

</table>
</body>
</html>
