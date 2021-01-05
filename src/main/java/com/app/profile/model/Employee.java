package com.app.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name = "m_employee",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "nik")
		})
public class Employee extends AbstractLog{

	@Id
	@Column(length = 20)
	private String nik;
	
	@Column(length = 100)
	private String name;
	
	private String address;
	
	@Column(length = 15)
	private String phoneNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Company company;

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
