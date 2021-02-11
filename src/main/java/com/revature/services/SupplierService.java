package com.revature.services;

import java.util.List;

import com.revature.beans.Supplier;

public interface SupplierService {
	
	public Supplier addSupplier(Supplier a);
	public Supplier getSupplier(int id);
	public List<Supplier> getAllSuppliers();
	public Supplier updateSupplier(Supplier change);
	public boolean deleteSupplier(int id);

}