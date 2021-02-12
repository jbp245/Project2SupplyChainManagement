package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Supplier;
import com.revature.repositories.SupplierRepo;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	SupplierRepo supplierrepo;
	
	@Override
	public Supplier getSupplier(int id) {
		return supplierrepo.findById(id).get();
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return (List<Supplier>) supplierrepo.findAll();
	}

	@Override
	public List<Supplier> getSupplier(String name) {
		return supplierrepo.findByName(name);
	}

	@Override
	public Supplier addSupplier(Supplier supplier) {
		return supplierrepo.save(supplier);
	}

	@Override
	public Supplier updateSupplier(Supplier change) {
		return supplierrepo.save(change); //saveOrUpdate
	}

	@Override
	public boolean deleteSupplier(int id) {
		try {
			supplierrepo.delete(supplierrepo.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
