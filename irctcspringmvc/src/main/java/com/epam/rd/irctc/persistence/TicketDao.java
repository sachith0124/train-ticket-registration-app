package com.epam.rd.irctc.persistence;

import java.util.List;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;

public interface TicketDao {
	
	void ensureTicketWithPnr(int pnr);
	
	Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType, List<Passenger> passengersList);

	Ticket getTicket(int pnr);
}
