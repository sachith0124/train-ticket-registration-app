package com.epam.rd.irctc.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.model.Ticket;

public class TicketCounterUserInterfaceImpl implements TicketCounterUserInterface {

	private Logger logger;
	
	public TicketCounterUserInterfaceImpl() {
		logger = Logger.getLogger(TicketCounterUserInterfaceImpl.class);
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
	public Map<String, Object> getBookTicketsParams() {
		
		Map<String, Object> paramsMap = new HashMap<>();
		Scanner scanner = new Scanner(System.in);
		
		logger.info("Enter Date of Journey : ");
		paramsMap.put("date", scanner.nextLine());
		
		logger.info("Enter Train ID : ");
		paramsMap.put("trainId", scanner.nextLine());
		
		logger.info("Enter source station ID : ");
		paramsMap.put("sourceStationId", scanner.nextLine());
		
		logger.info("Enter destination station ID : ");
		paramsMap.put("destinationStationId", scanner.nextLine());
		
		logger.info("Enter seat type : ");
		paramsMap.put("seatType", scanner.nextLine());
		
		logger.info("Enter number of Passengers : ");
		paramsMap.put("passengersList", getPassengersList(scanner.nextInt()));
		
		return paramsMap;
	}
	
	private List<Passenger> getPassengersList(int numPassengers) {
		
		List<Passenger> passengersList = new ArrayList<>();
		
		String name;
		String dateOfBirth;
		String gender;
		
		Scanner scanner = new Scanner(System.in);
		
		logger.info("\t Enter  Passengers Details");
		
		for(int nPassenger = 0; nPassenger < numPassengers; nPassenger++) {
			
			logger.info("\t Passenger" + nPassenger + " Details : ");
			
			logger.info("\t\t Enter Name : ");
			name = scanner.nextLine().trim();
			logger.info("\t\t Enter Date of Birth [YYYY-MM-dd] :");
			dateOfBirth = scanner.nextLine().trim();
			logger.info("\t\t Enter Gender :");
			gender = scanner.nextLine().trim();
			
			passengersList.add(new Passenger(name, dateOfBirth, gender));
		}
		
		return passengersList;
	}

	@Override
	public void logTicket(Ticket ticket) {
		
		logger.info(ticket);
	}
	
	
}
