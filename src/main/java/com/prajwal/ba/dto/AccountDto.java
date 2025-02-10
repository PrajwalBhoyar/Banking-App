package com.prajwal.ba.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor	
@Table(name = "accounts")
public class AccountDto {
	
	@Column(name = "account_number")
	private Long id;
	
	@Column(name = "account_holder_name")
	@NotBlank(message = "Account holder name is required")
	private String accountHolderName;
	
	
    @Positive(message = "Balance must be positive and cannot be empty")	
	private double balance;
	
	@Column(name = "account_type")
	@NotBlank(message = "Account type is required")
	private String accountType;
}
