package com.epam.rd.irctc.model;

public class Ticket {

	private int pnr;
	private String date;
	private String sourceStationId;
	private String destinationStationId;
	private int numPassengers;
	private String seatType;
	private int ticketFare;

	public Ticket(int pnr, String date, String sourceStationId, String destinationStationId, int numPassengers,
			String seatType, int ticketFare) {
		super();
		this.pnr = pnr;
		this.date = date;
		this.sourceStationId = sourceStationId;
		this.destinationStationId = destinationStationId;
		this.numPassengers = numPassengers;
		this.seatType = seatType;
		this.ticketFare = ticketFare;
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

	@Override
	public String toString() {
		return "Ticket [pnr=" + pnr + ", date=" + date + ", sourceStationId=" + sourceStationId
				+ ", destinationStationId=" + destinationStationId + ", numPassengers=" + numPassengers + ", seatType="
				+ seatType + ", ticketFare=" + ticketFare + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((destinationStationId == null) ? 0 : destinationStationId.hashCode());
		result = prime * result + numPassengers;
		result = prime * result + pnr;
		result = prime * result + ((seatType == null) ? 0 : seatType.hashCode());
		result = prime * result + ((sourceStationId == null) ? 0 : sourceStationId.hashCode());
		result = prime * result + ticketFare;
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
		} 
		else if (!date.equals(other.date))
			return false;
		if (destinationStationId == null) {
			if (other.destinationStationId != null)
				return false;
		} 
		else if (!destinationStationId.equals(other.destinationStationId))
			return false;
		if (numPassengers != other.numPassengers)
			return false;
		if (pnr != other.pnr)
			return false;
		if (seatType == null) {
			if (other.seatType != null)
				return false;
		} 
		else if (!seatType.equals(other.seatType))
			return false;
		if (sourceStationId == null) {
			if (other.sourceStationId != null)
				return false;
		} 
		else if (!sourceStationId.equals(other.sourceStationId))
			return false;
		if (ticketFare != other.ticketFare) {
			return false;
		}
		return true;
	}

}
