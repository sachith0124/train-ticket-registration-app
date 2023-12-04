<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
	<head>
		<title> IRCTC </title>		
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
		<div>
			<h1>Train Ticket Booking System</h1>
		</div>
		<hr/>
		<div id="ticketcounterdiv">
			<h2>TICKET COUNTER</h2>
			<h3> BOOK TICKETS </h3>
			<form action="BookingValidator" method="post">
				Enter Date : 
				<input type="text" name="date"> <br/>
				Enter Source Station ID : 
				<input type="text" name="sourceStationId"> <br/>
				Enter Destination Station ID : 
				<input type="text" name="destinationStationId"> <br/>
				Enter Train ID : 
				<input type="text" name="trainId"> <br/>
				Enter Seat Type : 
				<input type="text" name="seatType"> <br/>
				Enter Number of Seats : 
				<input type="text" name="numSeats"> <br/>
				<input type="submit"> <br/>
			</form>
		</div>
	</body>
</html>
