package com.turkcell.rentacar.entities.concretes;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@Column(name = "rent_date")
	private LocalDate rentDate;
	
	@Column(name = "returnDate")
	private LocalDate returnDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "ordered_additional_services", 
			  joinColumns = @JoinColumn(name = "additional_service_id"), 
			  inverseJoinColumns = @JoinColumn(name = "rental_id"))
	private Set<AdditionalService> additionalServices;
	
	@ManyToOne
	@JoinColumn(name = "rent_city_id")
	private City rentCity;
	
	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City returnCity;
	
	@Column(name = "price")
	private double price;
	
	@OneToOne
	@JoinColumn(name = "invoice_no")
	private Invoice invoice;
	
}
