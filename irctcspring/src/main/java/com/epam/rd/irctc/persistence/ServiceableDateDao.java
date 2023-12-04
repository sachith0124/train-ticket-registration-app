package com.epam.rd.irctc.persistence;

import java.util.List;

public interface ServiceableDateDao {
	
	void ensureDayId(String dayId);

	void validateDateFormat(String date);
	
	void ensureDateAvailability(String date);

	List<String> getAvailableDates();

	String getDayId(String date);
	
	String getDate(String dayId);
}
