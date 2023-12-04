package com.epam.rd.irctc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Train;

public class DTOFactory {
	
	private DTOFactory() {}
	
	public static List<Map <String, String>> createDateMapsListDTO(List<String> datesList) {
		
		List<Map<String, String>> dateMapsListDTO = new ArrayList<>();
		
		Map<String, String> dateMap;
		
		dateMapsListDTO.clear();
		
		for(String date: datesList) {
			
			dateMap = new HashMap<>();
			
			dateMap.put("date", date);
			
			dateMapsListDTO.add(dateMap);
		}
		
		return dateMapsListDTO;
	}
	
	public static List<Map<String, String>> createStationMapsListDTO(List<Station> stationsList) {
		
		List<Map<String, String>> stationMapsListDTO = new ArrayList<>();
		
		Map<String, String> stationMap;
		
		stationMapsListDTO.clear();
		
		for(Station station: stationsList) {
			
			stationMap = new HashMap<>();
			
			stationMap.put("stationId", station.getId());
			stationMap.put("stationName", station.getName());
			
			stationMapsListDTO.add(stationMap);
		}
		
		return stationMapsListDTO;
	}
	
	public static List<Map<String, String>> getTrainAttributesMapsListDTO() {
		
		List<Map<String, String>> trainAttributesMapsListDTO = new ArrayList<>();
		
		Map<String, String> getTrainAttributesMap;
		
		trainAttributesMapsListDTO.clear();
		
		getTrainAttributesMap = new HashMap<>();
		getTrainAttributesMap.put("attributeName", "Train ID");
		trainAttributesMapsListDTO.add(getTrainAttributesMap);
		
		getTrainAttributesMap = new HashMap<>();
		getTrainAttributesMap.put("attributeName", "Train Name");
		trainAttributesMapsListDTO.add(getTrainAttributesMap);
		
		return trainAttributesMapsListDTO;
	}
	
	public static List<Map<String, String>> createTrainMapsListDTO(List<Train> trainsList) {

		List<Map<String, String>> trainMapsListDTO = new ArrayList<>();

		Map<String, String> trainMap;
		
		trainMapsListDTO.clear();

		for (Train train : trainsList) {
			
			trainMap = new HashMap<>();

			trainMap.put("trainId", train.getId());
			trainMap.put("trainName", train.getName());

			trainMapsListDTO.add(trainMap);
		}

		return trainMapsListDTO;
	}
}
