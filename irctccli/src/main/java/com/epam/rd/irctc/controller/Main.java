package com.epam.rd.irctc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.view.HomeUserInterface;
import com.epam.rd.irctc.view.HomeUserInterfaceImpl;

public class Main {
	
	private static InquiryDeskController inquiryDeskController;
	private static TicketCounterController ticketCounterController;
	
	private static String welcomeMessage;
	private static String menuTitle;
	private static List<String> menu;
	
	private static HomeUserInterface homeUserInterface;
	
	private static Logger logger;
	
	private static void initiateController() {
		
		welcomeMessage = "WELCOME TO IRCTC";
		menuTitle = "MENU OF TYPES OF SERVICES AVAILABLE";
		menu = new ArrayList<>();
		populateServicesMenu();
		logger = Logger.getLogger(InquiryDeskController.class);
		homeUserInterface = new HomeUserInterfaceImpl();
		
		homeUserInterface.logMessage("\n");
		homeUserInterface.logMessage(welcomeMessage);
	}
	
	private static void populateServicesMenu() {
		
		menu.add("1. Inquiry Desk: Inquiry Services");
		menu.add("2. Ticket Counter: Ticketing Services");
		menu.add("0: EXIT OUT OF IRCTC");
	}
	
	public static void main(String[] args) {
		
		int option = -1;
		
		initiateController();
		
		while(option != 0) {
			
			homeUserInterface.logMessage("\n");
			homeUserInterface.logMessage("---------- IRCTC -------------");
			
			homeUserInterface.logMenu(menuTitle, menu);
			homeUserInterface.logMessage("\n");
			
			option = homeUserInterface.getUserOption();
			
			if(option == 1) {
				
				inquiryDeskController = new InquiryDeskController();
				inquiryDeskController.startServices();
			}
			else if(option == 2) {
				
				ticketCounterController = new TicketCounterController();
				ticketCounterController.startServices();
			}
			else {
				if(option == 0) {
					homeUserInterface.logMessage("THANK YOU FOR USING IRCTC");
				}
				else {
					homeUserInterface.logError("INVALID TYPE OF SERVICE SELECTED");
				}
			}
		}				
	}
	
}
