package com.epam.rd.irctc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity(name="RouteLog")
@IdClass(RouteLogKey.class)

@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getSourceStationId",
            query   =   "SELECT train_id, station_id, station_number, cumulative_distance " + 
            			"FROM RouteLog " + 
            			"WHERE train_id = :train_id AND station_number = 1",
                        resultClass=RouteLog.class
    ),
    @NamedNativeQuery(
            name    =   "getDestinationStationId",
            query   =   "SELECT train_id, station_id, station_number, cumulative_distance " + 
	            		"FROM RouteLog " + 
	            		"WHERE RouteLog.train_id = :train_id AND RouteLog.station_number = (" + 
	            		"	SELECT MAX(station_number) AS station_number " + 
	            		"	FROM RouteLog " + 
	            		"	WHERE RouteLog.train_id = :train_id" + 
	            		")",
                        resultClass=RouteLog.class
    ),
    @NamedNativeQuery(
            name    =   "getTrainIdsList",
            query   =   "SELECT train_id, station_id, station_number, cumulative_distance "
					+ "FROM RouteLog "
					+ "WHERE RouteLog.train_id IN ( "
					+ "  SELECT train_id "
					+ "  FROM RouteLog AS source "
					+ "  JOIN RouteLog AS destination "
					+ "  USING (train_id) "
					+ "  WHERE source.station_number <= destination.station_number "
					+ "  AND source.station_id = ? "
					+ "  AND destination.station_id = ? "
					+ " ) "
					+ " ORDER BY RouteLog.train_id",
                        resultClass=RouteLog.class
    ),
    @NamedNativeQuery(
            name    =   "getChargableStationIdsList",
            query   =   "SELECT train_id, station_id, station_number, cumulative_distance \r\n" + 
						" FROM RouteLog \r\n" + 
						" WHERE train_id = :train_id \r\n" + 
						" AND station_number	 BETWEEN \r\n" + 
						" (SELECT station_number FROM RouteLog WHERE station_id = :source_station_id AND train_id = :train_id ) AND \r\n" + 
						" (SELECT station_number FROM RouteLog WHERE station_id = :destination_station_id AND train_id = :train_id ) - 1",
                        resultClass=RouteLog.class
    ),
    @NamedNativeQuery(
            name    =   "getEnRouteStationIdsList",
            query   =   "SELECT train_id, station_id, station_number, cumulative_distance \n" + 
            			"FROM RouteLog \n" + 
            			"WHERE train_id = :train_id",
                        resultClass=RouteLog.class
    ),
    @NamedNativeQuery(
            name    =   "getCumulativeDistance",
            query   =   "SELECT train_id, station_id, station_number, cumulative_distance \n" + 
            			"FROM RouteLog \n" + 
            			"WHERE train_id = :train_id \n" + 
            			"AND station_id = :station_id",
                        resultClass=RouteLog.class
    )
})

public class RouteLog implements Serializable {

	private static final long serialVersionUID = -8149597882756617648L;
	
	@Id
	@Column(name="train_id", updatable=false, nullable=false)
	private String trainId;
	
	@Id
	@Column(name="station_id", updatable=false, nullable=false)
	private String stationId;
	
	@Column(name="station_number", nullable=false)
	private int stationNumber;
	
	@Column(name="cumulative_distance", nullable=false)
	private int cumulativeDistance;
	
	public RouteLog() {}

	public RouteLog(String trainId, String stationId) {
		super();
		this.trainId = trainId;
		this.stationId = stationId;
	}

	public RouteLog(String trainId, String stationId, int stationNumber, int cumulativeDistance) {
		super();
		this.trainId = trainId;
		this.stationId = stationId;
		this.stationNumber = stationNumber;
		this.cumulativeDistance = cumulativeDistance;
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

	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	public int getCumulativeDistance() {
		return cumulativeDistance;
	}

	public void setCumulativeDistance(int cumulativeDistance) {
		this.cumulativeDistance = cumulativeDistance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cumulativeDistance;
		result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
		result = prime * result + stationNumber;
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
		RouteLog other = (RouteLog) obj;
		if (cumulativeDistance != other.cumulativeDistance)
			return false;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		if (stationNumber != other.stationNumber)
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
		return "TrainStation [trainId=" + trainId + ", stationId=" + stationId + ", stationNumber=" + stationNumber
				+ ", cumulativeDistance=" + cumulativeDistance + "]";
	}
}
