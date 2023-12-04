package com.epam.rd.irctc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.Station;

public class StationDaoImpl implements StationDao {
	
	private Logger logger;
	
	{
		logger = Logger.getLogger(StationDaoImpl.class);
	}

	@Override
	public Station getStation(int id) {
		
		Station station = null;
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Station WHERE station_id = " + id);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {

			station = new Station(
					resultSet.getString("station_id"),
					resultSet.getString("station_name")
					);
			
			logger.info("Station feteched, with station id: " + id);
						
		} catch(SQLException e) {			
			logger.error(e.getMessage());
		}
		
		return station;
	}

	@Override
	public List<Station> getAllStations() {

		List<Station> stationsList = new ArrayList<>();
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Station ORDER BY station_name ASC");
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			while(resultSet.next()) {
				stationsList.add(
						new Station(
							resultSet.getString("station_id"),
							resultSet.getString("station_name")
						));
			}
				
			logger.info("All Stations fetched");
			
		} catch(SQLException e) {			
			logger.error(e.getMessage());
		}
		
		return stationsList;
	}

	@Override
	public void saveStation(Station station) {
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO Station VALUES(?,?)"
						);
				) {
			
			preparedStatement.setString(1, station.getId());
			preparedStatement.setString(2, station.getName());
			
			logger.info("Station with station id = " + station.getId() + " inserted \n" + preparedStatement.executeUpdate());
						
		} catch(SQLException e) {			
			logger.error(e.getMessage());
		}
	}

	@Override
	public void updateStationName(String id, String name) {
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Station SET station_name = ? WHERE station_id = " + id);
				) {
			
			preparedStatement.setString(1, name);
			logger.info("Rows Updated in Station : " + preparedStatement.executeUpdate());
			
		} catch(SQLException e) {			
			logger.error(e.getMessage());
		}		
	}

	@Override
	public void deleteStation(int id) {
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Station WHERE station_id = " + id);
				) {
			
			logger.info("Rows Deleted from Station : " + preparedStatement.executeUpdate());
			
		} catch(SQLException e) {			
			logger.error(e.getMessage());
		}		
	}
	
	
}
