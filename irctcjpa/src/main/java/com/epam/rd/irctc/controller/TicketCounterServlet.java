package com.epam.rd.irctc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;
import com.epam.rd.irctc.service.TicketCounter;

public class TicketCounterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger;
	private TicketCounter ticketCounter;

	public TicketCounterServlet() {
		super();

		logger = Logger.getLogger(TicketCounterServlet.class);
		ticketCounter = new TicketCounter();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String date = request.getParameter("date");
		String sourceStationId = request.getParameter("sourceStationId");
		String destinationStationId = request.getParameter("destinationStationId");
		String trainId = request.getParameter("trainId");
		String seatType = request.getParameter("seatType");
		int numPassengers = 0;

		Ticket ticket = null;

		boolean isServiceableRequest = true;

		try {
			numPassengers = (Integer.parseInt(request.getParameter("numPassengers")));
		} catch(NumberFormatException exception) {
			
			isServiceableRequest=false;
			logger.error(exception.getMessage());
			request.setAttribute("errorMessage", exception.getMessage());
		}

		if (isServiceableRequest) {

			try {
				validateParameters(date, sourceStationId, destinationStationId, trainId, seatType);
			} catch (NullPointerException nullPointerException) {

				isServiceableRequest = false;
				logger.error(nullPointerException.getMessage());

				request.setAttribute("errorMessage", nullPointerException.getMessage());
			}
		}

		if (isServiceableRequest) {
			String passengerName;
			String passengerDateOfBirth;
			String passengerGender;

			List<Passenger> passengersList = new ArrayList<>();

			for (int passengerCount = 1; passengerCount <= numPassengers; passengerCount++) {

				passengerName = request.getParameter("name" + passengerCount);
				passengerDateOfBirth = request.getParameter("dateOfBirth" + passengerCount);
				passengerGender = request.getParameter("gender" + passengerCount);

				passengersList.add(new Passenger(passengerName, passengerDateOfBirth, passengerGender));

			}

			try {
				ticket = ticketCounter.bookTicket(date, trainId, sourceStationId, destinationStationId, seatType,
						passengersList);
			} catch (Exception exception) {

				isServiceableRequest = false;

				request.setAttribute("errorMessage", exception.getMessage());
			}
		}

		if (isServiceableRequest) {
			logger.info(ticket);

			request.setAttribute("ticketAttributesMapsListDTO", DTOFactory.getTicketAttributesMapsListDTO());
			request.setAttribute("ticketMapsListDTO", DTOFactory.createTicketMapsListDTO(ticket));
		}

		if (isServiceableRequest) {
			try {
				request.getRequestDispatcher("ticket.jsp").forward(request, response);
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
			String seatType) {

		StringBuilder errorMessageSB = new StringBuilder("");

		errorMessageSB.append(date == null ? "NULL Value at Date\n" : "");
		errorMessageSB.append(sourceStationId == null ? "NULL value at Source Station ID.\n" : "");
		errorMessageSB.append(destinationStationId == null ? "NULL value at Destination Station ID.\n" : "");
		errorMessageSB.append(trainId == null ? "NULL value at Train ID.\n" : "");
		errorMessageSB.append(seatType == null ? "NULL value at Seat Type.\n" : "");

		if (errorMessageSB.length() != 0) {
			throw new NullPointerException(errorMessageSB.toString());
		}
	}

}
