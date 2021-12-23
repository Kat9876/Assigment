package com.assignment.exception;



public class CustomException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8063825754351841286L;
	private final String errorCode;
	
	public CustomException(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
