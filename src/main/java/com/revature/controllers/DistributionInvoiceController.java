package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.DistributionInvoice;
import com.revature.services.DistributionInvoiceService;

@CrossOrigin
@RestController
public class DistributionInvoiceController {

	@Autowired
	private DistributionInvoiceService dis;
	
	@GetMapping(value = "/distributionInvoice/{id}", produces = "application/json")
	public DistributionInvoice getDistributionInvoice(@PathVariable("id") int id) {
		return dis.getDistributionInvoice(id);
	}
	
	@GetMapping(value = "/distributionInvoice", produces = "application/json")
	public List<DistributionInvoice> getAllDistributionInvoice(){
		return dis.getAllDistributionInvoices();
	}
	
	@PostMapping(value = "/distributionInvoice", consumes = "application/json", produces = "application/json")
	public DistributionInvoice addDistributionInvoice(@RequestBody DistributionInvoice d) {
		return dis.addDistributionInvoice(d);
	}
	
	@PutMapping(value = "/distributionInvoice/{id}", consumes = "application/json", produces = "application/json")
	public DistributionInvoice updateDistributionInvoice(@PathVariable("id") int id, @RequestBody DistributionInvoice d) {
		d.setId(id);
		return dis.updateDistributionInvoice(d);
	}
	
	@DeleteMapping(value = "/distributionInvoice/{id}")
	public boolean deleteDistributionInvoice(@PathVariable("id") int id) {
		return dis.deleteDistributionInvoice(id);
	}
	
}
