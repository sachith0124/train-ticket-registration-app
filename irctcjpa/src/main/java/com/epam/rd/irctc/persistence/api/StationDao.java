package com.epam.rd.irctc.persistence.api;

import java.util.List;

import com.epam.rd.irctc.model.Station;

public interface StationDao {

	void ensureStation(String stationId);

	void ensureStations(List<String> stationIdsList);

	Station getStation(String stationId);

	List<Station> getStations(List<String> stationIdsList);

	List<Station> getAllStations();
}
