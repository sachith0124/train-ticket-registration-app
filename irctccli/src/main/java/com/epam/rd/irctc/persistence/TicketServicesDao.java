package com.epam.rd.irctc.persistence;

import java.util.List;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;

public interface TicketServicesDao {
	
	Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType, List<Passenger> passengersList);
}
