package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "distributor")
public class Distributor {

	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "distributor_seq", sequenceName = "distributor_seq", allocationSize = 1)
	@GeneratedValue(generator = "distributor_seq", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column
	private String phone_number;

	public Distributor() {
		super();
	}

	public Distributor(int id, String name, String address, String phone_number) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	@Override
	public String toString() {
		return "Distributor [id=" + id + ", name=" + name + ", address=" + address + ", phone_number=" + phone_number
				+ "]";
	}
	
	
}
