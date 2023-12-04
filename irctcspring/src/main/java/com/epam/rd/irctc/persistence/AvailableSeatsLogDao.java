package com.epam.rd.irctc.persistence;

import java.util.List;

public interface AvailableSeatsLogDao {
	
	void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType, int numPassengers);
	
	void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType);
	
	List<String> filterHouseFullTrains(String dayId, List<String>trainIdsList, String sourceStationId, String destinationStationId,String seatType, int numPassengers);
	
	List<String> filterHouseFullTrains(String dayId, List<String>trainIdsList, String sourceStationId, String destinationStationId,String seatType);
	
	int getAvailableSeats(String dayId, String trainId, String stationId, String seatType);
}
