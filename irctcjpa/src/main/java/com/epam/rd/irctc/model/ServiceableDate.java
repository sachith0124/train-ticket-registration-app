package com.epam.rd.irctc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity(name="ServiceableDatesDictionary")

@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllRecords",
            query   =   "SELECT day_id, date " +
                    	"FROM ServiceableDatesDictionary ",
                        resultClass=ServiceableDate.class
    ),
    @NamedNativeQuery(
            name    =   "getDayId",
            query   =   "SELECT day_id, date " +
                    	"FROM ServiceableDatesDictionary " +
                    	"WHERE date = :date",
                        resultClass=ServiceableDate.class
    )
})

public class ServiceableDate implements Serializable  {

	private static final long serialVersionUID = -4717760943145360751L;
	
	@Id
	@Column(name="day_id", nullable=false, unique=true)
	private String dayId;

	@Column(name="date", nullable=false, unique=true)
	private String date;

	public ServiceableDate() {}
	
	public ServiceableDate(String date, String dayId) {
		super();
		this.date = date;
		this.dayId = dayId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayId() {
		return dayId;
	}

	public void setDayId(String dayId) {
		this.dayId = dayId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		ServiceableDate other = (ServiceableDate) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (dayId == null) {
			if (other.dayId != null)
				return false;
		} else if (!dayId.equals(other.dayId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceableDatesDictionary [date=" + date + ", dayId=" + dayId + "]";
	}
}
