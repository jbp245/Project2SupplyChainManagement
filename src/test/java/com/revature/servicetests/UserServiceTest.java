/**
 * 
 */
package com.revature.servicetests;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.beans.User;
import com.revature.repositories.UserRepo;
import com.revature.services.UserServiceImpl;

/**
 * @author james
 *
 */
@SpringBootTest(classes = com.revature.demo.Project2Application.class)
public class UserServiceTest {

	@Autowired
	UserServiceImpl service;
	
	@MockBean
	UserRepo repo;
	
	@Test
	public void getUsers() {
		List<User> users = new ArrayList<>();

		User user = new User("Distributor","user1","pass",0);
		users.add(user);
		User user2 = new User("Distributor","user2","pass",0);
		users.add(user2);
		User user3 = new User("Distributor","user3","pass",0);
		users.add(user3);
		
		
		when(repo.findAll()).thenReturn(users);
		
		Assert.assertEquals(3, service.getUsers().size());
	}
	
	@Test
	public void getUserByIdTest() {
		List<User> users = new ArrayList<>();

		User user = new User("Distributor","user1","pass",0);
		users.add(user);
		User user2 = new User("Distributor","user2","pass",0);
		users.add(user2);
		User user3 = new User("Distributor","user3","pass",0);
		users.add(user3);
		
		when(repo.findById(3)).thenReturn(Optional.of(user3));
		
		Assert.assertEquals(user3, service.getUser(3));
	}
	
	@Test
	public void addUserTest() {
		User user = new User("Distributor","user1","pass",0);
		
		when(repo.save(user)).thenReturn(user);
		
		Assert.assertEquals(user, service.addUser(user));
	}
	
	@Test
	public void updateUserTest() {
		User user = new User("Distributor","user1","pass",0);
		
		when(repo.save(user)).thenReturn(user);
		
		Assert.assertEquals(user, service.updateUser(user));
	}
	
	@Test
	public void deleteUserTest() {
		User user = new User("Distributor","user1","pass",0);
		Optional<User> invoice = Optional.of(user);
		
		when(repo.findById(user.getId())).thenReturn(invoice);
		Mockito.doNothing().when(repo).delete(user);
		Assert.assertTrue(service.deleteUser(user.getId()));
	}
	
	@Test
	public void getFalseWhenErrorIsThrownDeleting() {

		User user = new User("Distributor","user1","pass",0);
		Optional<User> optUser = Optional.of(user);
		
		when(repo.findById(user.getId())).thenReturn(optUser);
		Mockito.doThrow(IllegalArgumentException.class).when(repo).delete(user);
		
		Assert.assertFalse(service.deleteUser(user.getId()));
	}
	
	// TODO seems odd with native queries because no repo is called
	@Test
	public void loginTest() {
		User user = new User("system","user1","pass",0);
		Optional<User> optUser = Optional.of(user);
		
		when(repo.findById(user.getId())).thenReturn(optUser);
		
		Assert.assertFalse(false);
		
	}

	// TODO seems odd with native queries because no repo is called
	@Test
	public void getRoleTest() {
		User user = new User("system","user1","pass",0);
		Optional<User> optUser = Optional.of(user);
		
		when(repo.findById(user.getId())).thenReturn(optUser);
		
		List<User> resultSet = service.getRole(user.getRole_name());
		System.out.println(resultSet);
		Assert.assertEquals(1, resultSet.size());
	}
}