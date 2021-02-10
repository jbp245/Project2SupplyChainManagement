package com.revature.services;

import java.util.List;

import com.revature.beans.Product;

public interface ProductService {

	public Product addProduct(Product product);
	public Product getProduct(int id);
//	public List<Product> getProduct(String name);
	public List<Product> getAllProducts();
	public Product updateProduct(Product product);
	public boolean deleteProduct(int id);

}
