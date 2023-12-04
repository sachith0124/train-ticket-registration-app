package com.epam.rd.irctc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String date = request.getParameter("date");
		String sourceStationId = request.getParameter("sourceStationId");
		String destinationStationId = request.getParameter("destinationStationId");
		String seatType = request.getParameter("seatType");
		
		List<Train> trainsList = inquiryDesk.getAvailableTrains(date, sourceStationId, destinationStationId, seatType);
		
		request.setAttribute("trainDTO", getTrainDTO(trainsList));
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		
		try {
			requestDispatcher.forward(request, response);
		} catch(ServletException | IOException exception) {
			logger.error(exception.getMessage());
		} finally {
			trainsList.clear();
		}		
	}
	
	private List<Map<String, String>> getTrainDTO(List<Train> trainsList) {
		
		Map<String, String> trainMap;
		
		List<Map<String, String>> trainDTO = new ArrayList<>();
		
		for(Train train: trainsList) {
			
			trainMap = new HashMap<>();
			
			trainMap.put("trainId", train.getId());
			trainMap.put("trainName", train.getName());
			
			trainDTO.add(trainMap);
		}
		
		return trainDTO;
	}

}
