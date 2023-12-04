package com.epam.rd.irctc.persistence.api;

import java.util.List;

public interface RouteLogDao {

	void ensureStationsEnRoute(String trainId, List<String> stationIdsList);

	void ensureConsecutiveStationOrder(String trainId, String sourceStationId, String destinationStationId);

	String getSourceStationId(String trainId);

	String getDestinationStationId(String trainId);

	List<String> getTrainIdsList(String sourceStationId, String destinationStationId);

	List<String> getChargableStationIdsList(String trainId, String sourceStationId, String destinationStationId);

	List<String> getEnRouteStationIdsList(String trainId);

	int getDistance(String trainId, String sourceStationId, String destinationStationId);
	
	int getJourneyFare(String trainId, String sourceStationId, String destinationStationId, String seatType, int numPassengers);
}
