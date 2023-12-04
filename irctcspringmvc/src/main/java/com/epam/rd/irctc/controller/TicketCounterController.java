package com.epam.rd.irctc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;
import com.epam.rd.irctc.service.TicketCounter;
import com.epam.rd.irctc.service.TicketServices;

@Controller
public class TicketCounterController  {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");
	
	private Logger logger;
	private TicketServices ticketCounter;
    
    public TicketCounterController() {
        super();
        
        logger = Logger.getLogger(TicketCounterController.class);
        ticketCounter = context.getBean(TicketCounter.class);
    }
    
    @RequestMapping("/TicketCounter")
	protected ModelAndView bookTicket(HttpServletRequest request) {
		
		String date = request.getParameter("date");
		String sourceStationId = request.getParameter("sourceStationId");
		String destinationStationId = request.getParameter("destinationStationId");
		String trainId = request.getParameter("trainId");
		String seatType = request.getParameter("seatType");
		int numPassengers = 0;

		try {
			numPassengers = (Integer.parseInt(request.getParameter("numPassengers")));
		} catch(NumberFormatException exception) {
			logger.error(exception.getMessage());
		}
		

		logger.info("\n--------------------------");
		logger.info("TICKET COUNTER SERVLET DATA:");
		logger.info("DATE = " + date);
		logger.info("SOURCE STATION ID = " + sourceStationId);
		logger.info("DESTINATION ID = " + destinationStationId);
		logger.info("TRAIN ID = " + trainId);
		logger.info("SEAT TYPE = " + seatType);
		logger.info("NUM PASSENGERS = " + numPassengers);
		
		if(date != null && sourceStationId != null && destinationStationId != null && trainId != null && seatType != null && numPassengers != 0) {
			logger.info("Data Received at Booking Validator Servlet\n");
		}
		else {
			logger.error("INVALID Data Received at Booking Validator Servlet !!\n");
		}
		
		String passengerName;
		String passengerDateOfBirth;
		String passengerGender;
		
		List<Passenger> passengersList = new ArrayList<>();
		

		logger.info("PASSENGERS DETAILS: ");
		for(int passengerCount = 1; passengerCount <= numPassengers; passengerCount++) {
			
			passengerName = request.getParameter("name"+passengerCount);
			passengerDateOfBirth = request.getParameter("dateOfBirth"+passengerCount);
			passengerGender = request.getParameter("gender"+passengerCount);
			
			passengersList.add(new Passenger(passengerName, passengerDateOfBirth, passengerGender));
			
			logger.info("\t Passenger" + passengerCount + " Details: " + passengerName + ", " + passengerDateOfBirth + ", " + passengerGender);
		}
		logger.info("--------------------------\n");
		
		Ticket ticket = ticketCounter.bookTicket(date, trainId, sourceStationId, destinationStationId, seatType, passengersList);
		
		logger.info(ticket);
		
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}

}
