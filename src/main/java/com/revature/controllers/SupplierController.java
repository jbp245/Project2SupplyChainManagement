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
	SupplierService supplierservice;
	
	@GetMapping(value = "/supplier/{id}")
	public Supplier getSupplier(@PathVariable("id") String id) {
		return supplierservice.getSupplier(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/supplier", produces = "application/json")
	public List<Supplier> getAllSuppliers() {
		System.out.println("Getting all Suppliers");
		return supplierservice.getAllSuppliers();
	}
	
	@GetMapping(value = "/supplier/search")
	public List<Supplier> getSupplierByName(@RequestParam(required = true) String name) {
		return supplierservice.getSupplier(name);
	}
	
	@PostMapping(value = "/supplier", consumes = "application/json", produces = "application/json")
	public Supplier addSupplier(@RequestBody Supplier supplier) {
		return supplierservice.addSupplier(supplier);
	}
	
	@PutMapping(value = "/supplier/{id}", consumes = "application/json")
	public Supplier updateSupplier(@PathVariable("id") int id, @RequestBody Supplier change) {
		change.setId(id);
		return supplierservice.updateSupplier(change);
	}
	
	//@Authorized
	@DeleteMapping(value = "/supplier/{id}")
	public boolean deleteSupplier(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return supplierservice.deleteSupplier(id);
	}

}
