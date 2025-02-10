package com.prajwal.ba.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prajwal.ba.dto.AccountDto;
import com.prajwal.ba.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/account")
	public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
		AccountDto createdAccount = accountService.createAccount(accountDto);
		return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
	}

	@GetMapping("/account/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
		AccountDto accountById = accountService.getAccountById(id);
		return ResponseEntity.ok(accountById);
	}

	@GetMapping("/account/type")
	public ResponseEntity<List<AccountDto>> getByAccountType(@RequestParam String accountType) {
		List<AccountDto> accounts = accountService.getByAccountType(accountType);
		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/account")
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		return ResponseEntity.ok(allAccounts);
	}

	@PutMapping("/account/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody AccountDto accountDto) {

		AccountDto deposit = accountService.deposit(id, accountDto);
		return ResponseEntity.ok(deposit);
	}

	@PutMapping("/account/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody AccountDto accountDto) {

		AccountDto withdraw = accountService.withdraw(id, accountDto);
		return ResponseEntity.ok(withdraw);
	}

	@PutMapping("account/{id}")
	public ResponseEntity<AccountDto> updateAccount(@Valid @RequestBody AccountDto accountDto, @PathVariable Long id) {
		AccountDto updatedAccount = accountService.updateAccount(accountDto, id);
		return ResponseEntity.ok(updatedAccount);
	}

	@DeleteMapping("account/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {

		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully for id: " + id);
	}

}
