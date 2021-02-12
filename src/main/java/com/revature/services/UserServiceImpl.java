package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userrepo;
	
	@Override
	public User getUser(int id) {
		return userrepo.findById(id).get();
	}
	
	/*public User getRole(String name) {
		return userrepo.findByRole_name(name);
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


