package com.epam.rd.irctc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.exceptions.CustomException;
import com.epam.rd.irctc.persistence.AvailableSeatsDao;
import com.epam.rd.irctc.persistence.AvailableSeatsDaoImpl;
import com.epam.rd.irctc.persistence.DateMapDao;
import com.epam.rd.irctc.persistence.DateMapDaoImpl;

public class BookingValidatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Logger logger;
	private DateMapDao dateMapDao;
	private AvailableSeatsDao availableSeatsDao;
    
    public BookingValidatorServlet() {
        super();
        
        logger = Logger.getLogger(BookingValidatorServlet.class);
        dateMapDao = new DateMapDaoImpl();
        availableSeatsDao = new AvailableSeatsDaoImpl();
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String date = request.getParameter("date");
		String sourceStationId = request.getParameter("sourceStationId");
		String destinationStationId = request.getParameter("destinationStationId");
		String trainId = request.getParameter("trainId");
		String seatType = request.getParameter("seatType");
		int numSeats = 0;		
		try {
			numSeats = Integer.parseInt(request.getParameter("numSeats"));
		} catch(NumberFormatException exception) {
			logger.error(exception.getMessage());
		}

		logger.info("\n--------------------------");
		logger.info("BOOKING VALIDATOR SERVLET DATA:");
		logger.info("DATE = " + date);
		logger.info("SOURCE STATION ID = " + sourceStationId);
		logger.info("DESTINATION ID = " + destinationStationId);
		logger.info("TRAIN ID = " + trainId);
		logger.info("SEAT TYPE = " + seatType);
		logger.info("NUM SEATS = " + numSeats);
		logger.info("--------------------------\n");
		
		if(date != null && sourceStationId != null && destinationStationId != null && trainId != null && seatType != null && numSeats != 0) {
			logger.info("Data Received at Booking Validator Servlet\n");
		}
		else {
			logger.error("INVALID Data Received at Booking Validator Servlet !!\n");
		}
		
		try {
			availableSeatsDao.ensureSeatAvailability(dateMapDao.getDayId(date), trainId, sourceStationId, destinationStationId, seatType, numSeats);
		} catch(CustomException exception) {
			logger.error(exception.getMessage());
		}
		
		request.setAttribute("date", date);
		request.setAttribute("sourceStationId", sourceStationId);
		request.setAttribute("destinationStationId", destinationStationId);
		request.setAttribute("trainId", trainId);
		request.setAttribute("seatType", seatType);
		request.setAttribute("numSeats", numSeats);
		
		try {
			request.getRequestDispatcher("passengerdetailsform.jsp").forward(request, response);
		} catch(ServletException | IOException exception) {
			logger.error(exception.getMessage());
		}
	}

}
