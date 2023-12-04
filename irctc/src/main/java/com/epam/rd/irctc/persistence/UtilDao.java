package com.epam.rd.irctc.persistence;

import java.util.List;

public interface UtilDao {
	
	String listToSQLTupleNotation(List<String> list);
	
	void ensureDateAvailability();
	
	void ensureTrainAvailability();
	
}
