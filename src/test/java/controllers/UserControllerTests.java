package controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.revature.beans.Product;
import com.revature.beans.User;
import com.revature.controllers.UserController;
import com.revature.services.UserServiceImpl;


@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.demo.Project2Application.class)
class UserControllerTests {
	@Autowired
	MockMvc mocMvc;
	
	@Autowired
    UserController usercontrol;
	
	@MockBean
	UserServiceImpl userservice;
	
	public static Gson gson = new Gson();
	
	@Test
	public void getAllUsers() throws Exception {
		User user1 = new User();
		User user2 = new User();
		List<User> users = new ArrayList<>();
		
		users.add(user1);
		users.add(user2);
		
		Mockito.when(userservice.getUsers()).thenReturn(users);
		
		ResultActions ra = mocMvc.perform(get("/users"));
		ra.andExpect(status().isOk());
	}
	
	@Test
	void getById() throws Exception {
		User user1 = new User();
		Mockito.when(userservice.getUser(1)).thenReturn(user1);
		
		mocMvc.perform(get("/users/1")).andExpect(status().isOk());
	}
	
	@Test
	void login() throws Exception {
		User user1 = new User();
		Mockito.when(userservice.login("distuser","distpass")).thenReturn(user1);
		
		mocMvc.perform(post("/users/login/distuser/distpass")).andExpect(status().isOk());
	}
	
	@Test
	void getRole() throws Exception {
		User user1 = new User();
		User user2 = new User();
		List<User> users = new ArrayList<>();
		
		users.add(user1);
		users.add(user2);
		
		Mockito.when(userservice.getRole("distuser")).thenReturn(users);
		
		mocMvc.perform(get("/users/search/distuser")).andExpect(status().isOk());
	}
	
	@Test
	void validateUser() throws Exception {
		User user1 = new User();
		
		
		Mockito.when(userservice.validateUser("distuser")).thenReturn(user1);
		
		mocMvc.perform(post("/users/validateuser/distuser")).andExpect(status().isOk());
	}
	
	
	
	@Test
	void addUser() throws Exception {
		User user1 = new User(1,"Admin", "adminuser", "adminpass",1);
		
		
		Mockito.when(userservice.addUser(user1)).thenReturn(user1);
		mocMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user1))).andExpect(status().isOk());
		
	}
	
	@Test
	void updateUser() throws Exception {
		User user1 = new User(1,"Admin", "adminuser", "adminpass",1);
		
		Mockito.when(userservice.updateUser(user1)).thenReturn(user1);
		mocMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user1))).andExpect(status().isOk());
	}
	
	@Test
	void deleteUser() throws Exception {
		User user1 = new User(1,"Admin", "adminuser", "adminpass",1);
		
		Mockito.when(userservice.deleteUser(1)).thenReturn(true);
		mocMvc.perform(delete("/users/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user1))).andExpect(status().isOk());
	}


}
