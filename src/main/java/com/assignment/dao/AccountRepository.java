package com.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

	public Account findByAccountNumber(String accountNumber);
	
	@Query( "select account from Account account where accountNumber in :accountNumbers" )
	public List<Account> findByAccountNumbers(@Param("accountNumbers") List<String> accountNumbers);

}
