<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Golf Score</title>
</head>
<body>
	<form action = "editScoreServlet" method="post">
	Course: <input type = "text" name = "course" value= "${scoreToEdit.golfCourse}">
	Score: <input type = "text" name = "score" value= "${scoreToEdit.golfScore}">
	<input type = "hidden" name = "id" value="${scoreToEdit.id}">
	Select Course and Score: <br />
		<select name = "theGolfScore" multiple size = "6">
			<c:forEach items="${requestScope.allGolfScores}" var="currentScore">
				<option value="${currentScore.id}">${currentScore.golfCourse} | ${currentScore.golfScore}</option>
			</c:forEach> 
		</select>
	<input type = "submit" value="Save Edited Score">
	</form>
</body>
</html>