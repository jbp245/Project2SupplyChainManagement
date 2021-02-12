package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Product;
import com.revature.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository repo;

	@Override
	public Product addProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public Product getProduct(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) repo.findAll();
	}
	
	public List<Product> getAllRawProducts() {
		List<Product> allProducts = (List<Product>) repo.findAll();
		List<Product> allRawProducts = new ArrayList<Product>();
		for (Product product : allProducts) {
		    if(product.getProduct_type().equals("raw")) {
		    	allRawProducts.add(product);
		    }
		}
		System.out.println(allRawProducts);
		return allRawProducts;
	}

	@Override
	public Product updateProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public boolean deleteProduct(int id) {
		try {
			repo.delete(repo.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
