<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title> IRCTC </title>
		<link rel="icon" href="resources/favicon.jpg"/>
		<link rel="stylesheet" type="text/css" href="style.css" sizes="128x128">
	</head>
<body>
	<div>
			<h1>Train Ticket Booking System</h1>
		</div>
		<hr/>
		<div id="ticket-confimration-div">
			<h2><b>TICKET CONFIRMATION</b> </h2>
			<br><h3>Your Ticket is Confirmed !!</h3>
			<hr/>
			<h2>Ticket</h2>
			<br/>
			<table align="center">
				<tr>
				<c:forEach items="${ticketAttributesMapsListDTO}" var="ticketAttributes">
					<th>${ticketAttributes.attributeName}</th>
				</c:forEach>
				</tr>				
				<c:forEach items="${ticketMapsListDTO}" var="ticket">
					<tr>
						<td>${ticket.pnr}</td>
						<td>${ticket.date}</td>
						<td>${ticket.trainId}</td>
						<td>${ticket.sourceStationId}</td>
						<td>${ticket.destinationStationId}</td>
						<td>${ticket.numPassengers}</td>
						<td>${ticket.seatType}</td>
						<td>${ticket.ticketFare}</td>
					</tr>
				</c:forEach>
			</table>
		</div>			
		<br>
		<hr/>
		<br><h3>HAPPY JOURNEY !!</h3>
	
</body>
</html>