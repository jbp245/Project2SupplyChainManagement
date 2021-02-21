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

import com.revature.beans.Distributor;
import com.revature.beans.User;
import com.revature.beans.UserDistributor;
import com.revature.services.DistributorService;
import com.revature.services.UserService;

@CrossOrigin
@RestController
public class DistributorController {

	@Autowired
	private DistributorService ds;
	
	@Autowired
	private UserService userservice;
	
	@GetMapping(value = "/distributor/{id}", produces = "application/json")
	public Distributor getDistributor(@PathVariable("id") int id) {
		return ds.getDistributor(id);
	}
	
	@GetMapping(value = "/distributor", produces = "application/json")
	public List<Distributor> getDistributor() {
		System.out.println("Inside getDistributor");
		System.out.println(ds.getAllDistributors());
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
	
	@DeleteMapping(value = "/distributor/{id}")
	public boolean deleteProduct(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return ds.deleteDistributor(id);
	}
	
	@PostMapping(value = "/users/adduser", consumes = "application/json", produces = "application/json")
	public User addUserDistributor(@RequestBody UserDistributor userdist) {
		
		System.out.println("From Frontend: " +userdist);
		
		String rolename = userdist.getRole_name();
		String username = userdist.getUsername();
		String userpass = userdist.getPass();
		String distname = userdist.getName();
		String address = userdist.getAddress();
		String phonenum = userdist.getPhone_number();
		
		Distributor distributor = new Distributor(distname, address, phonenum);
		distributor = ds.addDistributor(distributor);
		int dist_id = distributor.getId();
		
		
		User user = new User(rolename,username,userpass,dist_id);
		user = userservice.addUser(user);
		
		
		return user;
		
	}
	
	
	
}
