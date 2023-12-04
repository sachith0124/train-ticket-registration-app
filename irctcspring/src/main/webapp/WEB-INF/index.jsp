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
		<div id="indexdiv">
			<h2><b>SELECT ANY SERVICE :</b> </h2>
			<form action="inquirydesk.jsp" method="post">	
				<input type="submit" value="Inquire Desk"> <br/>
			</form>
			<br/>
			<form action="ticketcounter.jsp" method="post">	
				<input type="submit" value="Ticket Counter"> <br/>
			</form>
		</div>
		<br/> <br/>
	</body>
</html>
