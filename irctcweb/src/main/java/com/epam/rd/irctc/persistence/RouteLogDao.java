package com.epam.rd.irctc.persistence;

import java.util.List;

import com.epam.rd.irctc.model.RouteLog;

public interface RouteLogDao {
	
	List<String> getTrainIdsList(String sourceStationId, String destinationStationId); //done
	
	String getSourceStationId(String trainId);	
	String getDestinationStationId(String trainId);
	
	List<String> getActiveStationIdsList(String trainId, String sourceStationId, String destinationStationId);
	
	RouteLog getRouteLog(String trainId);	
	RouteLog getRouteLog(String trainId, String sourceStationId, String destinationStationId);	
	RouteLog getRouteLog(String trainId, int sourceStationNumber, int destinationStationNumber);
	
	void ensureStationsEnRoute(String trainId, List<String> stationIdsList);
	
	int getDistance(String trainId, String sourceStationId, String destinationStationId);
}
