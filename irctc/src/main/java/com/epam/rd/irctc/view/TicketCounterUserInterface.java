package com.epam.rd.irctc.view;

import java.util.List;
import java.util.Map;

import com.epam.rd.irctc.model.Ticket;

public interface TicketCounterUserInterface {

	void logMessage(String message);
	
	void logError(String errorMessage);
	
	void logServicesMenu(String servicesMenuTitle, List<String> servicesMenu);
	
	int getUserOption();
	
	Map<String, Object> getBookTicketsParams();
	
	void logTicket(Ticket ticket);
}
