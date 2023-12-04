package com.epam.rd.irctc.persistence;

import java.util.List;

public interface AvailableSeatsDao {
	
	List<String> filterUnavailableSeats(List<String>trainIdsList, String dayId, String seatType);
	
	void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType, int numSeats);
	
	int getNumberOfSeats(String dayId, String trainId, String stationId, String seatType);
}
