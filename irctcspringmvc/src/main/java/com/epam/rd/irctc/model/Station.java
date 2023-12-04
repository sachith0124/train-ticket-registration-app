package com.epam.rd.irctc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity(name="Station")

@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllStations",
            query   =   "SELECT station_id, station_name " +
                    	"FROM Station ",
                        resultClass=Station.class
    ),
    @NamedNativeQuery(
            name    =   "getStationsByStationName",
            query   =   "SELECT station_id, station_name " +
                        "FROM Station " +
                        "WHERE Station.station_name = ?",
                        resultClass=Station.class
    ),
    @NamedNativeQuery(
            name    =   "getStationsByStationIds",
            query   =   "SELECT station_id, station_name " +
                        "FROM Station " +
                        "WHERE Station.station_id IN :ids",
                        resultClass=Station.class
    )
})

public class Station implements Serializable {

	private static final long serialVersionUID = 1954177176388423734L;
	
	@Id
	@Column(name="station_id", updatable=false, nullable=false)
	private String id;
	
	@Column(name="station_name", nullable=false)
	private String name;
	
	public Station() {}

	public Station(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Station other = (Station) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", name=" + name + "]";
	}
}
