package com.epam.rd.irctc.exceptions;

public class InconsistentDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2970263376788663490L;
	
	private String message;
	
	public InconsistentDataException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
