package com.epam.rd.irctc.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.exceptions.UnavailableServiceException;
import com.epam.rd.irctc.model.AvailableSeatsLog;
import com.epam.rd.irctc.persistence.api.AvailableSeatsLogDao;
import com.epam.rd.irctc.persistence.api.RouteLogDao;

public class AvailableSeatsLogDaoImpl implements AvailableSeatsLogDao {

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

		if (getMinAvailableSeats(dayId, trainId, sourceStationId, destinationStationId, seatType) < numPassengers) {

			throw new UnavailableServiceException("SEATS UNAVAILABLE");
		}

		logger.info("SEATS AVAILABILITY ENSURED");
	}

	@Override
	public void ensureSeatAvailability(String dayId, String trainId, String sourceStationId,
			String destinationStationId, String seatType) {

		ensureSeatAvailability(dayId, trainId, sourceStationId, destinationStationId, seatType, 1);
	}

	@Override
	public List<AvailableSeatsLog> getMinAvailableSeatsLogs(String dayId, List<String> trainIdsList, String sourceStationId,
			String destinationStationId) {

		RouteLogDao routeLogDao = new RouteLogDaoImpl();
		
		List<AvailableSeatsLog> minAvailableSeatsLogList = new ArrayList<>();
		
		for(String trainId: trainIdsList) {
			
			minAvailableSeatsLogList.addAll(
					entityManager.createNamedQuery("getMinAvailableSeats", AvailableSeatsLog.class)
					.setParameter("train_id", trainId)
					.setParameter("station_ids",
							routeLogDao.getChargableStationIdsList(trainId, sourceStationId, destinationStationId))
					.setParameter("day_id", dayId).getResultList());
		}

		return minAvailableSeatsLogList;

	}

	@Override
	public int getMinAvailableSeats(String dayId, String trainId, String sourceStationId, String destinationStationId,
			String seatType) {

		List<AvailableSeatsLog> minAvailableSeatsLogList = new ArrayList<>();
		
		RouteLogDao routeLogDao = new RouteLogDaoImpl();

		minAvailableSeatsLogList = 
				entityManager.createNamedQuery("getMinAvailableSeats", AvailableSeatsLog.class)
				.setParameter("train_id", trainId)
				.setParameter("station_ids",
						routeLogDao.getChargableStationIdsList(trainId, sourceStationId, destinationStationId))
				.setParameter("day_id", dayId).getResultList();

		return minAvailableSeatsLogList.stream().filter(record -> record.getSeatType().equals(seatType))
				.collect(Collectors.toList()).get(0).getNumSeats();

	}

	@Override
	public List<String> filterHouseFullTrains(String dayId, List<String> trainIdsList, String sourceStationId,
			String destinationStationId, String seatType, int numPassengers) {

		List<String> filteredTrainIdsList = new ArrayList<>();

		for (String trainId : trainIdsList) {
			try {
				ensureSeatAvailability(dayId, trainId, sourceStationId, destinationStationId, seatType, numPassengers);
			} catch (UnavailableServiceException exception) {
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

		return entityManager.createNamedQuery("getRecord", AvailableSeatsLog.class).setParameter("train_id", trainId)
				.setParameter("station_id", stationId).setParameter("day_id", dayId).setParameter("seat_type", seatType)
				.getSingleResult().getNumSeats();
	}
}
