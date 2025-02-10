package com.prajwal.ba.service;

import java.util.List;

import com.prajwal.ba.dto.AccountDto;

public interface AccountService {
 AccountDto createAccount(AccountDto accountDto);
 
 AccountDto getAccountById(Long id);
 
 List<AccountDto> getByAccountType(String accountType);
 
 AccountDto deposit(Long id,AccountDto accountDto);
 
 AccountDto withdraw(Long id,AccountDto accountDto);
 
 List<AccountDto> getAllAccounts();
 
 AccountDto updateAccount(AccountDto accountDto, Long id);
 
 void deleteAccount(Long id);
}
