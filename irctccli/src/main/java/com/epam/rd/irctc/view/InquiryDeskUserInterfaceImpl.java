package com.epam.rd.irctc.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Train;

public class InquiryDeskUserInterfaceImpl implements InquiryDeskUserInterface {
	
	private Logger logger;
	
	public InquiryDeskUserInterfaceImpl() {
		logger = Logger.getLogger(InquiryDeskUserInterfaceImpl.class);
	}

	@Override
	public void logMessage(String message) {		
		logger.info(message);
	}

	@Override
	public void logError(String errorMessage) {
		logger.error(errorMessage);
	}

	@Override
	public void logServicesMenu(String servicesMenuTitle, List<String> servicesMenu) {
		
		logger.info(servicesMenuTitle);
		for(String servicesMenuItem: servicesMenu) {
			logger.info(servicesMenuItem);
		}
	}

	@Override
	public int getUserOption() {
		
		Scanner scanner = new Scanner(System.in);
		logger.info("Enter Option :");
		int option = scanner.nextInt();
		//scanner.close();
		return option;
	}

	@Override
	public void logAvailableDates(List<String> dates) {
		
		for(String date: dates) {
			logger.info(date);
		}
	}

	@Override
	public void logStations(List<Station> stations) {
		
		for(Station station: stations) {
			logger.info(station.getId() + " : " + station.getName());
		}
	}

	@Override
	public Map<String, String> getTrainQueryParams() {
		
		Scanner scanner = new Scanner(System.in);
		logger.info("\n");
		
		Map<String, String> paramsMap = new HashMap<>();
		
		logger.info("Enter date : ");
		paramsMap.put("date", scanner.nextLine());
		
		logger.info("Enter source station id : ");
		paramsMap.put("sourceStationId", scanner.nextLine());
		
		logger.info("Enter destination station id : ");
		paramsMap.put("destinationStationId", scanner.nextLine());
		
		logger.info("Enter seatType (enter 'NA' if undecided) : ");
		paramsMap.put("seatType", scanner.nextLine());
		
		//scanner.close();
		
		return paramsMap;
	}

	@Override
	public void logTrains(List<Train> trains) {
		
		for(Train train: trains) {
			logger.info(train.getId() + ": \t" + train.getName());
		}
	}

}
