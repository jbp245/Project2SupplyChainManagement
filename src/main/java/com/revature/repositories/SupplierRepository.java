package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer> {

}
