package com.epam.rd.irctc.crudetester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.rd.irctc.model.Passenger;
import com.epam.rd.irctc.persistence.AvailableSeatsLogDao;
import com.epam.rd.irctc.persistence.AvailableSeatsLogDaoImpl;
import com.epam.rd.irctc.persistence.RouteLogDao;
import com.epam.rd.irctc.persistence.RouteLogDaoImpl;
import com.epam.rd.irctc.persistence.ServiceableDateDao;
import com.epam.rd.irctc.persistence.ServiceableDateDaoImpl;
import com.epam.rd.irctc.persistence.StationDao;
import com.epam.rd.irctc.persistence.StationDaoImpl;
import com.epam.rd.irctc.persistence.TicketDao;
import com.epam.rd.irctc.persistence.TicketDaoImpl;
import com.epam.rd.irctc.persistence.TrainDao;
import com.epam.rd.irctc.persistence.TrainDaoImpl;

public class DaoTester {
	
	static Scanner scanner;
	static Logger logger;
	
	static {
		logger = Logger.getLogger(DaoTester.class);
	}
	
	private DaoTester() {}
	
	public static void testStationDao() {
		
		StationDao stationDao = new StationDaoImpl();
		
		String stationId = "S3";
		List<String> stationIdsList = new ArrayList<>();
		stationIdsList.add("S3");
		stationIdsList.add("S5");
		
		//void ensureStation(String stationId);
		logger.info("\n----------------------------------ENSURE STATION----------------------------------");
		stationDao.ensureStation(stationId);
		logger.info("--------------------------------------------------------------------\n");

		//void ensureStations(List<String> stationIdsList);
		logger.info("\n----------------------------------ENSURE STATIONS----------------------------------");
		stationDao.ensureStations(stationIdsList);
		logger.info("--------------------------------------------------------------------\n");

		//Station getStation(String stationId);
		logger.info("\n----------------------------------GET STATION----------------------------------");
		logger.info(stationDao.getStation(stationId));
		logger.info("--------------------------------------------------------------------\n");

		//List<Station> getStations(List<String> stationIdsList);
		logger.info("\n----------------------------------GET STATIONS----------------------------------");
		logger.info(stationDao.getStations(stationIdsList));
		logger.info("--------------------------------------------------------------------\n");

		//List<Station> getAllStations();
		logger.info("\n----------------------------------GET ALL STATIONS----------------------------------");
		logger.info(stationDao.getAllStations());
		logger.info("--------------------------------------------------------------------\n");
	}
	
	public static void testTrainDao() {
	
		TrainDao trainDao = new TrainDaoImpl();	
		
		String trainId = "T1";
		List<String> trainIdsList = new ArrayList<>();
		trainIdsList.add("T2");
		trainIdsList.add("T3");
		String seatType = "nonAC";
		String trainName = "Bullet";
		
		//void ensureTrain(String trainId);
		logger.info("\n----------------------------------ENSURE TRAIN----------------------------------");
		trainDao.ensureTrain(trainId);
		logger.info("--------------------------------------------------------------------\n");

		//void ensureTrains(List<String> trainIdsList);
		logger.info("\n----------------------------------ENSURE TRAINS----------------------------------");
		trainDao.ensureTrains(trainIdsList);
		logger.info("--------------------------------------------------------------------\n");		

		//void ensureSeatType(String seatType);
		logger.info("\n----------------------------------ENSURE SEAT TYPE----------------------------------");
		trainDao.ensureSeatType(seatType);
		logger.info("--------------------------------------------------------------------\n");
		//void ensureSeatType(String trainId, String seatType);
		logger.info("\n----------------------------------ENSURE SEAT TYPE----------------------------------");
		trainDao.ensureSeatType(trainId, seatType);
		logger.info("--------------------------------------------------------------------\n");

		//List<String> getSeatTypes(String trainId);
		logger.info("\n----------------------------------GET SEAT TYPES----------------------------------");
		logger.info(trainDao.getSeatTypes(trainId));
		logger.info("--------------------------------------------------------------------\n");
		//List<String> getAllSeatTypes();
		logger.info("\n----------------------------------GET ALL SEAT TYPES----------------------------------");
		logger.info(trainDao.getAllSeatTypes());
		logger.info("--------------------------------------------------------------------\n");

		//Train getTrain(String trainId);
		logger.info("\n----------------------------------GET TRAIN----------------------------------");
		logger.info(trainDao.getTrain(trainId));
		logger.info("--------------------------------------------------------------------\n");

		//List<Train> getTrains(List<String> trainIdsList);
		logger.info("\n----------------------------------GET TRAINS----------------------------------");
		logger.info(trainDao.getTrains(trainIdsList));
		logger.info("--------------------------------------------------------------------\n");
		
		//List<Train> getTrainsByTrainName(String trainName);
		logger.info("\n----------------------------------GET TRAIN BY TRAIN NAME----------------------------------");
		logger.info(trainDao.getTrainsByTrainName(trainName));
		logger.info("--------------------------------------------------------------------\n");
		
		//List<Train> getAllTrains();
		logger.info("\n----------------------------------GET ALL TRAINS----------------------------------");
		logger.info(trainDao.getAllTrains());
		logger.info("--------------------------------------------------------------------\n");
		
		//int getSeatFare(String trainId, String seatType);
		logger.info("\n----------------------------------GET SEAT FARE----------------------------------");
		logger.info(trainDao.getSeatFare(trainId, seatType));
		logger.info("--------------------------------------------------------------------\n");
		
	}
	
