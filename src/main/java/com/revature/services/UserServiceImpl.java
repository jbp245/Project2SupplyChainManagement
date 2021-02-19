package com.revature.services;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Distributor;
import com.revature.beans.User;
import com.revature.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userrepo;
	
	
	@PersistenceContext
	public EntityManager em;
	
	@Override
	public User getUser(int id) {
		return userrepo.findById(id).get();
	}
	
	public List<User> getRole(String name) {


		String query = " SELECT u.username from users u where u.role_name='"+name+"'";
		  Query q = em.createNativeQuery(query);
		    List<User> result = q.getResultList();
		    System.out.println("result: " +result.size());
		    System.out.println("result: " +result);
		   return result;
		   
		//return userrepo.findByRole_name(name);
	}

	public List<User> getUsers(){
		return (List<User>) userrepo.findAll();
	}
	
	public User addUser(User user) {
		System.out.println("Inside Add User");
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
	public User login(String name, String password) {
				
		String query = "SELECT u.id, u.role_name, u.username, u.pass, u.distributor_id  from users u where u.username='"+name+"' and u.pass='" +password+ "'";
		System.out.println("Query String: "+query);
		  Query q = em.createNativeQuery(query, User.class);
		  System.out.println("Resultset  : "+q.getResultList());
		  if((q.getResultList().size()) > 0) {
		  User user = (User) q.getResultList().get(0);
		   System.out.println("count: " +user.getId());
		   return user;
		  }
		  
		 else {
			  return null;
		 }
		
	}
	
	public User validateUser(String username) {
		String query = "SELECT * FROM USERS u where u.username='"+username+"'";
		System.out.println("Query String : "+query);
		Query q = em.createNativeQuery(query, User.class);
		  System.out.println("Resultset  : "+q.getResultList());
		  if((q.getResultList().size()) > 0) {
		  User user = (User) q.getResultList().get(0);
		  return user;
		  }
		  else {
			  return null;
		  }
	}
	
}


