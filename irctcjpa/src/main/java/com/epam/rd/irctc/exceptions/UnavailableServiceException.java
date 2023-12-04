package com.epam.rd.irctc.exceptions;

public class UnavailableServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8707101917615348051L;

	private String message;
	
	public UnavailableServiceException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
