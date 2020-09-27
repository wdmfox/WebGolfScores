<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Golf Score List</title>
</head>
<body>
	<form method = "post" action = "navigationServlet">
	<table>
	<c:forEach items="${requestScope.allScores}" var="currentscore">
		<tr> 
			<td><input type="radio" name="id" value="${currentscore.id}"></td>
			<td>${currentscore.golfCourse}</td>
			<td>${currentscore.golfScore}</td>
		</tr>
	</c:forEach>
	</table>
	<input type = "submit" value = "edit" name="doThisToScore">
	<input type = "submit" value = "delete" name="doThisToScore">
	<input type = "submit" value = "add" name="doThisToScore">
	</form>
</body>
</html>