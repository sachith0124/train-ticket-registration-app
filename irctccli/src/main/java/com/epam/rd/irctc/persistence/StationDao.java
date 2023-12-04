package com.epam.rd.irctc.persistence;

import java.util.List;

import com.epam.rd.irctc.model.Station;

public interface StationDao {
	
	Station getStation(int id);
	
	List<Station> getAllStations();
	
	void saveStation(Station station);
	
	void updateStationName(String id, String name);
	
	void deleteStation(int id);
}
