package com.epam.rd.irctc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;
import com.epam.rd.irctc.service.TicketCounter;
import com.epam.rd.irctc.service.TicketServices;
import com.epam.rd.irctc.view.TicketCounterUserInterface;
import com.epam.rd.irctc.view.TicketCounterUserInterfaceImpl;

public class TicketCounterController {
	
	private String welcomeMessage;
	private String servicesMenuTitle;
	private List<String> servicesMenu;
	
	private TicketServices ticketCounter;
	private TicketCounterUserInterface ticketCounterUI;
	
	private Logger logger;
	
	public TicketCounterController() {
		
		welcomeMessage = "WELCOME TO TICKET COUNTER";
		servicesMenuTitle = "TICKET COUNTER SERVICE MENU";
		servicesMenu = new ArrayList<>();
		populateServicesMenu();
		logger = Logger.getLogger(TicketCounterController.class);
		ticketCounterUI = new TicketCounterUserInterfaceImpl();
		
		ticketCounterUI.logMessage("\n");
		ticketCounterUI.logMessage(welcomeMessage);
	}
	
	private void populateServicesMenu() {
		
		servicesMenu.add("1. Book Ticket");
		servicesMenu.add("2. Show Ticket");
		servicesMenu.add("0: EXIT OUT OF TICKET COUNTER");
	}
	
	public void startServices() {
		
		ticketCounter = new TicketCounter();
		
		logger.info("Starting services at Ticket Counter.... ");
		
		int option = -1;
		
		while(option != 0) {
			
			ticketCounterUI.logMessage("\n");
			ticketCounterUI.logMessage("---------- IRCTC / INQUIRY DESK -------------");
			
			ticketCounterUI.logServicesMenu(servicesMenuTitle, servicesMenu);
			ticketCounterUI.logMessage("\n");
			
			option = ticketCounterUI.getUserOption();
			
			if(option == 1) {	// Book Ticket
				
				ticketCounterUI.logMessage("\n Ticket Booking Service ");
				
				Map<String, Object> paramsMap = ticketCounterUI.getBookTicketsParams();
				
				Ticket ticket = ticketCounter.bookTicket(
						(String)paramsMap.get("date"), 
						(String)paramsMap.get("trainId"), 
						(String)paramsMap.get("sourceStationId"), 
						(String)paramsMap.get("destinationStationId"), 
						(String)paramsMap.get("seatType"), 
						(List<Passenger>)paramsMap.get("passengersList"));
				
				ticketCounterUI.logTicket(ticket);
			}
			else if(option == 2) {
				
				ticketCounterUI.logMessage("Service Unavailable");
			}
			else {
				if(option == 0) {
					ticketCounterUI.logMessage("THANK YOU FOR USING TICKET COUNTER");
				}
				else {
					ticketCounterUI.logError("INVALID SERVICE OPTION");
				}
			}
		}
	}
}
