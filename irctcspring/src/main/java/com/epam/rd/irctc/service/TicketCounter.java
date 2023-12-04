package com.epam.rd.irctc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;
import com.epam.rd.irctc.persistence.TicketDao;
import com.epam.rd.irctc.persistence.TicketDaoImpl;

@Component
@Scope(value="prototype")
public class TicketCounter implements TicketServices {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");

	@Override
	public Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType,
			List<Passenger> passengersList) {
		
		TicketDao ticketDao = context.getBean(TicketDaoImpl.class);
		return ticketDao.bookTicket(date, trainId, sourceStationId, destinationStationId, seatType, passengersList);
	}

	@Override
	public Ticket getTicket(int pnr) {
		
		TicketDao ticketDao = context.getBean(TicketDaoImpl.class);
		return ticketDao.getTicket(pnr);
	}

	@Override
	public List<Passenger> getTicketPassengers(int pnr) {
		
		TicketDao ticketDao = context.getBean(TicketDaoImpl.class);
		return ticketDao.getTicket(pnr).getPassengers();
	}

	@Override
	public List<Passenger> getTrainPassengers(String trainId) {
		
		List<Passenger> passengersList = new ArrayList<>();
		// To Be Implemented
		
		return passengersList;
	}

}
