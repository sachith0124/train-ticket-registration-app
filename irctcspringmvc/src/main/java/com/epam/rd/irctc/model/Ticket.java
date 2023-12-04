package com.epam.rd.irctc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity(name="Ticket")

@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "selectMaxPNR",
            query   =   "SELECT pnr, date, train_id, source_station_id, destination_station_id, num_passengers, seat_type, ticket_fare " +
                        "FROM Ticket " +
                        "WHERE pnr = (" +
                        "  SELECT MAX(pnr) AS pnr " +
                        "  FROM Ticket" +
                        ")",
                        resultClass=Ticket.class
    )
})

public class Ticket implements Serializable {

	private static final long serialVersionUID = -1013747722875717251L;

	@Id
	@Column(name="pnr", updatable=false, nullable=false)
	private int pnr;
	
	@Column(name="date", updatable=false, nullable=false)
	private String date;
	
	@Column(name="train_id", updatable=false, nullable=false)
	private String trainId;
	
	@Column(name="source_station_id", updatable=false, nullable=false)
	private String sourceStationId;
	
	@Column(name="destination_station_id", updatable=false, nullable=false)
	private String destinationStationId;
	
	@Column(name="num_passengers", updatable=false, nullable=false)
	private int numPassengers;
	
	@Column(name="seat_type", updatable=false, nullable=false)
	private String seatType;
	
	@Column(name="ticket_fare", updatable=false, nullable=false)
	private int ticketFare;
	
	@ElementCollection
	private List<Passenger> passengers = new ArrayList<>();

	public Ticket() {
		super();
	}

	public Ticket(int pnr, String date, String trainId, String sourceStationId, String destinationStationId,
			int numPassengers, String seatType, int ticketFare) {
		super();
		this.pnr = pnr;
		this.date = date;
		this.trainId = trainId;
		this.sourceStationId = sourceStationId;
		this.destinationStationId = destinationStationId;
		this.numPassengers = numPassengers;
		this.seatType = seatType;
		this.ticketFare = ticketFare;
	}

	public Ticket(int pnr, String date, String trainId, String sourceStationId, String destinationStationId,
			int numPassengers, String seatType, int ticketFare, List<Passenger> passengers) {
		super();
		this.pnr = pnr;
		this.date = date;
		this.trainId = trainId;
		this.sourceStationId = sourceStationId;
		this.destinationStationId = destinationStationId;
		this.numPassengers = numPassengers;
		this.seatType = seatType;
		this.ticketFare = ticketFare;
		this.passengers = passengers;
	}

	public int getPnr() {
		return pnr;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getSourceStationId() {
		return sourceStationId;
	}

	public void setSourceStationId(String sourceStationId) {
		this.sourceStationId = sourceStationId;
	}

	public String getDestinationStationId() {
		return destinationStationId;
	}

	public void setDestinationStationId(String destinationStationId) {
		this.destinationStationId = destinationStationId;
	}

	public int getNumPassengers() {
		return numPassengers;
	}

	public void setNumPassengers(int numPassengers) {
		this.numPassengers = numPassengers;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public int getTicketFare() {
		return ticketFare;
	}

	public void setTicketFare(int ticketFare) {
		this.ticketFare = ticketFare;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((destinationStationId == null) ? 0 : destinationStationId.hashCode());
		result = prime * result + numPassengers;
		result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
		result = prime * result + pnr;
		result = prime * result + ((seatType == null) ? 0 : seatType.hashCode());
		result = prime * result + ((sourceStationId == null) ? 0 : sourceStationId.hashCode());
		result = prime * result + ticketFare;
		result = prime * result + ((trainId == null) ? 0 : trainId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (destinationStationId == null) {
			if (other.destinationStationId != null)
				return false;
		} else if (!destinationStationId.equals(other.destinationStationId))
			return false;
		if (numPassengers != other.numPassengers)
			return false;
		if (passengers == null) {
			if (other.passengers != null)
				return false;
		} else if (!passengers.equals(other.passengers))
			return false;
		if (pnr != other.pnr)
			return false;
		if (seatType == null) {
			if (other.seatType != null)
				return false;
		} else if (!seatType.equals(other.seatType))
			return false;
		if (sourceStationId == null) {
			if (other.sourceStationId != null)
				return false;
		} else if (!sourceStationId.equals(other.sourceStationId))
			return false;
		if (ticketFare != other.ticketFare)
			return false;
		if (trainId == null) {
			if (other.trainId != null)
				return false;
		} else if (!trainId.equals(other.trainId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [pnr=" + pnr + ", date=" + date + ", trainId=" + trainId + ", sourceStationId=" + sourceStationId
				+ ", destinationStationId=" + destinationStationId + ", numPassengers=" + numPassengers + ", seatType="
				+ seatType + ", ticketFare=" + ticketFare + ", passengers=" + passengers + "]";
	}
}
