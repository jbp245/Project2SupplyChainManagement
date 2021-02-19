package com.revature.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name = "supplier_invoice")
public class SupplierInvoice {
	
	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "INVOICE_SEQ", sequenceName = "INVOICE_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "INVOICE_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	private int product_id;
	
	private int supplier_id;
	
	private int user_id;
	
	private int order_quantity;
	
	@Column(columnDefinition = "number")
	private double total_cost;
	
	private Date date_issued;
	
	
	public SupplierInvoice() {
		super();
	}
	
	public SupplierInvoice(int id, int product_id, int supplier_id, int user_id, int order_quantity, double total_cost,
			Date date_issued) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.supplier_id = supplier_id;
		this.user_id = user_id;
		this.order_quantity = order_quantity;
		this.total_cost = total_cost;
		this.date_issued = date_issued;
	}



	public SupplierInvoice(int product_id, int supplier_id, int user_id, int order_quantity, double total_cost,
			Date date_issued) {
		super();
		this.product_id = product_id;
		this.supplier_id = supplier_id;
		this.user_id = user_id;
		this.order_quantity = order_quantity;
		this.total_cost = total_cost;
		this.date_issued = date_issued;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public int getSupplier_id() {
		return supplier_id;
	}


	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public int getOrder_quantity() {
		return order_quantity;
	}


	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	
	public double getTotal_cost() {
		return total_cost;
	}


	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}


	public Date getDate_issued() {
		return date_issued;
	}


	public void setDate_issued(Date date_issued) {
		this.date_issued = date_issued;
	}


	@Override
	public String toString() {
		return "SupplierInvoice [id=" + id + ", product_id=" + product_id + ", supplier_id=" + supplier_id
				+ ", user_id=" + user_id + ", order_quantity=" + order_quantity + ", total_cost=" + total_cost
				+ ", date_issued=" + date_issued + "]";
	}


	
	

}
