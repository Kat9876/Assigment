package com.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
import com.assignment.service.impl.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
class AssignmentApplicationTests {

	@InjectMocks
	private AccountService accountService = new AccountServiceImpl();

	@Mock
	private AccountRepository accountRepository;
	

	@Test
	@DisplayName("Test should pass and fetch accounts for valid account and throw exception for invalid account")
	void fetchAccountDetails() throws Exception {
		
		String validAccountNumber = "1010214514455";
	    String invalidAccountNumber = "1234";
	    Account account = new Account(validAccountNumber, new BigDecimal(100000));
		Mockito.when(accountRepository.findById(validAccountNumber)).thenReturn(Optional.of(account));
		
		//Test to check if correct account details are retrieved
		assertEquals(account ,accountService.fetchAccountDetails(new AccountRequest(validAccountNumber)));
		
		//Test to check if Custom exception is thrown for invalid account number
		CustomException exception = assertThrows(CustomException.class, () -> {
			accountService.fetchAccountDetails(new AccountRequest(invalidAccountNumber));
		});
		//Test to check if error code for invalid account number
		assertEquals(ErrorConstants.INVALID_ACCOUNT,exception.getErrorCode());
	}

	
	
	@Test
	@DisplayName("test to check account transfers")
	void transfer() throws Exception {
		Account account1 = new Account("1010214514455", new BigDecimal(100000));
		Account account2 = new Account("1010214514457", new BigDecimal(100000));
		TransferModel transferModel = new TransferModel();
		transferModel.setAmount(new BigDecimal(2000));
		transferModel.setFromAccount(account1.getAccountNumber());
		transferModel.setToAccount(account2.getAccountNumber());
		Mockito.when(accountRepository.findByAccountNumbers(Arrays.asList(transferModel.getFromAccount(),transferModel.getToAccount()))).thenReturn(Arrays.asList(account1,account2));
		Object result = accountService.transfer(transferModel);
		assertEquals(new ResponseModel(ApiConstants.SUCCESS).getClass(),result.getClass());
		
		BalanceResponse balanceResponse = accountService.performTransfer(account1, account2, transferModel);
		assertEquals(new BigDecimal(104000),balanceResponse.getCreditBalance());
		assertEquals(new BigDecimal(96000),balanceResponse.getDebitBalance());
	}

}
