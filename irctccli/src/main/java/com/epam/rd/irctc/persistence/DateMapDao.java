package com.epam.rd.irctc.persistence;

import java.util.List;

import com.epam.rd.irctc.model.DateMap;

public interface DateMapDao {
	
	DateMap getDateMap(int dayId);
	
	List<DateMap> getAllDateMaps();
	
	List<String> getAvailableDates();	
	
	String getDayId(String date);
	
	void updateDate(String dayId, String newDate);
}
