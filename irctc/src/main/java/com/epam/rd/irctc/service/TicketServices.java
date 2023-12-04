package com.epam.rd.irctc.service;

import java.util.List;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;

public interface TicketServices {
	
	Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType, List<Passenger> passengersList);
	
	Ticket getTicket(String pnr);
	
	List<Passenger> getTicketPassengers(String pnr);
	
	List<Passenger> getTrainPassengers(String trainId);
}
