package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.beans.User;

public interface UserRepo extends CrudRepository<User, Integer>{
	
	//public User findByRole(String name);
	
	//public boolean findByNameAndPassword(String name, String password);

}
