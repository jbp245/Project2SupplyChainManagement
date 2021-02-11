/**
 * 
 */
package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;

import com.revature.beans.PurchaseOrder;

/**
 * @author james
 *
 */
public interface PurchaseOrderRepo extends CrudRepository<PurchaseOrder, Integer> {

	//extra methods here
}
