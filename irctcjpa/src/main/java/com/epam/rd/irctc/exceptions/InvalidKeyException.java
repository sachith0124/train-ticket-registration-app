package com.epam.rd.irctc.exceptions;

public class InvalidKeyException extends RuntimeException{
	
	static final long serialVersionUID = -4164864574956391493L;
	
	private String message;
	
	public InvalidKeyException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
