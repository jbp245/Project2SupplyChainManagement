package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity //Denotes the Class as having DB representation
@Table(name = "supplier")
public class Supplier {
	
	@SequenceGenerator(name = "supplier_seq", sequenceName = "supplier_seq", allocationSize = 1)
	@GeneratedValue(generator = "supplier_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(updatable = false)
	private int id;
	private String name;
	private String address;
	private String phone_number;
	private int product_id;
	
	public Supplier() {
		super();
	}
	
	public Supplier(String name, String address, String phone_number, int product_id) {
		super();
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
		this.product_id = product_id;
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
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", address=" + address + ", phone_number=" + phone_number
				+ ", product_id=" + product_id + "]";
	}
	
}
