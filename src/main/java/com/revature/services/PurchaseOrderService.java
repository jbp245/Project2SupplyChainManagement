/**
 * 
 */
package com.revature.services;

import java.util.List;

import com.revature.beans.PurchaseOrder;


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

}
