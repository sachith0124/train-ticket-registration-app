package com.epam.rd.irctc.model;

public class Seats {

	private String trainID;
	private String seatType;
	private int numSeats;
	private int seatFare;

	public Seats(String trainID, String seatType, int numSeats, int seatFare) {
		this.setTrainID(trainID);
		this.setSeatType(seatType);
		this.setNumSeats(numSeats);
		this.setSeatFare(seatFare);
	}

	public String getTrainID() {
		return trainID;
	}

	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public int getSeatFare() {
		return seatFare;
	}

	public void setSeatFare(int seatFare) {
		this.seatFare = seatFare;
	}

	@Override
	public String toString() {
		return "Seats [trainID=" + trainID + ", seatType=" + seatType + ", numSeats=" + numSeats + ", seatPrice="
				+ seatFare + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numSeats;
		result = prime * result + seatFare;
		result = prime * result + ((seatType == null) ? 0 : seatType.hashCode());
		result = prime * result + ((trainID == null) ? 0 : trainID.hashCode());
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
		Seats other = (Seats) obj;
		if (numSeats != other.numSeats)
			return false;
		if (seatFare != other.seatFare)
			return false;
		if (seatType == null) {
			if (other.seatType != null)
				return false;
		} 
		else if (!seatType.equals(other.seatType))
			return false;
		if (trainID == null) {
			if (other.trainID != null)
				return false;
		} 
		else if (!trainID.equals(other.trainID))
			return false;
		return true;
	}

}
