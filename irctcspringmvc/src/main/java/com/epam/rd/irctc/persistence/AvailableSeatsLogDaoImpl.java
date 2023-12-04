package com.epam.rd.irctc.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.rd.irctc.exceptions.UnavailableServiceException;
import com.epam.rd.irctc.model.AvailableSeatsLog;

@Component
@Scope(value="prototype")
public class AvailableSeatsLogDaoImpl implements AvailableSeatsLogDao {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");
	
	private Logger logger;

	@PersistenceContext
	private EntityManager entityManager;
	
	public AvailableSeatsLogDaoImpl() {
		logger = Logger.getLogger(AvailableSeatsLogDaoImpl.class);
		entityManager = Persistence.createEntityManagerFactory("irctc_persistence").createEntityManager();
	}

	@Override
	public void ensureSeatAvailability(String dayId, String trainId, String sourceStationId,
			String destinationStationId, String seatType, int numPassengers) {
		
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		
		List<String> chargableStationIds = routeLogDao.getChargableStationIdsList(trainId, sourceStationId, destinationStationId);

		
		for(String stationId: chargableStationIds) {
			if(getAvailableSeats(dayId, trainId, stationId, seatType) < numPassengers) {
				throw new UnavailableServiceException("SEATS UNAVAILABLE");
			}
		}
		
		logger.info("SEATS AVAILABILITY ENSURED");
	}
	
	@Override
	public void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType) {
		
		ensureSeatAvailability(dayId, trainId, sourceStationId, destinationStationId, seatType, 1);
	}

	@Override
	public List<String> filterHouseFullTrains(String dayId, List<String> trainIdsList, String sourceStationId,
			String destinationStationId, String seatType, int numPassengers) {
		
		List<String> filteredTrainIdsList = new ArrayList<>();
		
		for(String trainId: trainIdsList) {
			try {
				ensureSeatAvailability(dayId, trainId, sourceStationId, destinationStationId, seatType, numPassengers);
			} catch(UnavailableServiceException exception) {
				continue;
			}
			filteredTrainIdsList.add(trainId);
		}
		
		return filteredTrainIdsList;
	}
	
	@Override
	public List<String> filterHouseFullTrains(String dayId, List<String> trainIdsList, String sourceStationId,
			String destinationStationId, String seatType) {
		
		return filterHouseFullTrains(dayId, trainIdsList, sourceStationId, destinationStationId, seatType, 1);
	}
	
	@Override
	public int getAvailableSeats(String dayId, String trainId, String stationId, String seatType) {
		
		return entityManager
				.createNamedQuery("getRecord", AvailableSeatsLog.class)
				.setParameter("train_id", trainId)
				.setParameter("station_id", stationId)
				.setParameter("day_id", dayId)
				.setParameter("seat_type", seatType)
				.getSingleResult()
				.getNumSeats();
	}
}
