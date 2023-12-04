package com.epam.rd.irctc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.DateMap;

public class DateMapDaoImpl implements DateMapDao {
	
	private Logger logger;
	
	{
		logger = Logger.getLogger(DateMapDaoImpl.class);
	}

	@Override
	public DateMap getDateMap(int dayId) {
		
		DateMap dateMap = null;
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DateMap WHERE day_id = " + dayId);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {

			dateMap = new DateMap(
					resultSet.getString("day_id"),
					resultSet.getString("date")
					);
			
			logger.info("DateMap feteched, with day id: " + dayId);
						
		} catch(SQLException sqlException) {			
			logger.error(sqlException.getMessage());
		}
		
		return dateMap;
	}

	@Override
	public List<DateMap> getAllDateMaps() {
		
		List<DateMap> dateMapList = new ArrayList<>();
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DateMap");
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			while(resultSet.next()) {
				dateMapList.add(
						new DateMap(
							resultSet.getString("day_id"),
							resultSet.getString("date")
						));				
			}
			
			logger.info("All DateMaps fetched");
			
		} catch(SQLException sqlException) {			
			logger.error(sqlException.getMessage());
		}
		
		return dateMapList;
	}
	
	@Override
	public List<String> getAvailableDates() {
		
		List<String> availableDates = new ArrayList<>();
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT date FROM DateMap");
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			while(resultSet.next()) {
				availableDates.add(resultSet.getString("date"));				
			}
			
			logger.info("All Available dates fetched");	
			
		} catch(SQLException sqlException) {			
			logger.error(sqlException.getMessage());
		}
		
		return availableDates;
	}

	@Override
	public String getDayId(String date) {
		
		String dayId = null;
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT day_id FROM DateMap WHERE date = '" + date.trim() +"';");
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			while(resultSet.next()) {
				dayId = resultSet.getString("day_id");				
			}
			
			logger.info("day id feteched, with for date = " + date);
						
		} catch(SQLException sqlException) {			
			logger.error(sqlException.getMessage());
		}
		
		return dayId;
	}

	@Override
	public void updateDate(String dayId, String newDate) {
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DateMap SET date = ? WHERE day_id = '" + dayId +"');");
				) {
			
			preparedStatement.setString(1, newDate);
			logger.info("Rows Updated in DateMap : " + preparedStatement.executeUpdate());
			
		} catch(SQLException sqlException) {			
			logger.error(sqlException.getMessage());
		}
	}

}
