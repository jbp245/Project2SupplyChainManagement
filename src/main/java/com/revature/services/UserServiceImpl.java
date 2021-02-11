package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.User;
import com.revature.repositories.UserRepo;

public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userrepo;
	
	@Override
	public User getUser(int id) {
		return userrepo.findById(id).get();
	}
	
	/*public User getRole(String name) {
		return userrepo.findByRole(name);
	}*/

	public List<User> getUsers(){
		return (List<User>) userrepo.findAll();
	}
	
	public User addUser(User user) {
		return userrepo.save(user);
	}
	
	public User updateUser(User change) {
		return userrepo.save(change); 
	}
	
	public boolean deleteUser(int id) {
		try {
			userrepo.delete(userrepo.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}
	/*public boolean login(String name, String password) {
		boolean result = userrepo.findByNameAndPassword(name, password);
		return result;
		
	}*/

	}


