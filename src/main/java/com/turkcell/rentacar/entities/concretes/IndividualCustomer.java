package com.turkcell.rentacar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "individual_customers")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class IndividualCustomer extends Customer {

	@Column(name = "id", 
			insertable = false, updatable = false)
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastname;
	
	@Column(name = "identity_number", unique = true)
	private String identityNumber;
}
