package com.epam.rd.irctc.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.rd.irctc.exceptions.InconsistentDataException;
import com.epam.rd.irctc.model.RouteLog;

@Component
@Scope(value="prototype")
public class RouteLogDaoImpl implements RouteLogDao {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");
	
	private Logger logger;

	@PersistenceContext
	private EntityManager entityManager;
	
	public RouteLogDaoImpl() {
		logger = Logger.getLogger(RouteLogDaoImpl.class);
		entityManager = Persistence.createEntityManagerFactory("irctc_persistence").createEntityManager();
	}

	@Override
	public void ensureStationsEnRoute(String trainId, List<String> stationIdsList) {
		
		List<String> trainStationsIdsList = getEnRouteStationIdsList(trainId);
	
		BiPredicate<List<String>, List<String>> containsAllInOrder = (List<String> parentList, List<String> childList) -> {
			
			// Refactor to check for order
			return parentList.containsAll(childList);
		};
		
		if(!containsAllInOrder.test(trainStationsIdsList, stationIdsList)) {
			throw new InconsistentDataException("INCONSISTENT DATA: TRAIN ID : " + trainId + ", STATION IDS LIST : " + stationIdsList.toString());
		}
		
		logger.info("For Train with train ID : " + trainId + " Stations with stationIds : " + stationIdsList.toString() + " ensured");
	}

	@Override
	public void ensureConsecutiveStationOrder(String trainId, String sourceStationId, String destinationStationId) {
	
		List<String> enrouteStationIdsList = getEnRouteStationIdsList(trainId);
		
		if(enrouteStationIdsList.contains(sourceStationId) && enrouteStationIdsList.contains(destinationStationId)) {
			if(enrouteStationIdsList.indexOf(sourceStationId) <= enrouteStationIdsList.indexOf(destinationStationId)) {
				logger.info("For Train with train ID : " + trainId + ", Station ID of source : " + sourceStationId + ", Station ID of destination : " + destinationStationId + " ensured");
				return;
			}
		}
		
		throw new InconsistentDataException("INCONSISTENT DATA: TRAIN ID : " + trainId + ", SOURCE STATION ID : " + 
				sourceStationId + ", DESTINATION STATION ID : " + destinationStationId);
		
	}

	@Override
	public String getSourceStationId(String trainId) {
		
		RouteLog routeLog = entityManager.createNamedQuery("getSourceStationId", RouteLog.class).setParameter("train_id", trainId).getSingleResult();
		return routeLog.getStationId();
	}

	@Override
	public String getDestinationStationId(String trainId) {
		
		RouteLog routeLog = entityManager.createNamedQuery("getDestinationStationId", RouteLog.class).setParameter("train_id", trainId).getSingleResult();
		logger.debug("getSourceStationId OUTPUT = " + routeLog.getStationId());
		return routeLog.getStationId();
	}

	@Override
	public List<String> getTrainIdsList(String sourceStationId, String destinationStationId) {
		
		Set<String> trainIdsSet = new HashSet<>();
		List<RouteLog> routeLogsList = entityManager.createNamedQuery("getTrainIdsList", RouteLog.class).setParameter(1, sourceStationId).setParameter(2, destinationStationId).getResultList();
		
		for(RouteLog routeLog: routeLogsList) {
			trainIdsSet.add(routeLog.getTrainId());
		}
		
		return trainIdsSet.stream().collect(Collectors.toList());
	}

	@Override
	public List<String> getChargableStationIdsList(String trainId, String sourceStationId,
			String destinationStationId) {

		List<String> stationIdsList = new ArrayList<>();
		List<RouteLog> routeLogsList = entityManager.createNamedQuery("getChargableStationIdsList", RouteLog.class)
				.setParameter("train_id", trainId)
				.setParameter("source_station_id", sourceStationId)
				.setParameter("destination_station_id", destinationStationId)
				.getResultList();
		
		for(RouteLog routeLog: routeLogsList) {
			stationIdsList.add(routeLog.getStationId());
		}
		
		return stationIdsList;
	}

	@Override
	public List<String> getEnRouteStationIdsList(String trainId) {
		
		List<String> stationIdsList = new ArrayList<>();
		List<RouteLog> routeLogsList = entityManager.createNamedQuery("getEnRouteStationIdsList", RouteLog.class)
				.setParameter("train_id", trainId)
				.getResultList();
		
		for(RouteLog routeLog: routeLogsList) {
			stationIdsList.add(routeLog.getStationId());
		}
		
		return stationIdsList;
	}

	@Override
	public int getDistance(String trainId, String sourceStationId, String destinationStationId) {

		RouteLog sourceRouteLog = entityManager.createNamedQuery("getCumulativeDistance", RouteLog.class)
				.setParameter("train_id", trainId)
				.setParameter("station_id", sourceStationId)
				.getSingleResult();
		
		RouteLog destinationRouteLog = entityManager.createNamedQuery("getCumulativeDistance", RouteLog.class)
				.setParameter("train_id", trainId)
				.setParameter("station_id", destinationStationId)
				.getSingleResult();
		
		return destinationRouteLog.getCumulativeDistance() - sourceRouteLog.getCumulativeDistance();
	}

	@Override
	public int getJourneyFare(String trainId, String sourceStationId, String destinationStationId, String seatType,
			int numPassengers) {
		
		TrainDao trainDao = context.getBean(TrainDaoImpl.class);
		
		return trainDao.getSeatFare(trainId, seatType) * getDistance(trainId, sourceStationId, destinationStationId);
	}

	
	

}
