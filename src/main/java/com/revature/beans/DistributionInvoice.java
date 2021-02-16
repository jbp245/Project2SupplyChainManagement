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
@Table(name = "distribution_invoice")
public class DistributionInvoice {

	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "invoice_seq", sequenceName = "invoice_seq", allocationSize = 1)
	@GeneratedValue(generator = "invoice_seq", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column
	private Date date_issued;
	
	@Column
	private int product_id;
	
	@Column(columnDefinition = "number")
	private double total_cost;
	
	@Column(name = "distributor_id")
	private int distributorId;
	
	@Column
	private int order_quantity;

	public DistributionInvoice() {
		super();
	}

	public DistributionInvoice(int id, Date date_issued, int product_id, double total_cost, int distributor_id,
			int order_quantity) {
		super();
		this.id = id;
		this.date_issued = date_issued;
		this.product_id = product_id;
		this.total_cost = total_cost;
		this.distributorId = distributor_id;
		this.order_quantity = order_quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_issued() {
		return date_issued;
	}

	public void setDate_issued(Date date_issued) {
		this.date_issued = date_issued;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

	public int getDistributor_id() {
		return distributorId;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributorId = distributor_id;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	@Override
	public String toString() {
		return "DistributionInvoice [id=" + id + ", date_issued=" + date_issued + ", product_id=" + product_id
				+ ", total_cost=" + total_cost + ", distributor_id=" + distributorId + ", order_quantity="
				+ order_quantity + "]";
	}
	
	
}
