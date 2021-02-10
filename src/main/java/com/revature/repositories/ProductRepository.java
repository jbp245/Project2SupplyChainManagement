package com.revature.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.revature.beans.Product;

@Repository
public interface ProductRepository {
	
	public void addProduct(Product product);
	public Product getProduct(int id);
	public List<Product> getAllProducts();
	public Product updateProduct(Product product);
	public boolean deleteProduct(int id);

}
