<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Golf Trip</title>
</head>
<body>
	<form action = "editTripServlet" method="post">
	Trip Date: <input type = "text" name = "month" placeholder = "mm" size = "4" value = "${tripToEdit.tripDate.getMonthValue()}">
			   <input type = "text" name = "day" placeholder = "dd" size = "4" value = "${tripToEdit.tripDate.getDayOfMonth()}">
			   <input type = "text" name = "year" placeholder = "year" size = "4" value = "${tripToEdit.tripDate.getYear()}"><br />
	Current Course: <input type = "text" name = "course" value= "${tripToEdit.gScore.getGolfCourse()}">		   
	Current Score: <input type = "text" name = "score" value= "${tripToEdit.gScore.getGolfScore()}"> <br />
		Select Course and Score: <br />
		<select name = "theGolfScore" multiple size = "6">
			<c:forEach items="${requestScope.allGolfScores}" var="currentScore">
				<option value="${currentScore.id}">${currentScore.golfCourse} | ${currentScore.golfScore}</option>
			</c:forEach> 
		</select>
	<input type = "hidden" name = "id" value="${tripToEdit.tripId}">
	<input type = "hidden" name = "scoreid" value="${tripToEdit.gScore.getId()}">
	<input type = "submit" value="Save Edited Score">
	</form>
</body>
</html>