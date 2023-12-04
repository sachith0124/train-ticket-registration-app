package com.epam.rd.irctc.persistence;

import java.util.List;

import com.epam.rd.irctc.model.Train;

public interface TrainDao {

	void ensureTrain(String trainId);

	void ensureTrains(List<String> trainIdsList);

	void ensureSeatType(String seatType);
	void ensureSeatType(String trainId, String seatType);

	List<String> getSeatTypes(String trainId);
	List<String> getAllSeatTypes();

	Train getTrain(String trainId);

	List<Train> getTrains(List<String> trainIdsList);
	
	List<Train> getTrainsByTrainName(String trainName);
	
	List<Train> getAllTrains();
	
	int getSeatFare(String trainId, String seatType);
}
