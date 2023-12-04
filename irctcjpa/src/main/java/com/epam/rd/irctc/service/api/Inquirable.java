package com.epam.rd.irctc.service.api;

import java.util.List;
import java.util.Map;

import com.epam.rd.irctc.model.AvailableSeatsLog;
import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Train;

public interface Inquirable {
	
	List<String> getServicableDates();
	
	List<Station> getAllStations();
	
	List<Train> getAvailableTrains(String date, String sourceStationId, String sourceDestinationId, String seatType);
	
	void ensureSeatAvailability(String date, String trainId, String sourceStationId, String destinationStationId, String seatType, int numPassengers);

	List<AvailableSeatsLog> getMinAvailableSeatsLogs(String date, List<Train> trainsList, String sourceStationId, String destinationStationId);
	
	int getMinAvailableSeats(String date, String trainId, String sourceStationId, String destinationStationId, String seatType);
	
	Map<Train, Integer> getDistanceBetweenStationsEnRoute(List<Train> trainsList, String sourceStationId, String destinationStationId);
}
