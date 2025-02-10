package com.prajwal.ba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prajwal.ba.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> getByAccountType(String accountType);
}
