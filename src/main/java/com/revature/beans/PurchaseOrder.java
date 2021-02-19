/**
 * 
 */
package com.revature.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author james
 *
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
	
	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
	@GeneratedValue(generator = "order_seq", strategy = GenerationType.SEQUENCE)
	private int id;	
	
	
	private String order_status;
	private Date order_placed_date;
	private Date order_completed_date;
	private Date order_shipped_date;
	private String order_type;
	
	@OneToOne
	@JoinColumn(name = "distribution_invoice_id")
	private DistributionInvoice distributionInvoice;
	
	@OneToOne
	@JoinColumn(name = "supplier_invoice_id")
	private SupplierInvoice supplier_invoice;
	
	/**
	 * DEFAULT CONSTRUCTOR
	 */
	public PurchaseOrder() {
		super();
	}

	/**
	 * ID-LESS CONSTRUCTOR
	 * @param order_status
	 * @param order_placed_date
	 * @param order_completed_date
	 * @param order_shipped_date
	 * @param distribution_invoice_id
	 * @param supplier_invoice_id
	 * @param order_type
	 */
	public PurchaseOrder(String order_status, Date order_placed_date, Date order_completed_date,
			Date order_shipped_date, String order_type, DistributionInvoice distribution_invoice,
			SupplierInvoice supplier_invoice) {
		super();
		this.order_status = order_status;
		this.order_placed_date = order_placed_date;
		this.order_completed_date = order_completed_date;
		this.order_shipped_date = order_shipped_date;
		this.order_type = order_type;
		this.distributionInvoice = distribution_invoice;
		this.supplier_invoice = supplier_invoice;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the order_status
	 */
	public String getOrder_status() {
		return order_status;
	}

	/**
	 * @param order_status the order_status to set
	 */
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	/**
	 * @return the order_placed_date
	 */
	public Date getOrder_placed_date() {
		return order_placed_date;
	}

	/**
	 * @param order_placed_date the order_placed_date to set
	 */
	public void setOrder_placed_date(Date order_placed_date) {
		this.order_placed_date = order_placed_date;
	}

	/**
	 * @return the order_completed_date
	 */
	public Date getOrder_completed_date() {
		return order_completed_date;
	}

	/**
	 * @param order_completed_date the order_completed_date to set
	 */
	public void setOrder_completed_date(Date order_completed_date) {
		this.order_completed_date = order_completed_date;
	}

	/**
	 * @return the order_shipped_date
	 */
	public Date getOrder_shipped_date() {
		return order_shipped_date;
	}

	/**
	 * @param order_shipped_date the order_shipped_date to set
	 */
	public void setOrder_shipped_date(Date order_shipped_date) {
		this.order_shipped_date = order_shipped_date;
	}

	/**
	 * @return the order_type
	 */
	public String getOrder_type() {
		return order_type;
	}
	
	/**
	 * @param order_type the order_type to set
	 */
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public DistributionInvoice getDistribution_invoice() {
		return distributionInvoice;
	}


	public void setDistribution_invoice(DistributionInvoice distribution_invoice) {
		this.distributionInvoice = distribution_invoice;
	}

	public SupplierInvoice getSupplier_invoice() {
		return supplier_invoice;
	}


	public void setSupplier_invoice(SupplierInvoice supplier_invoice) {
		this.supplier_invoice = supplier_invoice;
	}
	
	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", order_status=" + order_status + ", order_placed_date=" + order_placed_date
				+ ", order_completed_date=" + order_completed_date + ", order_shipped_date=" + order_shipped_date
				+ ", order_type=" + order_type + ", distributionInvoice=" + distributionInvoice
				+ ", supplier_invoice=" + supplier_invoice + "]";
	}
	

}
