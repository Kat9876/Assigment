package com.assignment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.assignment.dao.AccountRepository;
import com.assignment.entity.Account;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AssignmentApplication.class, args);
		AccountRepository accountRepository = context.getBean(AccountRepository.class);
		List<Account> accountsToBeSaved = new ArrayList<>();
		accountsToBeSaved.add(new Account("100023222323",new BigDecimal(10000)));
		accountsToBeSaved.add(new Account("100023222324",new BigDecimal(15000)));
		accountsToBeSaved.add(new Account("100023222326",new BigDecimal(10000)));
		accountsToBeSaved.add(new Account("100023222325",new BigDecimal(15000)));
		accountRepository.saveAll(accountsToBeSaved);
	}

}
