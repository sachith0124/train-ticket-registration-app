package com.epam.rd.irctc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.RouteLog;
import com.epam.rd.irctc.model.Train;

public class RouteLogDaoImpl implements RouteLogDao {
	
	private Logger logger;
	
	{
		logger = Logger.getLogger(RouteLogDaoImpl.class);
	}

	@Override
	public List<String> getTrainIdsList(String sourceStationId, String destinationStationId) {
		
		List<String> trainIdsList = new ArrayList<>();
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT DISTINCT train_id "
						+ "FROM RouteLog "
						+ "WHERE RouteLog.train_id IN ( "
						+ "  SELECT train_id "
						+ "  FROM RouteLog AS source "
						+ "  JOIN RouteLog AS destination "
						+ "  USING (train_id) "
						+ "  WHERE source.station_number <= destination.station_number "
						+ "  AND source.station_id = '" + sourceStationId + "' "
						+ "  AND destination.station_id = '" + destinationStationId + "' "
						+ " ) "
						+ " ORDER BY RouteLog.train_id;"
						);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			while(resultSet.next()) {
				trainIdsList.add(resultSet.getString("train_id"));				
			}
			
			logger.info("Train IDs of trains having stations: " + sourceStationId + " and " + destinationStationId + " are retrieved");	
			
		} catch(SQLException sqlException) {			
			logger.error(sqlException.getMessage());
		}
		
		return trainIdsList;
	}

	@Override
	public String getSourceStationId(String trainId) {
		
		String sourceStationId = null;
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						""
						);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			sourceStationId = resultSet.getString("station_id");
			
			logger.info("Station id for source station fetched for train with train id = " + trainId);
			
		} catch(SQLException sqlException) {
			logger.error(sqlException);
		}
		
		return sourceStationId;
	}

	@Override
	public String getDestinationStationId(String trainId) {
		
		String destinationStationId = null;
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						""
						);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			destinationStationId = resultSet.getString("station_id");
			
			logger.info("Station id for source station fetched for train with train id = " + trainId);
			
		} catch(SQLException sqlException) {
			logger.error(sqlException);
		}
		
		return destinationStationId;
	}

	@Override
	public List<String> getActiveStationIdsList(String trainId, String sourceStationId, String destinationStationId) {
		
		List<String> activeStationIdsList = new ArrayList<>();
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT station_id \r\n" + 
						" FROM RouteLog \r\n" + 
						" WHERE train_id = '" + trainId + "' \r\n" + 
						" AND station_number	 BETWEEN \r\n" + 
						" (SELECT station_number FROM RouteLog WHERE station_id = '" + sourceStationId + "' AND train_id = '" + trainId + "' ) AND \r\n" + 
						" (SELECT station_number FROM RouteLog WHERE station_id = '" + destinationStationId + "' AND train_id = '" + trainId + "' ) - 1"
						);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			while(resultSet.next()) {
				activeStationIdsList.add(resultSet.getString("station_id"));
			}
			
			logger.info("Active stations fetched for train with train id = " + trainId);
			
		} catch(SQLException sqlException) {
			logger.error(sqlException);
		}
		
		return activeStationIdsList;
	}

	@Override
	public RouteLog getRouteLog(String trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RouteLog getRouteLog(String trainId, String sourceStationId, String destinationStationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RouteLog getRouteLog(String trainId, int sourceStationNumber, int destinationStationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ensureStationsEnRoute(String trainId, List<String> stationIdsList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDistance(String trainId, String sourceStationId, String destinationStationId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
