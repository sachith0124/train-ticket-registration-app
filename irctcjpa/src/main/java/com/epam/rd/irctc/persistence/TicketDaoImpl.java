package com.epam.rd.irctc.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.exceptions.InvalidKeyException;
import com.epam.rd.irctc.model.AvailableSeatsLog;
import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;
import com.epam.rd.irctc.persistence.api.RouteLogDao;
import com.epam.rd.irctc.persistence.api.ServiceableDateDao;
import com.epam.rd.irctc.persistence.api.TicketDao;

public class TicketDaoImpl implements TicketDao {
	
	private Logger logger;

	@PersistenceContext
	private EntityManager entityManager;
	
	public TicketDaoImpl() {
		logger = Logger.getLogger(TicketDaoImpl.class);
		entityManager = Persistence.createEntityManagerFactory("irctc_persistence").createEntityManager();
	}
	
	@Override
	public void ensureTicketWithPnr(int pnr) {
		
		if( entityManager.find(Ticket.class, pnr) == null) {
			throw new InvalidKeyException("INVALID KEY: PNR : " + pnr);
		}
		logger.info("Ticket with PNR : " + pnr + " ensured");
	}

	@Override
	public Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType,
			List<Passenger> passengersList) {
		
		Ticket ticket = null;
		int pnr = -1;
		int journeyFare = -1;
		String dayId = null;
		
		ServiceableDateDao serviceableDateDao = new ServiceableDateDaoImpl();
		RouteLogDao routeLogDao = new RouteLogDaoImpl();
		
		// Calculate journeyFare
		journeyFare = routeLogDao.getJourneyFare(trainId, sourceStationId, destinationStationId, seatType, passengersList.size());
		
		// Get MAX pnr
		try {
			pnr = entityManager.createNamedQuery("selectMaxPNR", Ticket.class).getSingleResult().getPnr() + 1;
		} catch(NoResultException noResultException) {
			pnr = 1;
		}
		
		// Get Day ID for the date
		dayId = serviceableDateDao.getDayId(date);
		
		// Begin Transaction
		entityManager.getTransaction().begin();
		
		{
			// Reserve Seats
			entityManager.createNamedQuery("reserveSeats", AvailableSeatsLog.class)
			.setParameter("num_passengers", passengersList.size())
			.setParameter("day_id", dayId)
			.setParameter("train_id", trainId)
			.setParameter("seat_type", seatType)
			.setParameter("station_ids", routeLogDao.getChargableStationIdsList(trainId, sourceStationId, destinationStationId))
			.executeUpdate();
			
			// Instantiate a Ticket
			ticket = new Ticket(pnr, date, trainId, sourceStationId, destinationStationId, passengersList.size(), seatType, journeyFare);
		
			// Attach passengers to the ticket
			ticket.getPassengers().addAll(passengersList);
			
			// persist Ticket
			entityManager.persist(ticket);
		}
		
		entityManager.getTransaction().commit();
		
		return ticket;
	}
	
	@Override
	public Ticket getTicket(int pnr) {
		
		return entityManager.find(Ticket.class, pnr);
	}
}
