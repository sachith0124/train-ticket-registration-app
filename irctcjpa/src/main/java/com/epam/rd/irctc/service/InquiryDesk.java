package com.epam.rd.irctc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epam.rd.irctc.model.AvailableSeatsLog;
import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Train;
import com.epam.rd.irctc.persistence.AvailableSeatsLogDaoImpl;
import com.epam.rd.irctc.persistence.RouteLogDaoImpl;
import com.epam.rd.irctc.persistence.ServiceableDateDaoImpl;
import com.epam.rd.irctc.persistence.StationDaoImpl;
import com.epam.rd.irctc.persistence.TrainDaoImpl;
import com.epam.rd.irctc.persistence.api.AvailableSeatsLogDao;
import com.epam.rd.irctc.persistence.api.RouteLogDao;
import com.epam.rd.irctc.persistence.api.ServiceableDateDao;
import com.epam.rd.irctc.persistence.api.StationDao;
import com.epam.rd.irctc.persistence.api.TrainDao;
import com.epam.rd.irctc.service.api.Inquirable;

public class InquiryDesk implements Inquirable {

	@Override
	public List<String> getServicableDates() {

		ServiceableDateDao dateMapDao = new ServiceableDateDaoImpl();
		return dateMapDao.getAvailableDates();
	}

	@Override
	public List<Station> getAllStations() {

		StationDao stationDao = new StationDaoImpl();
		return stationDao.getAllStations();
	}

	@Override
	public List<Train> getAvailableTrains(String date, String sourceStationId, String destinationStationId,
			String seatType) {

		validateParameters(date, null, sourceStationId, destinationStationId, seatType);

		RouteLogDao routeLogDao = new RouteLogDaoImpl();
		List<String> trainIdsList = routeLogDao.getTrainIdsList(sourceStationId, destinationStationId);

		ServiceableDateDao serviceableDateDao = new ServiceableDateDaoImpl();
		String dayId = serviceableDateDao.getDayId(date);

		AvailableSeatsLogDao availableSeatsLogDao = new AvailableSeatsLogDaoImpl();
		List<String> filteredTrainIdList = trainIdsList.isEmpty() ? trainIdsList
				: availableSeatsLogDao.filterHouseFullTrains(dayId, trainIdsList, sourceStationId, destinationStationId,
						seatType);

		TrainDao trainDao = new TrainDaoImpl();
		return trainDao.getTrains(filteredTrainIdList);
	}

	@Override
	public void ensureSeatAvailability(String date, String trainId, String sourceStationId, String destinationStationId,
			String seatType, int numPassengers) {

		validateParameters(date, trainId, sourceStationId, destinationStationId, seatType);

		ServiceableDateDao serviceableDateDao = new ServiceableDateDaoImpl();
		String dayId = serviceableDateDao.getDayId(date);

		AvailableSeatsLogDao availableSeatsLogDao = new AvailableSeatsLogDaoImpl();
		availableSeatsLogDao.ensureSeatAvailability(dayId, trainId, sourceStationId, destinationStationId, seatType,
				numPassengers);

	}

	@Override
	public List<AvailableSeatsLog> getMinAvailableSeatsLogs(String date, List<Train> trainsList, String sourceStationId,
			String destinationStationId) {

		validateParameters(date, null, sourceStationId, destinationStationId, null);

		List<String> trainIdsList = trainsList.stream().map(Train::getId).collect(Collectors.toList());

		TrainDao trainDao = new TrainDaoImpl();
		trainDao.ensureTrains(trainIdsList);

		ServiceableDateDao serviceableDateDao = new ServiceableDateDaoImpl();
		String dayId = serviceableDateDao.getDayId(date);

		AvailableSeatsLogDao availableSeatsLogDao = new AvailableSeatsLogDaoImpl();
		return availableSeatsLogDao.getMinAvailableSeatsLogs(dayId, trainIdsList, sourceStationId,
				destinationStationId);
	}

	@Override
	public int getMinAvailableSeats(String date, String trainId, String sourceStationId, String destinationStationId,
			String seatType) {

		validateParameters(date, trainId, sourceStationId, destinationStationId, seatType);

		ServiceableDateDao serviceableDateDao = new ServiceableDateDaoImpl();
		String dayId = serviceableDateDao.getDayId(date);

		AvailableSeatsLogDao availableSeatsLogDao = new AvailableSeatsLogDaoImpl();
		return availableSeatsLogDao.getMinAvailableSeats(dayId, trainId, sourceStationId, destinationStationId,
				seatType);
	}

	@Override
	public Map<Train, Integer> getDistanceBetweenStationsEnRoute(List<Train> trainsList, String sourceStationId,
			String destinationStationId) {

		for (Train train : trainsList) {
			validateParameters(null, train.getId(), sourceStationId, destinationStationId, null);
		}

		Map<Train, Integer> distanceBetweenStationsEnRoute = new HashMap<>();

		RouteLogDao routeLogDao = new RouteLogDaoImpl();

		for (Train train : trainsList) {
			distanceBetweenStationsEnRoute.put(train,
					routeLogDao.getDistance(train.getId(), sourceStationId, destinationStationId));
		}

		return distanceBetweenStationsEnRoute;
	}

	private void validateParameters(String date, String trainId, String sourceStationId, String destinationStationId,
			String seatType) {

		ServiceableDateDao serviceableDateDao = new ServiceableDateDaoImpl();
		TrainDao trainDao = new TrainDaoImpl();
		StationDao stationDao = new StationDaoImpl();
		RouteLogDao routeLogDao = new RouteLogDaoImpl();

		if (date != null) {
			serviceableDateDao.ensureDateAvailability(date);
		}
		if (trainId != null) {
			trainDao.ensureTrain(trainId);
		}
		if (sourceStationId != null && destinationStationId != null) {
			stationDao.ensureStation(sourceStationId);
			stationDao.ensureStation(destinationStationId);
		}
		if (trainId != null && sourceStationId != null && destinationStationId != null) {
			routeLogDao.ensureConsecutiveStationOrder(trainId, sourceStationId, destinationStationId);
		}
		if (seatType != null) {
			trainDao.ensureSeatType(seatType);
		}
	}

}
