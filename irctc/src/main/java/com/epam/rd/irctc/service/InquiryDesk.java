package com.epam.rd.irctc.service;

import java.util.List;

import com.epam.rd.irctc.model.RouteLog;
import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Train;
import com.epam.rd.irctc.persistence.AvailableSeatsDao;
import com.epam.rd.irctc.persistence.AvailableSeatsDaoImpl;
import com.epam.rd.irctc.persistence.DateMapDao;
import com.epam.rd.irctc.persistence.DateMapDaoImpl;
import com.epam.rd.irctc.persistence.RouteLogDao;
import com.epam.rd.irctc.persistence.RouteLogDaoImpl;
import com.epam.rd.irctc.persistence.StationDao;
import com.epam.rd.irctc.persistence.StationDaoImpl;
import com.epam.rd.irctc.persistence.TrainDao;
import com.epam.rd.irctc.persistence.TrainDaoImpl;

public class InquiryDesk implements Inquirable {

	public List<String> getServicableDates() {
		
		DateMapDao dateMapDao = new DateMapDaoImpl();		
		return dateMapDao.getAvailableDates();
	}

	public List<Station> getAllStations() {
		
		StationDao stationDao = new StationDaoImpl();		
		return stationDao.getAllStations();
	}

	public List<Train> getAvailableTrains(String date, String sourceStationId, String destinationStationId,
			String seatType) {
		
		DateMapDao dateMapDao = new DateMapDaoImpl();
		RouteLogDao routeLogDao = new RouteLogDaoImpl();
		AvailableSeatsDao availableSeatsDao = new AvailableSeatsDaoImpl();
		TrainDao trainDao = new TrainDaoImpl();

		List<String> trainIdsList = routeLogDao.getTrainIdsList(sourceStationId, destinationStationId);

		String dayId = date == null ? null : dateMapDao.getDayId(date);
		
		List<String> filteredTrainIdList = availableSeatsDao.filterUnavailableSeats(trainIdsList, dayId, seatType);
		
		return trainDao.getTrains(filteredTrainIdList);
	}

	@Override
	public String getSourceStationId(String trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDestinationStationId(String trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RouteLog getRouteLog(String trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RouteLog getRouteLog(String trainId, String sourceStationId, String destinationStationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDistance(String trainId, String sourceStationId, String sourceDestinationId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSeatFare(String trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSeatFare(String trainId, String seatType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSeatFares(List<String> trainIdsList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSeatFares(List<String> trainIdsList, String seatType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRouteFare(String trainId, String sourceStationId, String destinationStationId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRouteFare(String trainId, String sourceStationId, String destinationStationId, int numPassengers) {
		// TODO Auto-generated method stub
		return 0;
	}

}
