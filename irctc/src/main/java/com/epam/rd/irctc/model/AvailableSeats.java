package com.epam.rd.irctc.model;

public class AvailableSeats {

	private String trainId;
	private String stationId;
	private String seatType;
	private int day1Seats;
	private int day2Seats;
	private int day3Seats;
	private int day4Seats;
	private int day5Seats;
	private int day6Seats;

	public AvailableSeats(String trainId, String stationId, String seatType) {
		super();
		this.trainId = trainId;
		this.stationId = stationId;
		this.seatType = seatType;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public int getDay1Seats() {
		return day1Seats;
	}

	public void setDay1Seats(int day1Seats) {
		this.day1Seats = day1Seats;
	}

	public int getDay2Seats() {
		return day2Seats;
	}

	public void setDay2Seats(int day2Seats) {
		this.day2Seats = day2Seats;
	}

	public int getDay3Seats() {
		return day3Seats;
	}

	public void setDay3Seats(int day3Seats) {
		this.day3Seats = day3Seats;
	}

	public int getDay4Seats() {
		return day4Seats;
	}

	public void setDay4Seats(int day4Seats) {
		this.day4Seats = day4Seats;
	}

	public int getDay5Seats() {
		return day5Seats;
	}

	public void setDay5Seats(int day5Seats) {
		this.day5Seats = day5Seats;
	}

	public int getDay6Seats() {
		return day6Seats;
	}

	public void setDay6Seats(int day6Seats) {
		this.day6Seats = day6Seats;
	}

	@Override
	public String toString() {
		return "AvailableSeats [trainId=" + trainId + ", stationID=" + stationId + ", seatType=" + seatType
				+ ", day1Seats=" + day1Seats + ", day2Seats=" + day2Seats + ", day3Seats=" + day3Seats + ", day4Seats="
				+ day4Seats + ", day5Seats=" + day5Seats + ", day6Seats=" + day6Seats + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day1Seats;
		result = prime * result + day2Seats;
		result = prime * result + day3Seats;
		result = prime * result + day4Seats;
		result = prime * result + day5Seats;
		result = prime * result + day6Seats;
		result = prime * result + ((seatType == null) ? 0 : seatType.hashCode());
		result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
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
		AvailableSeats other = (AvailableSeats) obj;
		if (day1Seats != other.day1Seats)
			return false;
		if (day2Seats != other.day2Seats)
			return false;
		if (day3Seats != other.day3Seats)
			return false;
		if (day4Seats != other.day4Seats)
			return false;
		if (day5Seats != other.day5Seats)
			return false;
		if (day6Seats != other.day6Seats)
			return false;
		if (seatType == null) {
			if (other.seatType != null)
				return false;
		} 
		else if (!seatType.equals(other.seatType))
			return false;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} 
		else if (!stationId.equals(other.stationId))
			return false;
		if (trainId == null) {
			if (other.trainId != null)
				return false;
		} 
		else if (!trainId.equals(other.trainId))
			return false;
		return true;
	}

}
