<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Golf Trips List</title>
</head>
<body>
	<form method = "post" action = "tripNavigationServlet">
	<table>
	<c:forEach items="${requestScope.allTrips}" var="currenttrip">
		<tr> 
			<td><input type="radio" name="id" value="${currenttrip.tripId}"></td>
				<td colspan="3">Trip Date: ${currenttrip.tripDate}</td></tr>
				<tr><td colspan="3">Course: ${currenttrip.gScore.golfCourse}</td></tr>
				<tr><td colspan="3">Score: ${currenttrip.gScore.golfScore}</td></tr>
	</c:forEach>
	</table>
	<input type = "submit" value = "edit" name="doThisToTrip">
	<input type = "submit" value = "delete" name="doThisToTrip">
	<input type = "submit" value = "add" name="doThisToTrip">
	</form>
	<a href="addTripServlet">Enter a new golf trip</a>
</body>
</html>