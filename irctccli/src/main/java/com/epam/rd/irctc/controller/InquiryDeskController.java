package com.epam.rd.irctc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.service.Inquirable;
import com.epam.rd.irctc.service.InquiryDesk;
import com.epam.rd.irctc.view.InquiryDeskUserInterface;
import com.epam.rd.irctc.view.InquiryDeskUserInterfaceImpl;

public class InquiryDeskController {
	
	private String welcomeMessage;
	private String servicesMenuTitle;
	private List<String> servicesMenu;
	
	private Inquirable inquiryDesk;
	private InquiryDeskUserInterface inquiryDeskUI;
	
	private Logger logger;
	
	public InquiryDeskController() {
		
		welcomeMessage = "WELCOME TO INQUIRY DESK";
		servicesMenuTitle = "INQUIRY DESK SERVICE MENU";
		servicesMenu = new ArrayList<>();
		populateServicesMenu();
		logger = Logger.getLogger(InquiryDeskController.class);
		inquiryDeskUI = new InquiryDeskUserInterfaceImpl();
		
		inquiryDeskUI.logMessage("\n");
		inquiryDeskUI.logMessage(welcomeMessage);
	}
	
	private void populateServicesMenu() {
		
		servicesMenu.add("1. Show serviceable dates");
		servicesMenu.add("2. Show available stations");
		servicesMenu.add("3. Show available trains");
		servicesMenu.add("4. Get source / destination of a train");
		servicesMenu.add("5. Get route information of a train");
		servicesMenu.add("6. Get seat fare of a train");
		servicesMenu.add("7. Get seat fares for list of trains");
		servicesMenu.add("8: Get Route Fare");
		servicesMenu.add("0: EXIT OUT OF INQUIRY DESK");
	}
	
	public void startServices() {
		
		inquiryDesk = new InquiryDesk();
		
		logger.info("Starting services at Inquiry Desk.... ");
		
		int option = -1;
		
		while(option != 0) {
			
			inquiryDeskUI.logMessage("\n");
			inquiryDeskUI.logMessage("---------- IRCTC / INQUIRY DESK -------------");
			
			inquiryDeskUI.logServicesMenu(servicesMenuTitle, servicesMenu);
			inquiryDeskUI.logMessage("\n");
			
			option = inquiryDeskUI.getUserOption();
			
			if(option == 1) {	// Show serviceable dates
				
				inquiryDeskUI.logMessage("\n Available Dates");
				
				inquiryDeskUI.logAvailableDates(inquiryDesk.getServicableDates());
			}
			else if(option == 2) { // Show available stations
				
				inquiryDeskUI.logMessage("\n Available Stations");
				
				inquiryDeskUI.logStations(inquiryDesk.getAllStations());
			}
			else if(option == 3) { // Show available trains
				
				Map<String, String> paramsMap = inquiryDeskUI.getTrainQueryParams();
				
				inquiryDeskUI.logMessage("\n Available Trains");
				
				inquiryDeskUI.logTrains( 
						inquiryDesk.getAvailableTrains(
								paramsMap.get("date").equalsIgnoreCase("NA") ? null : paramsMap.get("date"), 
								paramsMap.get("sourceStationId"), 
								paramsMap.get("destinationStationId"), 
								paramsMap.get("seatType").equalsIgnoreCase("NA") ? null : paramsMap.get("seatType")
								)
						);
			}
			else if(option == 4) { // Get source / destination of a train"
				
				inquiryDeskUI.logMessage("Service Not Available");
			}
			else if(option == 5) { // Get route information of a train
				
				inquiryDeskUI.logMessage("Service Not Available");
			}
			else if(option == 6) { // Get seat fare of a train
				
				inquiryDeskUI.logMessage("Service Not Available");
			}
			else if(option == 7) { // Get seat fares for list of trains
				
				inquiryDeskUI.logMessage("Service Not Available");
			}
			else if(option == 8) { // Get Route Fare
				
				inquiryDeskUI.logMessage("Service Not Available");
			}
			else {
				if(option == 0) {
					inquiryDeskUI.logMessage("THANK YOU FOR USING INQUIRY DESK");
				}
				else {
					inquiryDeskUI.logError("INVALID SERVICE OPTION");
				}
			}
		}
	}
	
	
	
}
