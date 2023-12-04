package com.epam.rd.irctc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epam.rd.irctc.model.AvailableSeatsLog;
import com.epam.rd.irctc.model.Station;
import com.epam.rd.irctc.model.Ticket;
import com.epam.rd.irctc.model.Train;

public class DTOFactory {

	private DTOFactory() {
	}

	public static List<Map<String, String>> createDateMapsListDTO(List<String> datesList) {

		List<Map<String, String>> dateMapsListDTO = new ArrayList<>();

		Map<String, String> dateMap;

		dateMapsListDTO.clear();

		for (String date : datesList) {

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

		for (Station station : stationsList) {

			stationMap = new HashMap<>();

			stationMap.put("stationId", station.getId());
			stationMap.put("stationName", station.getName());

			stationMapsListDTO.add(stationMap);
		}

		return stationMapsListDTO;
	}

	public static List<Map<String, String>> getTrainAttributesMapsListDTO() {

		List<Map<String, String>> trainAttributesMapsListDTO = new ArrayList<>();

		Map<String, String> trainAttributesMap;

		String[] trainAttributeArray = new String[] { "Train ID", "Train Name", "AC Seats Journey Fare",
				"Available Number of AC Seats", "Non AC Seats Journey Fare", "Available Number of Non AC Seats" };

		trainAttributesMapsListDTO.clear();

		for (String trainAttribute : trainAttributeArray) {

			trainAttributesMap = new HashMap<>();
			trainAttributesMap.put("attributeName", trainAttribute);
			trainAttributesMapsListDTO.add(trainAttributesMap);
		}

		return trainAttributesMapsListDTO;
	}

	public static List<Map<String, String>> createTrainMapsListDTO(List<Train> trainsList,
			List<AvailableSeatsLog> minAvailableSeatsLogList, Map<Train, Integer> distanceBetweenStationsEnRoute) {

		List<Map<String, String>> trainMapsListDTO = new ArrayList<>();

		Map<String, String> trainMap;

		trainMapsListDTO.clear();

		for (Train train : trainsList) {

			trainMap = new HashMap<>();

			String minNumAvailableACSeats = minAvailableSeatsLogList.stream()
					.filter(record -> record.getSeatType().equals("AC") && record.getTrainId().equals(train.getId()))
					.collect(Collectors.toList()).get(0).getNumSeats() + " / "
					+ Integer.toString(train.getSeatsLog().get("AC").getNumSeats());

			String minNumAvailableNonACSeats = minAvailableSeatsLogList.stream()
					.filter(record -> record.getSeatType().equals("nonAC") && record.getTrainId().equals(train.getId()))
					.collect(Collectors.toList()).get(0).getNumSeats() + " / "
					+ Integer.toString(train.getSeatsLog().get("nonAC").getNumSeats());
			
			int acSeatsJourneyFare = distanceBetweenStationsEnRoute.get(train) * train.getSeatsLog().get("AC").getSeatFare();

			int nonAcSeatsJourneyFare = distanceBetweenStationsEnRoute.get(train) * train.getSeatsLog().get("nonAC").getSeatFare();
			
			trainMap.put("trainId", train.getId());
			trainMap.put("trainName", train.getName());
			trainMap.put("acSeatFare", "Rs." + Integer.toString(acSeatsJourneyFare));
			trainMap.put("numAcSeats", minNumAvailableACSeats);
			trainMap.put("nonAcSeatFare", "Rs." + Integer.toString(nonAcSeatsJourneyFare));
			trainMap.put("numNonAcSeats", minNumAvailableNonACSeats);

			trainMapsListDTO.add(trainMap);
		}

		return trainMapsListDTO;
	}

	public static List<Map<String, String>> getTicketAttributesMapsListDTO() {

		List<Map<String, String>> ticketAttributesMapsListDTO = new ArrayList<>();

		Map<String, String> ticketAttributesMap;

		String[] ticketAttributeArray = new String[] { "PNR", "Date", "Train ID", "Source Station ID",
				"Destination Station ID", "Number Of Passengers", "Seat Type", "Ticket Fare" };

		ticketAttributesMapsListDTO.clear();

		for (String ticketAttribute : ticketAttributeArray) {

			ticketAttributesMap = new HashMap<>();
			ticketAttributesMap.put("attributeName", ticketAttribute);
			ticketAttributesMapsListDTO.add(ticketAttributesMap);
		}

		return ticketAttributesMapsListDTO;
	}

	public static List<Map<String, String>> createTicketMapsListDTO(Ticket ticket) {

		List<Map<String, String>> ticketMapsListDTO = new ArrayList<>();

		Map<String, String> ticketMap = new HashMap<>();

		ticketMap.put("pnr", Integer.toString(ticket.getPnr()));
		ticketMap.put("date", ticket.getDate());
		ticketMap.put("trainId", ticket.getTrainId());
		ticketMap.put("sourceStationId", ticket.getSourceStationId());
		ticketMap.put("destinationStationId", ticket.getDestinationStationId());
		ticketMap.put("numPassengers", Integer.toString(ticket.getNumPassengers()));
		ticketMap.put("seatType", ticket.getSeatType());
		ticketMap.put("ticketFare", Integer.toString(ticket.getTicketFare()));

		ticketMapsListDTO.clear();
		ticketMapsListDTO.add(ticketMap);

		return ticketMapsListDTO;
	}
}
