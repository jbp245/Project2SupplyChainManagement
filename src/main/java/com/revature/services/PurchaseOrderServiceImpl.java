/**
 * 
 */
package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.PurchaseOrder;
import com.revature.repositories.PurchaseOrderRepo;

/**
 * @author james
 *
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	@Autowired
	PurchaseOrderRepo por;

	@Override
	public PurchaseOrder get(int id) {
		return por.findById(id).get();
	}

	@Override
	public List<PurchaseOrder> getAll() {
		return (List<PurchaseOrder>) por.findAll();
	}

	@Override
	public PurchaseOrder add(PurchaseOrder a) {
		return por.save(null);
	}

	@Override
	public PurchaseOrder update(PurchaseOrder change) {
		return por.save(change);
	}

	@Override
	public boolean delete(int id) {
		try {
			por.delete(por.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
