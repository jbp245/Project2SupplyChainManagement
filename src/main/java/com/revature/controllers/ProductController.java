package com.revature.controllers;

import static org.assertj.core.api.Assertions.as;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.beans.Product;

public class ProductController {
	
	@Autowired
	ProductService ps;
	
	@GetMapping(value = "/products/{id}")
	public Product getActor(@PathVariable("id") int id) {
		return ps.getActor(id);
	}
	
	@GetMapping(value = "/products", produces = "application/json")
	public List<Product> getAllActors() {
		return as.getAllActors();
	}
	
	@GetMapping(value = "/products/search")
	public Product getActor(@RequestParam(required = true) String name) {
		return as.getActor(name);
	}
	
	@PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
	public Product addActor(@RequestBody Product a) {
		return as.addActor(a);
	}
	
	@PutMapping(value = "/products/{id}", consumes = "application/json")
	public Product updateActor(@PathVariable("id") int id, @RequestBody Product change) {
		change.setId(id);
		return as.updateActor(change);
	}
	
	@DeleteMapping(value = "/products/{id}")
	public boolean deleteActor(@PathVariable("id") int id) {
		return as.deleteActor(id);
	}


}
