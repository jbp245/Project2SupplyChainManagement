package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

}
