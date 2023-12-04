package com.epam.rd.irctc.view;

import java.util.List;
import java.util.Map;

import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Train;

public interface InquiryDeskUserInterface {
	
	void logMessage(String message);
	
	void logError(String errorMessage);
	
	void logServicesMenu(String servicesMenuTitle, List<String> servicesMenu);
	
	int getUserOption();
	
	void logAvailableDates(List<String> dates);
	
	void logStations(List<Station> stations);
	
	Map<String, String> getTrainQueryParams();
	
	void logTrains(List<Train> trains);
}
