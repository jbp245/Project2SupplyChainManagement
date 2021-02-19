package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.beans.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Integer> {
	List<Supplier> findByName(String name);

}

