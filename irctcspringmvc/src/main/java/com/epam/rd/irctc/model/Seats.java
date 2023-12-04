package com.epam.rd.irctc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Seats implements Serializable {

	private static final long serialVersionUID = -372681889650518749L;

	@Column(name="num_seats")
	private int numSeats;
	
	@Column(name="seat_fare")
	private int seatFare;
	
	public Seats() {}

	public Seats(int numSeats, int seatFare) {
		super();
		this.numSeats = numSeats;
		this.seatFare = seatFare;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numSeats;
		result = prime * result + seatFare;
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
		return true;
	}

	@Override
	public String toString() {
		return "Seats [numSeats=" + numSeats + ", seatFare=" + seatFare + "]";
	}
}
