package com.epam.rd.irctc.service.api;

import java.util.List;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;

public interface TicketServices {
	
	Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType, List<Passenger> passengersList);
	
	Ticket getTicket(int pnr);
	
	List<Passenger> getTicketPassengers(int pnr);
}
