package com.turkcell.rentacar.dataaccess.abstracts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.turkcell.rentacar.entities.concretes.Brand;

class BrandDaoTest {

	@Autowired
	private BrandDao underTest;
	
	@Test
	void itShouldGetByBrandName() {
		// given
		String brandName = "tesla";
		Brand brand = new Brand();
		brand.setBrandName(brandName);
		underTest.save(brand);
		// when
		Brand result = underTest.findByBrandName(brandName);
		// then
		
	}

}
