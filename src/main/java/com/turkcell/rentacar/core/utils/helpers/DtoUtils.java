package com.turkcell.rentacar.core.utils.helpers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public final class DtoUtils {
	
	private DtoUtils() {
		
	}
	
	public static <ENT, DTO> List<DTO> listEntityToListDto(
			List<ENT> entityList,
			Class<DTO> cls) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return (List<DTO>) entityList.stream()
				.map(entity -> modelMapper.map(entity, cls))
				.collect(Collectors.toList());
	}
}
