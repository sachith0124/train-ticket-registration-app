<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
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
		<div id="network-map-div">
			<h2><b>NETWORK MAP</b> </h2>
			<img src="resources/network-map.png" alt="Network Map">
			<br/> <hr/>
		</div>
		<hr/>
		<div id="task1-div">
			<h2><b>INQUIRY DESK</b> </h2>
			<br><h3>INQUIRE AVAILABILITY OF TRAINS</h3>
			<form action="InquiryDesk" method="post">				
				Enter Date : 
				<input type="text" name="date"> 
				<!-- select name="servicableDate">
				    <c:forEach items="${serviceableDatesMapsListDTO}" var="servicableDate">
				        <option value="${servicableDate.date}">${servicableDate.date}</option>
				    </c:forEach>
				</select-->
				<br/>
				Enter Source Station ID : 
				<input type="text" name="sourceStationId"> <br/>
				Enter Destination Station ID : 
				<input type="text" name="destinationStationId"> <br/>
				Enter Seat Type : 
				<input type="text" name="seatType"> <br/>
				<input type="submit"> <br/>
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
						<td>${train.acSeatFare}</td>
						<td>${train.numAcSeats}</td>
						<td>${train.nonAcSeatFare}</td>
						<td>${train.numNonAcSeats}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br/> <br/>
		<hr/>
		<div id="task2-div">
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
