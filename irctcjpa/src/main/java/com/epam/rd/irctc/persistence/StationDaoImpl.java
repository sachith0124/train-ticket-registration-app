package com.epam.rd.irctc.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.exceptions.InvalidKeyException;
import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.persistence.api.StationDao;

public class StationDaoImpl implements StationDao {

	private Logger logger;

	@PersistenceContext
	private EntityManager entityManager;

	public StationDaoImpl() {
		logger = Logger.getLogger(StationDaoImpl.class);
		entityManager = Persistence.createEntityManagerFactory("irctc_persistence").createEntityManager();
	}

	@Override
	public void ensureStation(String stationId) {
		
		if( entityManager.find(Station.class, stationId) == null) {
			throw new InvalidKeyException("INVALID KEY: STATION ID : " + stationId);
		}
		logger.info("Station with station ID : " + stationId + " ensured");
	}

	@Override
	public void ensureStations(List<String> stationIdsList) {
		
		List<String> invalidStationIdsList = new ArrayList<>();
		
		for(String stationId: stationIdsList) {
			if( entityManager.find(Station.class, stationId) == null) {
				invalidStationIdsList.add(stationId);
			}
		}
		
		if(!invalidStationIdsList.isEmpty()) {
			throw new InvalidKeyException("INVALID KEY: STATION IDs : " + invalidStationIdsList.toString());
		}
		
		logger.info("Stations with station IDs : " + stationIdsList.toString() + " ensured");
	}

	@Override
	public Station getStation(String stationId) {
		
		return entityManager.find(Station.class, stationId);
	}

	@Override
	public List<Station> getStations(List<String> stationIdsList) {
		
		return entityManager.createNamedQuery("getStationsByStationIds", Station.class).setParameter("ids", stationIdsList).getResultList();
	}

	@Override
	public List<Station> getAllStations() {
		
		return entityManager.createNamedQuery("getAllStations", Station.class).getResultList();
	}

	

}
