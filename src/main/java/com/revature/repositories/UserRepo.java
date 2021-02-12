package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
	
	//public User findByRole_name(String role_name);
	
	//public boolean findByNameAndPassword(String name, String password);

}
