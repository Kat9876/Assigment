package com.assignment.constants;

public class ErrorConstants {

	public static final String INVALID_DEBIT_ACCOUNT_NUMBER = "INVALID_DEBIT_ACCOUNT_NUMBER";
	public static final String INVALID_CREDIT_ACCOUNT_NUMBER = "INVALID_CREDIT_ACCOUNT_NUMBER";
	public static final String INSUFFICIENT_BALANCE = "INSUFFICIENT_BALANCE";
	public static final String INVALID_ACCOUNT = "INVALID_ACCOUNT";
	public static final String CREDIT_DEBIT_ACCOUNT_SAME = "CREDIT_DEBIT_ACCOUNT_SAME";
	
	private ErrorConstants(){
		throw new IllegalAccessError("ErrorConstants class");
	}

}
