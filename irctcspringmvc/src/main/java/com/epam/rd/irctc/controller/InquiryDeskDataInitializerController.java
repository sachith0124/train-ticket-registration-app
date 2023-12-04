package com.epam.rd.irctc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.service.Inquirable;
import com.epam.rd.irctc.service.InquiryDesk;

@Controller
public class InquiryDeskDataInitializerController {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");

	private Logger logger;
	private Inquirable inquiryDesk;

	public InquiryDeskDataInitializerController() {
		logger = Logger.getLogger(InquiryDeskDataInitializerController.class);
		inquiryDesk = context.getBean(InquiryDesk.class);
	}
	
	
	public ModelAndView loadInquiryData(HttpServletRequest request)
	        throws IOException, ServletException {
	        
		// Get All Serviceable Dates
		List<String> serviceableDatesList = inquiryDesk.getServicableDates();
								
		// Get All Stations
		List<Station> stationsList = inquiryDesk.getAllStations();
								
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("index.jsp");
		modelAndView.addObject("serviceableDatesMapsListDTO", DTOFactory.createDateMapsListDTO(serviceableDatesList));
		modelAndView.addObject("stationMapsListDTO", DTOFactory.createStationMapsListDTO(stationsList));
		
		return modelAndView;
	}
}
