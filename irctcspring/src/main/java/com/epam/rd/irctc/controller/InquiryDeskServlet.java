package com.epam.rd.irctc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.rd.irctc.model.Train;
import com.epam.rd.irctc.service.Inquirable;
import com.epam.rd.irctc.service.InquiryDesk;

public class InquiryDeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1186807521227509413L;
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");

	private Inquirable inquiryDesk;

	private Logger logger;

	public InquiryDeskServlet() {

		super();
		inquiryDesk = context.getBean(InquiryDesk.class);
		logger = Logger.getLogger(InquiryDeskServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String date = request.getParameter("date");
		String sourceStationId = request.getParameter("sourceStationId");
		String destinationStationId = request.getParameter("destinationStationId");
		String seatType = request.getParameter("seatType");
		
		List<Train> trainsList = inquiryDesk.getAvailableTrains(date, sourceStationId, destinationStationId, seatType);
		
		logger.info(trainsList);
		
		request.setAttribute("trainAttributesMapsListDTO", DTOFactory.getTrainAttributesMapsListDTO());
		request.setAttribute("trainMapsListDTO", DTOFactory.createTrainMapsListDTO(trainsList));
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		
		try {
			requestDispatcher.forward(request, response);
		} catch(ServletException | IOException exception) {
			logger.error(exception.getMessage());
		} finally {
			trainsList.clear();
		}		
	}

	

}
