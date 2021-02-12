package com.revature.services;

import java.util.List;

import com.revature.beans.Supplier;

public interface SupplierService {
	
	public Supplier getSupplier(int id);
	
	public List<Supplier> getAllSuppliers();
	
	public List<Supplier> getSupplier(String name);
	
	public Supplier addSupplier(Supplier supplier);
	
	public Supplier updateSupplier(Supplier supplier);
	
	public boolean deleteSupplier(int id);
}
