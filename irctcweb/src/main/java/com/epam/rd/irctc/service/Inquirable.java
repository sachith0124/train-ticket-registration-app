package com.epam.rd.irctc.service;

import java.util.List;

import com.epam.rd.irctc.model.RouteLog;
import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Train;

public interface Inquirable {
	
	List<String> getServicableDates(); //Done
	
	List<Station> getAllStations(); //Done
	
	List<Train> getAvailableTrains(String date, String sourceStationId, String sourceDestinationId, String seatType); //Done
	
	String getSourceStationId(String trainId);	
	String getDestinationStationId(String trainId);
	
	RouteLog getRouteLog(String trainId);	
	RouteLog getRouteLog(String trainId, String sourceStationId, String destinationStationId);
	
	int getDistance(String trainId, String sourceStationId, String sourceDestinationId);
	
	int getSeatFare(String trainId);
	int getSeatFare(String trainId, String seatType);
	
	int getSeatFares(List<String> trainIdsList);
	int getSeatFares(List<String> trainIdsList, String seatType);

	int getRouteFare(String trainId, String sourceStationId, String destinationStationId);
	int getRouteFare(String trainId, String sourceStationId, String destinationStationId, int numPassengers);
}
