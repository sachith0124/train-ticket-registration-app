package com.epam.rd.irctc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.exceptions.CustomException;
import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;

public class TicketServicesDaoImpl implements TicketServicesDao {
	
	private Logger logger;
	
	public TicketServicesDaoImpl() {
		logger = Logger.getLogger(TicketServicesDaoImpl.class);
	}

	@Override
	public Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType,
			List<Passenger> passengersList) {
		
		checkTicketBookingPreConditions(date, trainId, sourceStationId, destinationStationId, seatType, passengersList);
		
		Ticket ticket = null;
		int pnr = -1;
		int ticketFare = -1;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		DateMapDao dateMapDao = new DateMapDaoImpl();
		String dayId = dateMapDao.getDayId(date);		
		
		RouteLogDao routeLogDao = new RouteLogDaoImpl();
		List<String> activeStationIdsList = routeLogDao.getActiveStationIdsList(trainId, sourceStationId, destinationStationId);
		String activeStationIds = listToSQLTupleNotation(activeStationIdsList);
		
		StringBuilder passengerTableValues = new StringBuilder();
		
		try {
			
			connection = Database.getConnection();
			connection.setAutoCommit(false);
			
			// Reserve Seats between the source and destination stations for the selected train with selected seat type
			
			preparedStatement = connection.prepareStatement( 
						"UPDATE AvailableSeats \r\n" + 
						"SET " + dayId + " = " + dayId + " - " + passengersList.size() + " \r\n" + 
						"WHERE train_id = '" + trainId + "' \r\n" + 
						"AND station_id IN " + activeStationIds + " \r\n" + 
						"AND seat_type = '" + seatType + "';"
					);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			// Calculate Ticket Fare
			
			preparedStatement = connection.prepareStatement(
					"SELECT seat_fare \r\n" + 
					"FROM Seats \r\n" + 
					"WHERE train_id = '" + trainId + "' \r\n" + 
					"AND seat_type = '" + seatType + "';"
					);
			
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				throw new CustomException("Seat Fare NOT FOUND for seat type of " + seatType + " for train with train ID = " + trainId );
			}
			
			ticketFare = resultSet.getInt("seat_fare") * passengersList.size();
			
			resultSet.close();
			preparedStatement.close();
			
			// Generate PNR
			
			preparedStatement = connection.prepareStatement("SELECT MAX(pnr) AS pnr FROM Ticket;");
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				pnr = resultSet.getInt("pnr") + 1;
			}
			
			resultSet.close();
			preparedStatement.close();
			
			// Generate and save Ticket to DB
			
			preparedStatement = connection.prepareStatement(
						"INSERT INTO Ticket "
						+ "(pnr, train_id, date, source_station_id, destination_station_id, num_passengers, seat_type, ticket_fare)\r\n" + 
						"VALUES (" + 
						pnr + ", '" + trainId + "', '" + date + "', '" + sourceStationId + "', '" + 
						destinationStationId + "', " + passengersList.size() + ",'" + seatType + "', " + ticketFare +
						");"
					);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			// Save Passengers Details in DB
			
			for(Passenger passenger: passengersList) {				
				passengerTableValues.append("(" + pnr + ", '" + passenger.getName() + "', '" + 
						passenger.getDateOfBirth() + "', '" + passenger.getGender() + "' ), ");
			}
			passengerTableValues.delete(passengerTableValues.length() - 2, passengerTableValues.length());
			
			preparedStatement = connection.prepareStatement(
						"INSERT INTO Passenger(pnr, passenger_name, date_of_birth, gender) \n" + 
						"VALUES \n" + 
						passengerTableValues.toString() + 
						";"
					);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			connection.commit();
			connection.close();
			
			// Instantiate Ticket Object
			
			ticket = new Ticket(pnr, date, sourceStationId, destinationStationId, passengersList.size(), seatType, ticketFare);
			
		} catch(SQLException sqlException) {
			
			try {
				if(connection != null) {
					connection.rollback();
				}			
			} catch(SQLException sqlException2) {
				logger.error(sqlException2.getMessage());
			}
			logger.error(sqlException.getMessage());
			
		} finally {
			
			try {
				if(connection != null) {
					connection.setAutoCommit(true);
					connection.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch(SQLException sqlException) {
				logger.error(sqlException.getMessage());
			}
		}
		
		return ticket;
	}
	
	private void checkTicketBookingPreConditions(String date, String trainId, String sourceStationId, String destinationStationId, String seatType,
			List<Passenger> passengersList) {
		
		DateMapDao dateMapDao = new DateMapDaoImpl();
		String dayId = dateMapDao.getDayId(date);
		
		logger.info(date);
		
		if(dayId == null) {
			throw new CustomException("Unable to perform operations for the desired date");
		}
		
		AvailableSeatsDao availableSeatsDao = new AvailableSeatsDaoImpl();
		availableSeatsDao.ensureSeatAvailability(dayId, trainId, sourceStationId, destinationStationId, seatType, passengersList.size());
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

}
