package com.epam.rd.irctc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.exceptions.CustomException;

public class AvailableSeatsDaoImpl implements AvailableSeatsDao {
	
	private Logger logger;
	
	{
		logger = Logger.getLogger(AvailableSeatsDaoImpl.class);
	}

	@Override
	public List<String> filterUnavailableSeats(List<String> trainIdsList, String dayId, String seatType) {
		
		if(dayId == null && seatType == null) {
			return trainIdsList;
		}		
		
		String trainIds = listToSQLTupleNotation(trainIdsList);
		trainIdsList.clear();
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT train_id "
						+ " FROM AvailableSeats "
						+ " WHERE train_id IN " + trainIds
						+ generateQuerySuffix(dayId, seatType)
						);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			while(resultSet.next()) {
				trainIdsList.add(resultSet.getString("train_id"));				
			}
			
			logger.info("Train IDs for trains on selected day NOT having selected seat type are filtered out");	
			
		} catch(SQLException sqlException) {			
			logger.error(sqlException.getMessage());
		}
		
		return trainIdsList;
	}
	
	private String generateQuerySuffix(String dayId, String seatType) {
		
		String querySuffix = "";
		
		if(dayId != null) {
			querySuffix = querySuffix.concat(" AND " + dayId + " > 0");
		}
		
		if(seatType != null) {
			querySuffix = querySuffix.concat(" AND seat_type = '" + seatType +"' ");
		}
		
		querySuffix = querySuffix.concat(";");
		
		return querySuffix;
	}
	
	private String listToSQLTupleNotation(List<String> list) {
		
		StringBuilder tupleNotationSB = new StringBuilder("");
		
		for(String item: list) {
			tupleNotationSB.append("'");
			tupleNotationSB.append(item);
			tupleNotationSB.append("'");
			tupleNotationSB.append(",");
			tupleNotationSB.append(" ");
		}
		
		tupleNotationSB.insert(0, '(');
		tupleNotationSB.replace(tupleNotationSB.length()-2, tupleNotationSB.length(), ")");
		
		return tupleNotationSB.toString();
	}

	@Override
	public int getNumberOfSeats(String dayId, String trainId, String stationId, String seatType) {
		
		int numSeats = -1;
			
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
							"SELECT " + dayId + " FROM AvailableSeats "
									+ " WHERE train_id = '" + trainId + "'"
									+ " AND station_id = '" + stationId + "'"
									+ " AND seatType = '" + seatType + "'"
						);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			numSeats = resultSet.getInt(dayId);
			
		} catch(SQLException sqlException) {
			
			logger.error(sqlException.getMessage());
		}
		
		return numSeats;
	}

	@Override
	public void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId,
			String seatType, int numSeats) {
		
		//check if those stations belong to the train
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
							"SELECT DISTINCT station_id \n" + 
							"FROM AvailableSeats \n" + 
							"WHERE train_id = '" + trainId + "'\n" +
							" AND seat_type = '" + seatType + "'\n" +
							" AND station_id = ANY ( \n" + 
							"	SELECT station_id \n" + 
							"    FROM AvailableSeats \n" + 
							"    WHERE seat_type = '" + seatType + "'\n" +
							"    AND station_id IN ( \n" + 
							"		SELECT station_id \n" + 
							"		FROM RouteLog \n" + 
							"		WHERE train_id = '" + trainId + "' \n" + 
							"        AND station_number	 BETWEEN \n" + 
							"        (SELECT station_number FROM RouteLog WHERE station_id = '" + sourceStationId + "' AND train_id = '" + trainId + "' ) AND \n" + 
							"        (SELECT station_number FROM RouteLog WHERE station_id = '" + destinationStationId +"' AND train_id = '" + trainId + "' ) - 1\n" + 
							"    ) AND "+ dayId + " < " + numSeats + " \n" + 
							");"
						);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			if(resultSet.next()) {
				throw new CustomException("Seats unavailable");
			}
			
		} catch(SQLException sqlException) {
			
			logger.error(sqlException.getMessage());
		}
	}

}
