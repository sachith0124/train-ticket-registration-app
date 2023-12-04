package com.epam.rd.irctc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.Train;

public class TrainDaoImpl implements TrainDao {
	
	private Logger logger;
	
	{
		logger = Logger.getLogger(TrainDaoImpl.class);
	}

	@Override
	public Train getTrain(String trainId) {
		
		Train train = null;
		
		try(
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Train WHERE train_id = " + trainId);
				ResultSet resultSet = preparedStatement.executeQuery();
				) {

			train = new Train(
					resultSet.getString("train_id"),
					resultSet.getString("train_name")
					);
			
			logger.info("Train feteched, with train id: " + trainId);
						
		} catch(SQLException e) {			
			logger.error(e.getMessage());
		}
		
		return train;
	}

	@Override
	public List<Train> getTrains(List<String> trainIdsList) {
		
		List<Train> trainsList = new ArrayList<>();
		
		String trainIds = listToSQLTupleNotation(trainIdsList);
		trainIdsList.clear();
		
		try(	
				Connection connection = Database.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Train WHERE train_id IN " + trainIds);
				ResultSet resultSet = preparedStatement.executeQuery();				
				) {
			
			while(resultSet.next()) {
				trainsList.add(
						new Train(
							resultSet.getString("train_id"),
							resultSet.getString("train_name")
						));
			}
			
			logger.info("Selected Trains fetched");
			
		} catch(SQLException sqlException) {
			logger.error(sqlException.getMessage());
		}
		
		return trainsList;
	
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
