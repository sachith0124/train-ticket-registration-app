package com.epam.rd.irctc.model;

public class RouteLog {

	private String trainId;
	private String stationId;
	private int stationNumber;
	private int cumulativeDistance;

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

	@Override
	public String toString() {
		return "RouteLog [trainId=" + trainId + ", stationId=" + stationId + ", stationNumber=" + stationNumber
				+ ", cumulativeDistance=" + cumulativeDistance + "]";
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
		} 
		else if (!stationId.equals(other.stationId))
			return false;
		if (stationNumber != other.stationNumber)
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
