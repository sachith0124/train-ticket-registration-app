package com.epam.rd.irctc.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Train;
import com.epam.rd.irctc.persistence.AvailableSeatsLogDao;
import com.epam.rd.irctc.persistence.AvailableSeatsLogDaoImpl;
import com.epam.rd.irctc.persistence.ServiceableDateDao;
import com.epam.rd.irctc.persistence.ServiceableDateDaoImpl;
import com.epam.rd.irctc.persistence.RouteLogDao;
import com.epam.rd.irctc.persistence.RouteLogDaoImpl;
import com.epam.rd.irctc.persistence.StationDao;
import com.epam.rd.irctc.persistence.StationDaoImpl;
import com.epam.rd.irctc.persistence.TrainDao;
import com.epam.rd.irctc.persistence.TrainDaoImpl;

@Component
@Scope(value="prototype")
public class InquiryDesk implements Inquirable {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");

	public List<String> getServicableDates() {
		
		ServiceableDateDao dateMapDao = context.getBean(ServiceableDateDaoImpl.class);		
		return dateMapDao.getAvailableDates();
	}

	public List<Station> getAllStations() {
		
		StationDao stationDao = context.getBean(StationDaoImpl.class);	
		return stationDao.getAllStations();
	}

	public List<Train> getAvailableTrains(String date, String sourceStationId, String destinationStationId,
			String seatType) {
		
		ServiceableDateDao dateMapDao = context.getBean(ServiceableDateDaoImpl.class);
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		AvailableSeatsLogDao availableSeatsDao = context.getBean(AvailableSeatsLogDaoImpl.class);
		TrainDao trainDao = context.getBean(TrainDaoImpl.class);
		List<String> trainIdsList = routeLogDao.getTrainIdsList(sourceStationId, destinationStationId);

		String dayId = date == null ? null : dateMapDao.getDayId(date);
		
		List<String> filteredTrainIdList = availableSeatsDao.filterHouseFullTrains(dayId, trainIdsList, sourceStationId, destinationStationId, seatType);
		
		return trainDao.getTrains(filteredTrainIdList);
	}

	@Override
	public String getSourceStationId(String trainId) {
		
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		return routeLogDao.getSourceStationId(trainId);
	}

	@Override
	public String getDestinationStationId(String trainId) {
		
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		return routeLogDao.getDestinationStationId(trainId);
	}

	@Override
	public List<Station> getRouteLog(String trainId) {
		
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		StationDao stationDao = context.getBean(StationDaoImpl.class);
		
		return stationDao.getStations(routeLogDao.getEnRouteStationIdsList(trainId));
	}

	@Override
	public List<Station> getRouteLog(String trainId, String sourceStationId, String destinationStationId) {
		
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		StationDao stationDao = context.getBean(StationDaoImpl.class);
		
		List<String> stationIdsList = routeLogDao.getChargableStationIdsList(trainId, sourceStationId, destinationStationId);
		stationIdsList.add(destinationStationId);
		
		return stationDao.getStations(stationIdsList);
	}

	@Override
	public int getDistance(String trainId, String sourceStationId, String destinationStationId) {
		
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		
		return routeLogDao.getDistance(trainId, sourceStationId, destinationStationId);
	}

	@Override
	public int getSeatFare(String trainId, String seatType) {
		
		TrainDao trainDao = context.getBean(TrainDaoImpl.class);
		
		return trainDao.getSeatFare(trainId, seatType);
	}

	@Override
	public int getRouteFare(String trainId, String sourceStationId, String destinationStationId, String seatType, int numPassengers) {
		
		RouteLogDao routeLogDao = context.getBean(RouteLogDaoImpl.class);
		
		return routeLogDao.getJourneyFare(trainId, sourceStationId, destinationStationId, seatType, numPassengers);
	}

}
