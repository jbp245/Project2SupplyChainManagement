package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

	public Product findByName(String product_name);
}
