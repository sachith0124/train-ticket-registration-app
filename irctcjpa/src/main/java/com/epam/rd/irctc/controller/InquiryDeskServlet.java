package com.epam.rd.irctc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.AvailableSeatsLog;
import com.epam.rd.irctc.model.Train;
import com.epam.rd.irctc.service.InquiryDesk;

public class InquiryDeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1186807521227509413L;

	private InquiryDesk inquiryDesk;

	private Logger logger;

	public InquiryDeskServlet() {

		super();
		inquiryDesk = new InquiryDesk();
		logger = Logger.getLogger(InquiryDeskServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String date = request.getParameter("date");
		String sourceStationId = request.getParameter("sourceStationId");
		String destinationStationId = request.getParameter("destinationStationId");
		String seatType = request.getParameter("seatType");

		boolean isServiceableRequest = true;

		List<Train> trainsList = null;
		List<AvailableSeatsLog> minAvailableSeatsLogList = null;
		Map<Train, Integer> distanceBetweenStationsEnRoute = null;

		try {
			validateParameters(date, sourceStationId, destinationStationId, seatType);

		} catch (NullPointerException nullPointerException) {

			isServiceableRequest = false;
			request.setAttribute("errorMessage", nullPointerException.getMessage());
		}

		if (isServiceableRequest) {

			try {
				trainsList = inquiryDesk.getAvailableTrains(date, sourceStationId, destinationStationId, seatType);
				minAvailableSeatsLogList = inquiryDesk.getMinAvailableSeatsLogs(date, trainsList, sourceStationId,
						destinationStationId);
				distanceBetweenStationsEnRoute = inquiryDesk.getDistanceBetweenStationsEnRoute(trainsList,
						sourceStationId, destinationStationId);

			} catch (RuntimeException runtimeException) {

				isServiceableRequest = false;
				request.setAttribute("errorMessage", runtimeException.getMessage());
			}
		}

		if (isServiceableRequest) {

			logger.info(trainsList);
			request.setAttribute("trainAttributesMapsListDTO", DTOFactory.getTrainAttributesMapsListDTO());
			request.setAttribute("trainMapsListDTO",
					DTOFactory.createTrainMapsListDTO(trainsList, minAvailableSeatsLogList, distanceBetweenStationsEnRoute));
		}

		if (isServiceableRequest) {
			try {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (ServletException | IOException exception) {
				logger.error(exception.getMessage());
			} finally {
				trainsList.clear();
			}
		} else {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} catch (ServletException | IOException innerException) {
				logger.error(innerException.getMessage());
			}
		}

	}

	public void validateParameters(String date, String sourceStationId, String destinationStationId, String seatType) {

		StringBuilder errorMessageSB = new StringBuilder("");

		errorMessageSB.append(date == null ? "NULL Value at Date\n" : "");
		errorMessageSB.append(sourceStationId == null ? "NULL value at Source Station ID.\n" : "");
		errorMessageSB.append(destinationStationId == null ? "NULL value at Destination Station ID.\n" : "");
		errorMessageSB.append(seatType == null ? "NULL value at Seat Type.\n" : "");

		if (errorMessageSB.length() != 0) {
			throw new NullPointerException(errorMessageSB.toString());
		}
	}

}
