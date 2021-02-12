package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.SupplierInvoice;
import com.revature.repositories.SupplierInvoiceRepo;

@Service
public class SupplierInvoiceServiceImpl implements SupplierInvoiceService {

	@Autowired
	SupplierInvoiceRepo supplierinvrepo;
	
	@Override
	public SupplierInvoice getSuppInvoice(int id) {
		return supplierinvrepo.findById(id).get();
	}

	@Override
	public List<SupplierInvoice> getAllSuppInvoice() {
		return (List<SupplierInvoice>) supplierinvrepo.findAll();
	}

	//@Override
	/*public Optional<SupplierInvoice> getSuppInvByProduct(int prod_id) {
		return supplierinvrepo.findById(prod_id);
	}*/

	@Override
	public SupplierInvoice addSupplierInvoice(SupplierInvoice suppinvoice) {
		return supplierinvrepo.save(suppinvoice);
	}

	@Override
	public SupplierInvoice updateSupplierInvoice(SupplierInvoice change) {
		return supplierinvrepo.save(change);
	}

	@Override
	public boolean deleteSupplierInvoice(int invoiceid) {
		try {
			supplierinvrepo.delete(supplierinvrepo.findById(invoiceid).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

}
