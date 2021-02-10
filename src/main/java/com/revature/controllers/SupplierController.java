package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Supplier;
import com.revature.services.SupplierService;

@RestController
public class SupplierController {
	
	@Autowired
	SupplierService service;
	
	@GetMapping(value = "/supplier/{id}")
	public Supplier getSupplier(@PathVariable("id") String id) {
		return service.getSupplier(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/supplier", produces = "application/json")
	public List<Supplier> getAllSuppliers() {
		System.out.println("Getting all Suppliers");
		return service.getAllSuppliers();
	}
	
	@PostMapping(value = "/supplier", consumes = "application/json", produces = "application/json")
	public Supplier addSupplier(@RequestBody Supplier a) {
		return service.addSupplier(a);
	}
	
	@PutMapping(value = "/supplier/{id}", consumes = "application/json")
	public Supplier updateSupplier(@PathVariable("id") int id, @RequestBody Supplier change) {
		change.setId(id);
		return service.updateSupplier(change);
	}
	
	@DeleteMapping(value = "/Suppliers/{id}")
	public boolean deleteSupplier(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return service.deleteSupplier(id);
	}

}
