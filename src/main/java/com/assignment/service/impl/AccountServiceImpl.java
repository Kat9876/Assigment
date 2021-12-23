package com.assignment.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.constants.ApiConstants;
import com.assignment.constants.ErrorConstants;
import com.assignment.dao.AccountRepository;
import com.assignment.entity.Account;
import com.assignment.exception.CustomException;
import com.assignment.model.AccountRequest;
import com.assignment.model.BalanceResponse;
import com.assignment.model.ResponseModel;
import com.assignment.model.TransferModel;
import com.assignment.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Object transfer(TransferModel transferModel) throws CustomException {
		List<Account> accounts = accountRepository.findByAccountNumbers(Arrays.asList(transferModel.getFromAccount(),transferModel.getToAccount()));
		Optional<Account> fromAccount = accounts.stream().filter(x->transferModel.getFromAccount().equals(x.getAccountNumber())).findFirst();
		Optional<Account> toAccount = accounts.stream().filter(x->transferModel.getToAccount().equals(x.getAccountNumber())).findFirst();
		checkData(fromAccount,toAccount,transferModel);
		performTransfer(fromAccount.get(),toAccount.get(),transferModel);
		return new ResponseModel(ApiConstants.SUCCESS);
	}
	
	@Override
	public BalanceResponse performTransfer(Account fromAccount, Account toAccount, TransferModel transferModel) {
		BigDecimal fromAccountBalance = fromAccount.getBalance().subtract(transferModel.getAmount());
		BigDecimal toAccountBalance = toAccount.getBalance().add(transferModel.getAmount());
		fromAccount.setBalance(fromAccountBalance);
		toAccount.setBalance(toAccountBalance);
		accountRepository.saveAll(Arrays.asList(fromAccount,toAccount));
		return new BalanceResponse(fromAccountBalance,toAccountBalance);
	}

	private void checkData(Optional<Account> fromAccount, Optional<Account> toAccount, TransferModel transferModel) throws CustomException {
		if(fromAccount.isEmpty()) {
			throw new CustomException(ErrorConstants.INVALID_DEBIT_ACCOUNT_NUMBER);
		}
		if(toAccount.isEmpty()) {
			throw new CustomException(ErrorConstants.INVALID_CREDIT_ACCOUNT_NUMBER);
		}
		if(toAccount.get().getAccountNumber().equals(fromAccount.get().getAccountNumber())) {
			throw new CustomException(ErrorConstants.CREDIT_DEBIT_ACCOUNT_SAME);
		}
		if(transferModel.getAmount().compareTo(fromAccount.get().getBalance())>0) {
			throw new CustomException(ErrorConstants.INSUFFICIENT_BALANCE);
		}
		
	}

	@Override
	public Account fetchAccountDetails(AccountRequest accountRequest) throws CustomException {
		return accountRepository.findById(accountRequest.getAccountNumber())
				.orElseThrow(() -> new CustomException(ErrorConstants.INVALID_ACCOUNT));
	}

}
