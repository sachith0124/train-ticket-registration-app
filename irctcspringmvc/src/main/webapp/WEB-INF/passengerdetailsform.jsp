<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>IRCTC</title>		
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
<body>
	<div>
		<h1>Train Ticket Booking System</h1>
	</div>
	<hr/>
	<div id="task1-div">
	
		<h2><b>PASSENGER DETAILS FORM</b> </h2> <br>
		<hr width="50%">
		
		<form action="TicketCounter" method="post">	
			<input type="hidden" value = "${requestScope.date}" name ="date">
			<input type="hidden" value = "${requestScope.sourceStationId}" name ="sourceStationId">
			<input type="hidden" value = "${requestScope.destinationStationId}" name ="destinationStationId">
			<input type="hidden" value = "${requestScope.trainId}" name ="trainId">
			<input type="hidden" value = "${requestScope.seatType}" name ="seatType">
			<input type="hidden" value = "${requestScope.numSeats}" name ="numPassengers">
			<input type ="hidden" name = "classType" id="classType">
			<% int passengerCount = (Integer)request.getAttribute("numSeats"); %>
			<c:forEach var="count" begin="1" end= "<%= passengerCount %>" >
				<div>
					<h3 style="text-align:left">Enter Details of Passenger <c:out value="${count}"></c:out> </h3> <br/>
					Passenger Name : 
					<input type="text" name="name<c:out value="${count}"></c:out>"> &nbsp;
					Date Of Birth : 
					<input type="text" name="dateOfBirth<c:out value="${count}"></c:out>"> &nbsp;
					Gender : 
					<input type="text" name="gender<c:out value="${count}"></c:out>"> <br/>
					<hr width="50%">
				</div>
			</c:forEach>
			<input type="submit" name="BookTickets"> <br/>
		</form>
		<table align="center">
			<tr>
			<c:forEach items="${trainAttributesMapsListDTO}" var="trainAttributes">
				<th>${trainAttributes.attributeName}</th>
			</c:forEach>
			</tr>				
			<c:forEach items="${trainMapsListDTO}" var="train">
				<tr>
					<td>${train.trainId}</td>
					<td>${train.trainName}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>