package com.epam.rd.irctc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.rd.irctc.persistence.AvailableSeatsLogDao;
import com.epam.rd.irctc.persistence.AvailableSeatsLogDaoImpl;
import com.epam.rd.irctc.persistence.ServiceableDateDao;
import com.epam.rd.irctc.persistence.ServiceableDateDaoImpl;

@Controller
public class BookingValidatorController {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");
       
	private Logger logger;
	private ServiceableDateDao dateMapDao;
	private AvailableSeatsLogDao availableSeatsDao;
    
    public BookingValidatorController() {
        super();
        
        logger = Logger.getLogger(BookingValidatorController.class);
        dateMapDao = context.getBean(ServiceableDateDaoImpl.class);
        availableSeatsDao = context.getBean(AvailableSeatsLogDaoImpl.class);
    }
    
    @RequestMapping("/BookingValidator")
	protected ModelAndView validateBookingDetails(HttpServletRequest request) {

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
		} catch(Exception exception) {
			logger.error(exception.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("passnegersdetailsform");
		
		modelAndView.addObject("date", date);
		modelAndView.addObject("sourceStationId", sourceStationId);
		modelAndView.addObject("destinationStationId", destinationStationId);
		modelAndView.addObject("trainId", trainId);
		modelAndView.addObject("seatType", seatType);
		modelAndView.addObject("numSeats", numSeats);
		
		return modelAndView;
	}

}
