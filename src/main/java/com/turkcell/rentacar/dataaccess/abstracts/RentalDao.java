package com.turkcell.rentacar.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entities.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {
	
	List<Rental> findByCarId(int carId);
}
