<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action = "createTripServlet" method = "post">
		Trip Date: <input type = "text" name = "month" placeholder = "mm" size = "4">
				   <input type = "text" name = "day" placeholder = "dd" size = "4">
				   <input type = "text" name = "year" placeholder = "year" size = "4"><br />
		Select Course and Score: <br />
		<select name = "theGolfScore" multiple size = "6">
			<c:forEach items="${requestScope.allGolfScores}" var="currentScore">
				<option value="${currentScore.id}">${currentScore.golfCourse} | ${currentScore.golfScore}</option>
			</c:forEach> 
		</select>
		<input type = "submit" value = "Create Golf Trip">
	</form>
</body>
</html>