package com.turkcell.rentacar.dataaccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {
	Car findByCarName(String name);
}
