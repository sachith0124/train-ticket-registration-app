package com.epam.rd.irctc.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.rd.irctc.exceptions.InvalidKeyException;
import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;

@Component
@Scope(value="prototype")
public class TicketDaoImpl implements TicketDao {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");
	
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
		
		ServiceableDateDao serviceableDateDao = context.getBean(ServiceableDateDaoImpl.class);
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		
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
		
		// Instantiate a Ticket
		ticket = new Ticket(pnr, date, trainId, sourceStationId, destinationStationId, passengersList.size(), seatType, journeyFare);

		
		// Begin Transaction
		entityManager.getTransaction().begin();
		
		{
			// Reserve Seats
			entityManager.createNamedQuery("reserveSeats", Ticket.class)
			.setParameter("day_id", dayId)
			.setParameter("train_id", trainId)
			.setParameter("seat_type", seatType)
			.setParameter("station_ids", routeLogDao.getChargableStationIdsList(trainId, sourceStationId, destinationStationId))
			.setParameter("num_passengers", passengersList.size())
			.executeUpdate();
			
					
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
