/**
 * 
 */
package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.beans.DistributionInvoice;
import com.revature.beans.PurchaseOrder;

/**
 * @author james
 *
 */
public interface PurchaseOrderRepo extends CrudRepository<PurchaseOrder, Integer> {

	//extra methods here
	public List<PurchaseOrder> findByDistributionInvoice_DistributorId(int distribution_id);
}
