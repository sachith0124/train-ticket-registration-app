package com.epam.rd.irctc.persistence;

import java.util.List;

import com.epam.rd.irctc.model.Train;

public interface TrainDao {
	
	Train getTrain(String trainId);
	
	List<Train> getTrains(List<String> trainIdsList);
}
