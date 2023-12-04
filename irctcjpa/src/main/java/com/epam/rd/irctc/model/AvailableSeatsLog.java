package com.epam.rd.irctc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@Entity(name="AvailableSeatsLog")
@IdClass(AvailableSeatsLog.class)

@SqlResultSetMapping(name="updateResult", columns = { @ColumnResult(name = "count")})

@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getRecord",
            query   =   "SELECT train_id, station_id, day_id, seat_type, num_seats " +
                    	"FROM AvailableSeatsLog " +
                    	"WHERE train_id = :train_id " +
                    	"AND station_id = :station_id " +
                    	"AND day_id = :day_id " +
                    	"AND seat_type = :seat_type",
                        resultClass=AvailableSeatsLog.class
    ),
    @NamedNativeQuery(
    		name    =   "getMinAvailableSeats",
            query   =   "SELECT train_id, \n" + 
	            		"	\"null\" AS station_id, \n" + 
	            		"	\"null\" AS day_id, \n" + 
	            		"	seat_type, \n" + 
	            		"	MIN(num_seats) AS num_seats \n" + 
	            		"FROM AvailableSeatsLog \n" + 
	            		"WHERE day_id = :day_id \n" + 
	            		"AND train_id = :train_id \n" + 
	            		"AND station_id IN :station_ids \n" + 
	            		"GROUP BY train_id, seat_type ;",
                        resultClass=AvailableSeatsLog.class
    ),
    @NamedNativeQuery(
            name    =   "reserveSeats",
            query   =   "UPDATE AvailableSeatsLog \n" + 
	            		"SET num_seats = num_seats - :num_passengers \n" + 
	            		"WHERE day_id = :day_id \n" + 
	            		"AND train_id = :train_id \n" + 
	            		"AND seat_type = :seat_type\n" + 
	            		"AND station_id IN :station_ids",
	            		resultSetMapping = "updateResult"
    )
})

public class AvailableSeatsLog implements Serializable {

	private static final long serialVersionUID = -4858717854003486099L;
	
	@Id
	@Column(name="train_id", updatable=false, nullable=false)
	private String trainId;
	
	@Id
	@Column(name="station_id", updatable=false, nullable=false)
	private String stationId;
	
	@Id
	@Column(name="day_id", updatable=false, nullable=false)
	private String dayId;
	
	@Id
	@Column(name="seat_type", updatable=false, nullable=false)
	private String seatType;
	
	@Column(name="num_seats")
	private int numSeats;

	public AvailableSeatsLog() {
		super();
	}

	public AvailableSeatsLog(String trainId, String stationId, String dayId, String seatType) {
		super();
		this.trainId = trainId;
		this.stationId = stationId;
		this.dayId = dayId;
		this.seatType = seatType;
	}

	public AvailableSeatsLog(String trainId, String stationId, String dayId, String seatType, int numSeats) {
		super();
		this.trainId = trainId;
		this.stationId = stationId;
		this.dayId = dayId;
		this.seatType = seatType;
		this.numSeats = numSeats;
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

	public String getDayId() {
		return dayId;
	}

	public void setDayId(String dayId) {
		this.dayId = dayId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayId == null) ? 0 : dayId.hashCode());
		result = prime * result + numSeats;
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
		AvailableSeatsLog other = (AvailableSeatsLog) obj;
		if (dayId == null) {
			if (other.dayId != null)
				return false;
		} else if (!dayId.equals(other.dayId))
			return false;
		if (numSeats != other.numSeats)
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
		return "AvailableSeatsLog [trainId=" + trainId + ", stationId=" + stationId + ", dayId=" + dayId + ", seatType="
				+ seatType + ", numSeats=" + numSeats + "]";
	}
}
