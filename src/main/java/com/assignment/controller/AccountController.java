package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.constants.ApiConstants;
import com.assignment.entity.Account;
import com.assignment.exception.CustomException;
import com.assignment.model.AccountRequest;
import com.assignment.model.TransferModel;
import com.assignment.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping(ApiConstants.FETCH_ACCOUNT_DETAILS_API)
	public Account fetchAccountDetails(@RequestBody AccountRequest accountRequest) throws CustomException {
		return accountService.fetchAccountDetails(accountRequest);
	}
	
	@PostMapping(ApiConstants.TRANSFER_API)
	public Object transfer(@RequestBody TransferModel transferModel) throws CustomException {
		return accountService.transfer(transferModel);
	}

}
 