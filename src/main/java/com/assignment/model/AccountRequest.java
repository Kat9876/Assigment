package com.assignment.model;

import javax.validation.constraints.NotNull;

public class AccountRequest {

	@NotNull(message = "Account number cannot be empty")
	private String accountNumber;

	public AccountRequest(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	public AccountRequest() {
		super();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
