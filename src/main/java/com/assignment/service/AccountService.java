package com.assignment.service;

import com.assignment.entity.Account;
import com.assignment.exception.CustomException;
import com.assignment.model.AccountRequest;
import com.assignment.model.BalanceResponse;
import com.assignment.model.TransferModel;

public interface AccountService {

	public Object transfer(TransferModel transferModel) throws CustomException;

	public Account fetchAccountDetails(AccountRequest accountRequest) throws CustomException;
	
	public BalanceResponse performTransfer(Account fromAccount, Account toAccount, TransferModel transferModel);

}
