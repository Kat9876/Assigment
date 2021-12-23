package com.assignment.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;


public class TransferModel {
	
	@NotNull(message = "Account number cannot be empty")
	private String fromAccount;
	
	@NotNull(message = "Account number cannot be empty")
	private String toAccount;
	
	@NotNull(message = "Transfer amount cannot be empty")
	private BigDecimal amount;

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

}
