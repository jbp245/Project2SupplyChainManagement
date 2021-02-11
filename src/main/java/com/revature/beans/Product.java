package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
	@GeneratedValue(generator = "product_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(updatable = false)
	private int id;
	private String product_name;
	@Column(columnDefinition = "NUMBER")
	private double product_cost;
	private int stock_in_warehouse;
	
	public Product() {
		super();
	}

	public Product(String product_name, double product_cost, int stock_in_warehouse) {
		super();
		this.product_name = product_name;
		this.product_cost = product_cost;
		this.stock_in_warehouse = stock_in_warehouse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_cost() {
		return product_cost;
	}

	public void setProduct_cost(double product_cost) {
		this.product_cost = product_cost;
	}

	public int getStock_in_warehouse() {
		return stock_in_warehouse;
	}

	public void setStock_in_warehouse(int stock_in_warehouse) {
		this.stock_in_warehouse = stock_in_warehouse;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", product_name=" + product_name + ", product_cost=" + product_cost
				+ ", stock_in_warehouse=" + stock_in_warehouse + "]";
	}
	
}
