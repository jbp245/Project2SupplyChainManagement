/**
 * 
 */
package com.revature.services;

import java.util.List;

import com.revature.beans.Product;
import com.revature.beans.PurchaseOrder;
import com.revature.beans.SupplierInvoice;


/**
 * @author james
 *
 */
public interface PurchaseOrderService {

	public PurchaseOrder get(int id);
	public List<PurchaseOrder> getAll();
	public PurchaseOrder add(PurchaseOrder order);
	public PurchaseOrder update(PurchaseOrder change);
	public boolean delete(int id);
	public PurchaseOrder orderNeededRawGoods(SupplierInvoice invoice);
	public String checkInventory(int product_id, int quantity_needed);
	public Product increaseInventoryWhenSuppOrderReceived(PurchaseOrder change);

}
