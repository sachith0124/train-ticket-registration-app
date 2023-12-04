package com.epam.rd.irctc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.service.InquiryDesk;

public class InquiryDeskDataInitializerServlet extends HttpServlet {

	private static final long serialVersionUID = -4104248712675935212L;

	private Logger logger;
	private InquiryDesk inquiryDesk;

	@Override
	public void	init() {
		logger = Logger.getLogger(InquiryDeskDataInitializerServlet.class);
		inquiryDesk = new InquiryDesk();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException {
	        
		// Get All Serviceable Dates
		List<String> serviceableDatesList = inquiryDesk.getServicableDates();
		request.setAttribute("serviceableDatesMapsListDTO", DTOFactory.createDateMapsListDTO(serviceableDatesList));
								
		// Get All Stations
		List<Station> stationsList = inquiryDesk.getAllStations();
		request.setAttribute("stationMapsListDTO", DTOFactory.createStationMapsListDTO(stationsList));
								
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException | IOException exception) {
			logger.error(exception.getMessage());
		} finally {
			serviceableDatesList.clear();
			stationsList.clear();
		}
	}
}
