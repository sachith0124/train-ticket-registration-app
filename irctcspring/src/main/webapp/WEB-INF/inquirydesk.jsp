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
		<div id="inquirydeskdiv">
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
				<input type="submit" value="Inquire"> <br/>
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
