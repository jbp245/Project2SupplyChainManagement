/**
 * 
 */
package com.revature.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author james
 *
 */
@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder {
	
	@Id
	@Column(updatable = false)
	@GeneratedValue
	private int id;	
	private String product_offered;
	private String order_status;
	@Temporal(TemporalType.DATE)
	private Date order_placed_date;
	@Temporal(TemporalType.DATE)
	private Date order_completed_date;
	@Temporal(TemporalType.DATE)
	private Date order_shipped_date;
	private int order_quantity;
	private int invoice_id;
	private String order_type;
	
	/**
	 * DEFAULT CONSTRUCTOR
	 */
	public PurchaseOrder() {
		super();
	}

	/**
	 * ID-LESS CONSTRUCTOR
	 * @param product_offered
	 * @param order_status
	 * @param order_placed_date
	 * @param order_completed_date
	 * @param order_shipped_date
	 * @param order_quantity
	 * @param invoice_id
	 * @param order_type
	 */
	public PurchaseOrder(String product_offered, String order_status, Date order_placed_date, Date order_completed_date,
			Date order_shipped_date, int order_quantity, int invoice_id, String order_type) {
		super();
		this.product_offered = product_offered;
		this.order_status = order_status;
		this.order_placed_date = order_placed_date;
		this.order_completed_date = order_completed_date;
		this.order_shipped_date = order_shipped_date;
		this.order_quantity = order_quantity;
		this.invoice_id = invoice_id;
		this.order_type = order_type;
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
	 * @return the product_offered
	 */
	public String getProduct_offered() {
		return product_offered;
	}

	/**
	 * @param product_offered the product_offered to set
	 */
	public void setProduct_offered(String product_offered) {
		this.product_offered = product_offered;
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
	 * @return the order_quantity
	 */
	public int getOrder_quantity() {
		return order_quantity;
	}

	/**
	 * @param order_quantity the order_quantity to set
	 */
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	/**
	 * @return the invoice_id
	 */
	public int getInvoice_id() {
		return invoice_id;
	}

	/**
	 * @param invoice_id the invoice_id to set
	 */
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
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

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", product_offered=" + product_offered + ", order_status=" + order_status
				+ ", order_placed_date=" + order_placed_date + ", order_completed_date=" + order_completed_date
				+ ", order_shipped_date=" + order_shipped_date + ", order_quantity=" + order_quantity + ", invoice_id="
				+ invoice_id + ", order_type=" + order_type + "]";
	}
}
