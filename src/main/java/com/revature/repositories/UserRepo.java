package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	/*@Query(value="SELECT u.username from users u where u.role_name=?1", nativeQuery=true)
	public List<User> findByRole_name(String role_name);*/
	
	//public boolean findByNameAndPassword(String name, String password);

}
