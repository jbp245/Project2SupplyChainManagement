package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Distributor;
import com.revature.services.DistributorService;

@RestController
public class DistributorController {

	@Autowired
	private DistributorService ds;
	
	@GetMapping(value = "/distributor/{id}", produces = "application/json")
	public Distributor getDistributor(@PathVariable("id") int id) {
		return ds.getDistributor(id);
	}
	
	@GetMapping(value = "/distributor", produces = "application/json")
	public List<Distributor> getDistributor() {
		return ds.getAllDistributors();
	}
	
	@PostMapping(value = "/distributor", consumes = "application/json", produces = "application/json")
	public Distributor addDistributor(@RequestBody Distributor d) {
		return ds.addDistributor(d);
	}
	
	@PutMapping(value = "/distributor/{id}", consumes = "application/json")
	public Distributor updateDistributor(@PathVariable int id, @RequestBody Distributor update) {
		update.setId(id);
		return ds.updateDistributor(update);
	}
	
}
