package com.prajwal.ba.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prajwal.ba.dto.AccountDto;
import com.prajwal.ba.entities.Account;
import com.prajwal.ba.exceptions.InsufficientBalanceException;
import com.prajwal.ba.exceptions.ResourceNotFoundException;
import com.prajwal.ba.mapper.AccountMapper;
import com.prajwal.ba.repositories.AccountRepository;
import com.prajwal.ba.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	AccountRepository accountRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public List<AccountDto> getByAccountType(String accountType) {
		List<Account> accounts = accountRepository.getByAccountType(accountType);

		if (accounts.isEmpty()) {
			throw new ResourceNotFoundException("Account", "account type", accountType);
		}

		return accounts.stream().map(account -> new AccountDto(account.getId(), account.getAccountHolderName(),
				account.getBalance(), account.getAccountType())).collect(Collectors.toList());
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> allAccounts = this.accountRepository.findAll();
		return allAccounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	}

	@Override
	public AccountDto deposit(Long id, AccountDto accountDto) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

		account.setBalance(account.getBalance() + accountDto.getBalance());
		Account save = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(save);
	}

	@Override
	public AccountDto withdraw(Long id, AccountDto accountDto) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

		if (account.getBalance() < accountDto.getBalance()) {
			throw new InsufficientBalanceException("Insufficient balance in account ID: " + id);
		}

		account.setBalance(account.getBalance() - accountDto.getBalance());
		Account save = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(save);
	}

	@Override
	public AccountDto updateAccount(AccountDto accountDto, Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

		account.setAccountHolderName(accountDto.getAccountHolderName());
		account.setAccountType(accountDto.getAccountType());
		Account save = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(save);
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
		accountRepository.delete(account);

	}

}
