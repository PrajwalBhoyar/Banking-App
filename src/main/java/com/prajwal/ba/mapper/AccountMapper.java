package com.prajwal.ba.mapper;

import com.prajwal.ba.dto.AccountDto;
import com.prajwal.ba.entities.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance(),
                accountDto.getAccountType());		
 		return account;
	}

	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto=new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance(),
				account.getAccountType());
		return accountDto;
	}

}