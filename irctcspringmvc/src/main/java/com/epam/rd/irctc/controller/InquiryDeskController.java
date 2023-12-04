package com.epam.rd.irctc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.rd.irctc.model.Train;
import com.epam.rd.irctc.service.Inquirable;
import com.epam.rd.irctc.service.InquiryDesk;

@Controller
public class InquiryDeskController {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");

	private Inquirable inquiryDesk;

	private Logger logger;

	public InquiryDeskController() {

		super();
		inquiryDesk = context.getBean(InquiryDesk.class);
		logger = Logger.getLogger(InquiryDeskController.class);
	}

	@RequestMapping("InquiryDesk")
	protected ModelAndView inquire(HttpServletRequest request) {
		
		String date = request.getParameter("date");
		String sourceStationId = request.getParameter("sourceStationId");
		String destinationStationId = request.getParameter("destinationStationId");
		String seatType = request.getParameter("seatType");
		
		List<Train> trainsList = inquiryDesk.getAvailableTrains(date, sourceStationId, destinationStationId, seatType);
		
		logger.info(trainsList);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("index.jsp");
		
		modelAndView.addObject("trainAttributesMapsListDTO", DTOFactory.getTrainAttributesMapsListDTO());
		modelAndView.addObject("trainMapsListDTO", DTOFactory.createTrainMapsListDTO(trainsList));
		
		return modelAndView;	
	}

	

}
