package com.epam.rd.irctc.model;

public class DateMap {

	private String dayId;
	private String date;

	public DateMap(String dayId, String date) {
		super();
		this.dayId = dayId;
		this.date = date;
	}

	public String getDayId() {
		return dayId;
	}

	public void setDayId(String dayId) {
		this.dayId = dayId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DateMap [dayId=" + dayId + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((dayId == null) ? 0 : dayId.hashCode());
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
		DateMap other = (DateMap) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} 
		else if (!date.equals(other.date))
			return false;
		if (dayId == null) {
			if (other.dayId != null)
				return false;
		} 
		else if (!dayId.equals(other.dayId))
			return false;
		return true;
	}
	
	
}
