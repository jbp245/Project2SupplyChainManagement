package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.services.UserService;
import java.util.List;


@RestController
@CrossOrigin
//@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	UserService userservice;
	

	@GetMapping(value = "/users/{id}")
	public User getUser(@PathVariable("id") String id) {
		int usrid = Integer.parseInt(id);
		return userservice.getUser(usrid);
	}
	
	@GetMapping(value = "/users", produces = "application/json")
	public List<User> getAllUsers() {
		System.out.println("Getting all Actors");
		return userservice.getUsers();
	}
	
	@GetMapping(value = "/users/search")
	public List<User> getUserByRole(@RequestParam(required = true) String name) {
		return userservice.getRole(name);
	}
	
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public User addUser(@RequestBody User a) {
		return userservice.addUser(a);
	}
	
	@PutMapping(value = "/users/{id}", consumes = "application/json")
	public User updateUser(@PathVariable("id") int id, @RequestBody User change) {
		change.setId(id);
		return userservice.updateUser(change);
	}
	
	/*@PutMapping(value = "/users/{username, password}", consumes = "application/json")
	public boolean login(@PathVariable("username") String name, @PathVariable("password") String password) {
		return userservice.login(name, password);
	}*/
	
	
	
	//@Authorized
	@DeleteMapping(value = "/users/{id}")
	public boolean deleteUser(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return userservice.deleteUser(id);
	}
	
	
	@GetMapping(value = "/users/login/{name}/{password}", produces = "application/json")
	public List<User> login(@PathVariable("name") String name, @PathVariable("password") String password) {
		System.out.println("Executing Login");
		
		return userservice.login(name, password);
	}
	


}
