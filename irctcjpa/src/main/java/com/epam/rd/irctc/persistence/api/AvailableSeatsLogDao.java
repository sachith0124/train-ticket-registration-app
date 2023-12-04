package com.epam.rd.irctc.persistence.api;

import java.util.List;

import com.epam.rd.irctc.model.AvailableSeatsLog;

public interface AvailableSeatsLogDao {
	
	void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType, int numPassengers);
	
	void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType);
	
	List<AvailableSeatsLog> getMinAvailableSeatsLogs(String dayId, List<String> trainIdsList, String sourceStationId, String destinationStationId);
	
	int getMinAvailableSeats(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType);
	
	List<String> filterHouseFullTrains(String dayId, List<String>trainIdsList, String sourceStationId, String destinationStationId,String seatType, int numPassengers);
	
	List<String> filterHouseFullTrains(String dayId, List<String>trainIdsList, String sourceStationId, String destinationStationId,String seatType);
	
	int getAvailableSeats(String dayId, String trainId, String stationId, String seatType);
}
