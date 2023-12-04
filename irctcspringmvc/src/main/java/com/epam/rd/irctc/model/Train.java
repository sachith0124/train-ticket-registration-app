package com.epam.rd.irctc.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;

@Entity(name="Train")

@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllTrains",
            query   =   "SELECT train_id, train_name, source_station_id, destination_station_id " +
                    	"FROM Train ",
                        resultClass=Train.class
    ),
    @NamedNativeQuery(
            name    =   "getTrainsByTrainName",
            query   =   "SELECT train_id, train_name, source_station_id, destination_station_id " +
                        "FROM Train " +
                        "WHERE Train.train_name = ?",
                        resultClass=Train.class
    ),
    @NamedNativeQuery(
            name    =   "getTrainsByTrainIds",
            query   =   "SELECT train_id, train_name, source_station_id, destination_station_id " +
                        "FROM Train " +
                        "WHERE Train.train_id IN :ids",
                        resultClass=Train.class
    )
})

public class Train implements Serializable {

	private static final long serialVersionUID = 4212735667253323901L;
	
	@Id
	@Column(name="train_id", updatable=false, nullable=false)
	private String id;
	
	@Column(name="train_name", updatable=false, nullable=false)
	private String name;
	
	@OneToOne
	private Station source;
	
	@OneToOne
	private Station destination;
	
	@ElementCollection
	@MapKeyColumn(name="seat_type")
	private Map<String, Seats> seatsLog = new HashMap<>();

	
	public Train() {
		super();
	}
	
	public Train(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Train(String id, String name, Station source, Station destination) {
		super();
		this.id = id;
		this.name = name;
		this.source = source;
		this.destination = destination;
	}

	public Train(String id, String name, Station source, Station destination, Map<String, Seats> seatsLog) {
		super();
		this.id = id;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.seatsLog = seatsLog;
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

	public Station getSource() {
		return source;
	}

	public void setSource(Station source) {
		this.source = source;
	}

	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destination) {
		this.destination = destination;
	}

	public Map<String, Seats> getSeatsLog() {
		return seatsLog;
	}

	public void setSeatsLog(Map<String, Seats> seatsLog) {
		this.seatsLog = seatsLog;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((seatsLog == null) ? 0 : seatsLog.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		Train other = (Train) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
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
		if (seatsLog == null) {
			if (other.seatsLog != null)
				return false;
		} else if (!seatsLog.equals(other.seatsLog))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", name=" + name + ", source=" + source + ", destination=" + destination
				+ ", seatsLog=" + seatsLog + "]";
	}
}
