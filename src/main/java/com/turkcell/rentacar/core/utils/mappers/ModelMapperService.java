package com.turkcell.rentacar.core.utils.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface ModelMapperService {
	ModelMapper forDto();
	ModelMapper forRequest();
}
