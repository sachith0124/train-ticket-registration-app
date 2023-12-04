package com.epam.rd.irctc.persistence;

import java.util.List;

public class DBUtil {

	private DBUtil() {}
	
	public static String listToSQLTupleNotation(List<String> list) {
		
		StringBuilder tupleNotationSB = new StringBuilder("");
		
		for(String item: list) {
			tupleNotationSB.append("'");
			tupleNotationSB.append(item);
			tupleNotationSB.append("'");
			tupleNotationSB.append(",");
			tupleNotationSB.append(" ");
		}
		
		tupleNotationSB.insert(0, '(');
		tupleNotationSB.replace(tupleNotationSB.length()-2, tupleNotationSB.length(), ")");
		
		return tupleNotationSB.toString();
	}
}
