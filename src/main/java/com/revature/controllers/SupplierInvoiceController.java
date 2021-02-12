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

import com.revature.beans.SupplierInvoice;
import com.revature.services.SupplierInvoiceService;

@RestController
public class SupplierInvoiceController {
	

	@Autowired
	SupplierInvoiceService suppinvservice;
	
	@GetMapping(value = "/supplier_invoice/{id}")
	public SupplierInvoice getSuppInvoice(@PathVariable("id") String id) {
		return suppinvservice.getSuppInvoice(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/supplier_invoice", produces = "application/json")
	public List<SupplierInvoice> getAllActors() {
		System.out.println("Getting all Actors");
		return suppinvservice.getAllSuppInvoice();
	}
	
	/*@GetMapping(value = "/supplier_invoice/search")
	public List<SupplierInvoice> getSuppInvByProduct(@RequestParam(required = true) int prod_id) {
		return suppinvservice.getSuppInvByProduct(prod_id);
	}*/
	
	@PostMapping(value = "/supplier_invoice", consumes = "application/json", produces = "application/json")
	public SupplierInvoice addSupplierInvoice(@RequestBody SupplierInvoice suppinvoice) {
		return suppinvservice.addSupplierInvoice(suppinvoice);
	}
	
	@PutMapping(value = "/supplier_invoice/{id}", consumes = "application/json")
	public SupplierInvoice updateSupplierInvoicer(@PathVariable("id") int id, @RequestBody SupplierInvoice change) {
		change.setId(id);
		return suppinvservice.updateSupplierInvoice(change);
	}
	
	//@Authorized
	@DeleteMapping(value = "/supplier_invoice/{id}")
	public boolean deleteSupplierInvoice(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return suppinvservice.deleteSupplierInvoice(id);
	}

}
