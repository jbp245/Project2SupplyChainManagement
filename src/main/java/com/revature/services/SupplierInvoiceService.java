package com.revature.services;

import java.util.List;

import com.revature.beans.SupplierInvoice;

public interface SupplierInvoiceService {
	
	public SupplierInvoice getSuppInvoice(int id);
	
	public List<SupplierInvoice> getAllSuppInvoice();
	
	//public SupplierInvoice getSuppInvByProduct(int prod_id);
	
	public SupplierInvoice addSupplierInvoice(SupplierInvoice suppinvoice);
	
	public SupplierInvoice updateSupplierInvoice(SupplierInvoice change);
	
	public boolean deleteSupplierInvoice(int invoiceid);

}