	public static void testRouteLogDao() {
		
		logger.info("\n\n\n ---------------------------------- INVOKED ------------------------------ \n\n\n");
		
		RouteLogDao routeLogDao = new RouteLogDaoImpl();
		scanner = new Scanner(System.in);
		
		String trainId = "T2";
		List<String> stationIdsList = new ArrayList<>();
		stationIdsList.add("S3");
		stationIdsList.add("S4");
		stationIdsList.add("S5");
		String sourceStationId = "S3";
		String destinationStationId = "S5";
		String seatType = "AC";
		int numPassengers = 2;
		
		//void ensureStationsEnRoute(String trainId, List<String> stationIdsList);
		logger.info("\n----------------------------------ENSURE STATIONS ENROUTE----------------------------------");
		routeLogDao.ensureStationsEnRoute(trainId, stationIdsList);
		logger.info("--------------------------------------------------------------------\n");

		//void ensureSourceAndDestinationEnRoute(String trainId, String sourceStationId, String destinationStationId);
		logger.info("\n----------------------------------ENSURE CONSECUTIVE STATION ORDER----------------------------------");
		routeLogDao.ensureConsecutiveStationOrder(trainId, sourceStationId, destinationStationId);
		logger.info("--------------------------------------------------------------------\n");

		//String getSourceStationId(String trainId);
		logger.info("\n----------------------------------GET SOURCE STATION ID----------------------------------");
		logger.info(routeLogDao.getSourceStationId(trainId));
		logger.info("--------------------------------------------------------------------\n");

		//String getDestinationStationId(String trainId);
		logger.info("\n----------------------------------GET DESTINATION STATION ID----------------------------------");
		logger.info(routeLogDao.getDestinationStationId(trainId));
		logger.info("--------------------------------------------------------------------\n");

		//List<String> getTrainIdsList(String sourceStationId, String destinationStationId);
		logger.info("\n----------------------------------GET TRAIN IDS LIST----------------------------------");
		logger.info(routeLogDao.getTrainIdsList(sourceStationId, destinationStationId));
		logger.info("--------------------------------------------------------------------\n");

		//List<String> getChargableStationIdsList(String trainId, String sourceStationId, String destinationStationId);
		logger.info("\n----------------------------------GET CHARGABLE STATION IDS LIST----------------------------------");
		logger.info(routeLogDao.getChargableStationIdsList(trainId, sourceStationId, destinationStationId));
		logger.info("--------------------------------------------------------------------\n");

		//List<String> getEnRouteStationIdsList(String trainId);
		logger.info("\n----------------------------------GET ENROUTE STATION IDS LIST----------------------------------");
		logger.info(routeLogDao.getEnRouteStationIdsList(trainId));
		logger.info("--------------------------------------------------------------------\n");

		//int getDistance(String trainId, String sourceStationId, String destinationStationId);
		logger.info("\n----------------------------------GET DISTANCE----------------------------------");
		logger.info(routeLogDao.getDistance(trainId, sourceStationId, destinationStationId));
		logger.info("--------------------------------------------------------------------\n");
		
		//int getJourneyFare(String trainId, String sourceStationId, String destinationStationId, String seatType, int numPassengers);
		logger.info("\n----------------------------------GET JOURNEY FARE----------------------------------");
		logger.info(routeLogDao.getJourneyFare(trainId, sourceStationId, destinationStationId, seatType, numPassengers));
		logger.info("--------------------------------------------------------------------\n");
	}
	
	public static void testServiceableDateDao() {
		
		ServiceableDateDao serviceableDateDao = new ServiceableDateDaoImpl();
		
		String date = "2019-08-23";
		String dayId = "day4";
		
		//void ensureDayId(String dayId);
		logger.info("\n----------------------------------ENSURE DAY ID----------------------------------");
		serviceableDateDao.ensureDayId(dayId);
		logger.info("--------------------------------------------------------------------\n");

		//void validateDateFormat(String date);
		logger.info("\n----------------------------------VALIDATE DATE FORMAT----------------------------------");
		serviceableDateDao.validateDateFormat(date);
		logger.info("--------------------------------------------------------------------\n");
		
		//void ensureDateAvailability(String date);
		logger.info("\n----------------------------------ENSURE DATE AVAILABILITY----------------------------------");
		serviceableDateDao.ensureDateAvailability(date);
		logger.info("--------------------------------------------------------------------\n");

		//List<String> getAvailableDates();
		logger.info("\n----------------------------------GET AVAILABLE DATES----------------------------------");
		logger.info(serviceableDateDao.getAvailableDates());
		logger.info("--------------------------------------------------------------------\n");

		//String getDayId(String date);
		logger.info("\n----------------------------------GET DAY ID----------------------------------");
		logger.info(serviceableDateDao.getDayId(date));
		logger.info("--------------------------------------------------------------------\n");
		
		//String getDate(String dayId);
		logger.info("\n----------------------------------GET DATE----------------------------------");
		logger.info(serviceableDateDao.getDate(dayId));
		logger.info("--------------------------------------------------------------------\n");
		
	}

