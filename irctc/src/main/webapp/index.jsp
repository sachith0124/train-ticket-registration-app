<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
	<head>
		<title> IRCTC </title>
	</head>
	<body>
		<div>
			<h1 align="center">WELCOME TO IRCTC</h1> <br/>
		</div>
		<hr/>
		<div id="task1-div">
			<h2 align="center">TASK 1: INQUIRY DESK - INQUIRE ABOUT AVAILABLE TRAINS </h2>
			<form action="InquiryDesk" method="post">				
				Enter Date : <input type="text" name="date"> <br/> <br/>
				Enter Source Station ID : <input type="text" name="sourceStationId"> <br/> <br/>
				Enter Destination Station ID : <input type="text" name="destinationStationId"> <br/> <br/>
				Enter Seat Type : <input type="text" name="seatType"> <br/> <br/>
				<input type="submit">
				<br/> <br/>
				<table>	
					<tr>
						<th>Train ID</th>
						<th>Train Name</th>
					</tr>				
					<c:forEach items="${trainDTO}" var="train">
						<tr>
							<td>${train.trainId}</td>
							<td>${train.trainName}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
		<br/> <br/>
		<hr/>
		<div id="task2-div">
			<h2 align="center">TASK2: TICKET COUNTER - BOOK TICKETS </h2>
			<form action="TicketCounter">
			</form>
		</div>
	</body>
</html>