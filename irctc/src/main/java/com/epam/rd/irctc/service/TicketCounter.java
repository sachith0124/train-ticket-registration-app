package com.epam.rd.irctc.service;

import java.util.List;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;
import com.epam.rd.irctc.persistence.TicketServicesDao;
import com.epam.rd.irctc.persistence.TicketServicesDaoImpl;

public class TicketCounter implements TicketServices {

	@Override
	public Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType,
			List<Passenger> passengersList) {
		
		TicketServicesDao ticketServicesDao = new TicketServicesDaoImpl();
		return ticketServicesDao.bookTicket(date, trainId, sourceStationId, destinationStationId, seatType, passengersList);
	}

	@Override
	public Ticket getTicket(String pnr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passenger> getTicketPassengers(String pnr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passenger> getTrainPassengers(String trainId) {
		// TODO Auto-generated method stub
		return null;
	}

}
