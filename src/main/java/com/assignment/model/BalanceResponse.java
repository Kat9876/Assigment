package com.assignment.model;

import java.math.BigDecimal;

public class BalanceResponse {
	
	private BigDecimal debitBalance;
	
	private BigDecimal creditBalance;

	public BalanceResponse(BigDecimal debitBalance, BigDecimal creditBalance) {
		super();
		this.debitBalance = debitBalance;
		this.creditBalance = creditBalance;
	}

	public BigDecimal getDebitBalance() {
		return debitBalance;
	}

	public void setDebitBalance(BigDecimal debitBalance) {
		this.debitBalance = debitBalance;
	}

	public BigDecimal getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(BigDecimal creditBalance) {
		this.creditBalance = creditBalance;
	}

}
