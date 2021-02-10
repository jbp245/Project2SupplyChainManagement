package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Supplier;
import com.revature.repositories.SupplierRepository;

@Service
public class SupplierServiceImpl implements  SupplierService {
	
	@Autowired
	SupplierRepository repo;

	@Override
	public Supplier addSupplier(Supplier supplier) {
		return repo.save(supplier);
	}

	@Override
	public Supplier getSupplier(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return (List<Supplier>) repo.findAll();
	}

	@Override
	public Supplier updateSupplier(Supplier supplier) {
		return repo.save(supplier);
	}

	@Override
	public boolean deleteSupplier(int id) {
		try {
			repo.delete(repo.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}

	}

}
