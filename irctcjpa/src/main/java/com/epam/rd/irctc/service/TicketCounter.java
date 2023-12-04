package com.epam.rd.irctc.service;

import java.util.List;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;
import com.epam.rd.irctc.persistence.AvailableSeatsLogDaoImpl;
import com.epam.rd.irctc.persistence.RouteLogDaoImpl;
import com.epam.rd.irctc.persistence.ServiceableDateDaoImpl;
import com.epam.rd.irctc.persistence.StationDaoImpl;
import com.epam.rd.irctc.persistence.TicketDaoImpl;
import com.epam.rd.irctc.persistence.TrainDaoImpl;
import com.epam.rd.irctc.persistence.api.AvailableSeatsLogDao;
import com.epam.rd.irctc.persistence.api.RouteLogDao;
import com.epam.rd.irctc.persistence.api.ServiceableDateDao;
import com.epam.rd.irctc.persistence.api.StationDao;
import com.epam.rd.irctc.persistence.api.TicketDao;
import com.epam.rd.irctc.persistence.api.TrainDao;
import com.epam.rd.irctc.service.api.TicketServices;

public class TicketCounter implements TicketServices {

	@Override
	public Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId,
			String seatType, List<Passenger> passengersList) {

		ServiceableDateDao serviceableDateDao = new ServiceableDateDaoImpl();
		TrainDao trainDao = new TrainDaoImpl();
		StationDao stationDao = new StationDaoImpl();
		RouteLogDao routeLogDao = new RouteLogDaoImpl();

		serviceableDateDao.ensureDateAvailability(date);
		trainDao.ensureTrain(trainId);
		stationDao.ensureStation(sourceStationId);
		stationDao.ensureStation(destinationStationId);
		routeLogDao.ensureConsecutiveStationOrder(trainId, sourceStationId, destinationStationId);
		trainDao.ensureSeatType(seatType);

		AvailableSeatsLogDao availableSeatsLogDao = new AvailableSeatsLogDaoImpl();

		availableSeatsLogDao.ensureSeatAvailability(serviceableDateDao.getDayId(date), trainId, sourceStationId,
				destinationStationId, seatType, passengersList.size());

		TicketDao ticketDao = new TicketDaoImpl();
		
		return ticketDao.bookTicket(date, trainId, sourceStationId, destinationStationId, seatType,
				passengersList);
	}

	@Override
	public Ticket getTicket(int pnr) {

		TicketDao ticketDao = new TicketDaoImpl();
		
		ticketDao.ensureTicketWithPnr(pnr);
		
		return ticketDao.getTicket(pnr);
	}

	@Override
	public List<Passenger> getTicketPassengers(int pnr) {

		TicketDao ticketDao = new TicketDaoImpl();
		
		ticketDao.ensureTicketWithPnr(pnr);
		
		return ticketDao.getTicket(pnr).getPassengers();
	}
}
