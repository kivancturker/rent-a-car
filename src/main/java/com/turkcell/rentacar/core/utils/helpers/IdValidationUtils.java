package com.turkcell.rentacar.core.utils.helpers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.business.exceptions.BusinessException;
import com.turkcell.rentacar.core.utils.constants.Messages;

public final class IdValidationUtils {
	
	private IdValidationUtils () {
		
	}
	
	public static <T, ID extends Number> void checkIfIdValid(ID id, JpaRepository<T, ID> dao) {
		if (id == null)
		{
			throw new IllegalArgumentException(Messages.ID_VALIDATION_UTILS_NULL);
		}
		
		if (!(id instanceof Integer || id instanceof Long))
		{
			throw new IllegalArgumentException(Messages.ID_VALIDATION_UTILS_NOT_VALID_TYPE);
		}
		
		if (!dao.existsById(id))
		{
			throw new BusinessException(Messages.ID_VALIDATION_UTILS_ID_NOT_EXIST);
		}
	}
}
