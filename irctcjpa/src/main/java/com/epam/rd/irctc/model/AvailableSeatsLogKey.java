package com.epam.rd.irctc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AvailableSeatsLogKey implements Serializable {

	private static final long serialVersionUID = 649721161535716439L;

	@Column(name="train_id")
	private String trainId;
	
	@Column(name="station_id")
	private String stationId;
	
	@Column(name="date")
	private String date;
	
	@Column(name="seat_type")
	private String seatType;

	public AvailableSeatsLogKey() {
		super();
	}

	public AvailableSeatsLogKey(String trainId, String stationId, String date, String seatType) {
		super();
		this.trainId = trainId;
		this.stationId = stationId;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		AvailableSeatsLogKey other = (AvailableSeatsLogKey) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (seatType == null) {
			if (other.seatType != null)
				return false;
		} else if (!seatType.equals(other.seatType))
			return false;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
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
		return "AvailableSeatsId [trainId=" + trainId + ", stationId=" + stationId + ", date=" + date + ", seatType="
				+ seatType + "]";
	}
}