package com.prajwal.ba.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with this %s:%s", resourceName, fieldName, fieldValue));

	}

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with this %s:%s", resourceName, fieldName, fieldValue));

	}
	
	
}
