package com.turkcell.rentacar.core.helpers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.business.exceptions.BusinessException;

public class IdValidator {
	
	public static <T, ID extends Number> void checkIfIdValid(ID id, JpaRepository<T, ID> dao) {
		if (!(id instanceof Integer || id instanceof Long))
		{
			throw new IllegalArgumentException("Id must be Integer or Long.");
		}
		
		if (dao.existsById(id))
		{
			throw new BusinessException("Id doesn't exist on database.");
		}
	}
	
	
}