	public static void testAvailableSeatsLogDao() {
		
		AvailableSeatsLogDao availableSeatsLogDao = new AvailableSeatsLogDaoImpl();
		
		String dayId = "day4";
		String trainId = "T2";
		List<String> stationIdsList = new ArrayList<>();
		stationIdsList.add("S3");
		stationIdsList.add("S4");
		stationIdsList.add("S5");
		List<String> trainIdsList = new ArrayList<>();
		trainIdsList.add("T1");
		trainIdsList.add("T2");
		String sourceStationId = "S3";
		String destinationStationId = "S5";
		String seatType = "AC";
		int numPassengers = 2;
		String stationId = sourceStationId;
		
		scanner = new Scanner(System.in);
		
		//void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType, int numPassengers);
		logger.info("\n----------------------------------ENSURE SEAT AVAILABILITY----------------------------------");
		availableSeatsLogDao.ensureSeatAvailability(dayId, trainId, sourceStationId, destinationStationId, seatType, numPassengers);;
		logger.info("--------------------------------------------------------------------\n");
		scanner.next();
		
		//void ensureSeatAvailability(String dayId, String trainId, String sourceStationId, String destinationStationId, String seatType);
		logger.info("\n----------------------------------ENSURE SEAT AVAILABILITY----------------------------------");
		availableSeatsLogDao.ensureSeatAvailability(dayId, trainId, sourceStationId, destinationStationId, seatType);
		logger.info("--------------------------------------------------------------------\n");
		scanner.next();
		
		//List<String> filterHouseFullTrains(String dayId, List<String>trainIdsList, String sourceStationId, String destinationStationId,String seatType, int numPassengers);
		logger.info("\n----------------------------------FILTER HOUSEFULL TRAINS----------------------------------");
		logger.info(availableSeatsLogDao.filterHouseFullTrains(dayId, trainIdsList, sourceStationId, destinationStationId, seatType, numPassengers));
		logger.info("--------------------------------------------------------------------\n");
		scanner.next();
		
		//List<String> filterHouseFullTrains(String dayId, List<String>trainIdsList, String sourceStationId, String destinationStationId,String seatType);
		logger.info("\n----------------------------------FILTER HOUSEFULL TRAINS----------------------------------");
		logger.info(availableSeatsLogDao.filterHouseFullTrains(dayId, trainIdsList, sourceStationId, destinationStationId, seatType));
		logger.info("--------------------------------------------------------------------\n");
		scanner.next();
		
		//int getAvailableSeats(String dayId, String trainId, String stationId, String seatType);
		logger.info("\n----------------------------------GET AVAILABLE SEATS----------------------------------");
		logger.info(availableSeatsLogDao.getAvailableSeats(dayId, trainId, stationId, seatType));
		logger.info("--------------------------------------------------------------------\n");
		scanner.next();
	}
	
	public static void testTicketDao() {
		
		TicketDao ticketDao = new TicketDaoImpl();
		
		scanner = new Scanner(System.in);
		
		int pnr = 1;
		
		String date = "2019-08-23";
		String trainId = "T2";
		List<String> stationIdsList = new ArrayList<>();
		stationIdsList.add("S3");
		stationIdsList.add("S4");
		stationIdsList.add("S5");
		List<String> trainIdsList = new ArrayList<>();
		trainIdsList.add("T1");
		trainIdsList.add("T2");
		String sourceStationId = "S3";
		String destinationStationId = "S5";
		String seatType = "AC";
		List<Passenger> passengersList = new ArrayList<>();
		passengersList.add(new Passenger("Adam", "1997-01-23", "M"));
		passengersList.add(new Passenger("Barbara", "1998-05-12", "F"));
			
		//Ticket bookTicket(String date, String trainId, String sourceStationId, String destinationStationId, String seatType, List<Passenger> passengersList);
		logger.info("\n----------------------------------BOOK TICKET----------------------------------");
		logger.info(ticketDao.bookTicket(date, trainId, sourceStationId, destinationStationId, seatType, passengersList));
		logger.info("--------------------------------------------------------------------\n");
		
		//void validateTicket(int pnr);
		logger.info("\n----------------------------------ENSURE TICKET WTTH PNR----------------------------------");
		ticketDao.ensureTicketWithPnr(pnr);
		logger.info("--------------------------------------------------------------------\n");
		
		//Ticket getTicket(int pnr);
		logger.info("\n----------------------------------GET AVAILABLE SEATS----------------------------------");
		logger.info(ticketDao.getTicket(pnr));
		logger.info("--------------------------------------------------------------------\n");
	}
}
