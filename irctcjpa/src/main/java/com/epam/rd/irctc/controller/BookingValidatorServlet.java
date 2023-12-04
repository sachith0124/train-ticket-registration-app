package com.epam.rd.irctc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.service.InquiryDesk;
import com.epam.rd.irctc.service.api.Inquirable;

public class BookingValidatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Logger logger;
	private final Inquirable inquiryDesk;

	public BookingValidatorServlet() {
		super();

		logger = Logger.getLogger(BookingValidatorServlet.class);
		inquiryDesk = new InquiryDesk();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String date = request.getParameter("date");
		String sourceStationId = request.getParameter("sourceStationId");
		String destinationStationId = request.getParameter("destinationStationId");
		String trainId = request.getParameter("trainId");
		String seatType = request.getParameter("seatType");
		String numSeatsParam = request.getParameter("numSeats");

		boolean isServiceableRequest = true;

		int numSeats = 0;
		try {
			numSeats = Integer.parseInt(numSeatsParam);
		} catch (NumberFormatException exception) {

			isServiceableRequest = false;
			logger.error(exception.getMessage());

			request.setAttribute("errorMessage", exception.getMessage());
		}

		if (isServiceableRequest) {
			
			try {
				validateParameters(date, sourceStationId, destinationStationId, trainId, seatType, numSeatsParam);
			} catch(NullPointerException nullPointerException) {
				
				isServiceableRequest = false;
				logger.error(nullPointerException.getMessage());

				request.setAttribute("errorMessage", nullPointerException.getMessage());
			}
		}

		if (isServiceableRequest) {
			try {
				inquiryDesk.ensureSeatAvailability(date, trainId, sourceStationId, destinationStationId, seatType,
						numSeats);
			} catch (Exception exception) {

				isServiceableRequest = false;
				logger.error(exception.getMessage());

				request.setAttribute("errorMessage", exception.getMessage());
			}
		}

		if (isServiceableRequest) {

			request.setAttribute("date", date);
			request.setAttribute("sourceStationId", sourceStationId);
			request.setAttribute("destinationStationId", destinationStationId);
			request.setAttribute("trainId", trainId);
			request.setAttribute("seatType", seatType);
			request.setAttribute("numSeats", numSeats);
		}

		if (isServiceableRequest) {
			try {
				request.getRequestDispatcher("passengerdetailsform.jsp").forward(request, response);
			} catch (ServletException | IOException exception) {
				logger.error(exception.getMessage());
			}
		} else {

			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} catch (ServletException | IOException innerException) {
				logger.error(innerException.getMessage());
			}
		}
	}

	public void validateParameters(String date, String sourceStationId, String destinationStationId, String trainId,
			String seatType, String numSeatsParam) {

		StringBuilder errorMessageSB = new StringBuilder("");

		errorMessageSB.append(date == null ? "NULL Value at Date\n" : "");
		errorMessageSB.append(sourceStationId == null ? "NULL value at Source Station ID.\n" : "");
		errorMessageSB.append(destinationStationId == null ? "NULL value at Destination Station ID.\n" : "");
		errorMessageSB.append(trainId == null ? "NULL value at Train ID.\n" : "");
		errorMessageSB.append(seatType == null ? "NULL value at Seat Type.\n" : "");
		errorMessageSB.append(numSeatsParam == null ? "NULL value at Number of Seats.\n" : "");

		if (errorMessageSB.length() != 0) {
			throw new NullPointerException(errorMessageSB.toString());
		}
	}

}
