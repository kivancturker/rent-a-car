package com.turkcell.rentacar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="daily_price")
	private double dailyPrice;
	
	@Column(name="model_year")
	private String modelYear;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;
	
	@OneToMany(mappedBy="car")
	private List<CarMaintenance> carMaintenances;
}
