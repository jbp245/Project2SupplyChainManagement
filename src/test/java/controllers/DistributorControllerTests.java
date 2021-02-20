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
import com.revature.beans.Distributor;
import com.revature.beans.Product;
import com.revature.beans.User;
import com.revature.beans.UserDistributor;
import com.revature.controllers.DistributorController;
import com.revature.services.DistributorServiceImpl;
import com.revature.services.UserServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.demo.Project2Application.class)
class DistributorControllerTests {
	@Autowired
	MockMvc mocMvc;
	
	@Autowired
	DistributorController distcontroller;
	
	@MockBean
	DistributorServiceImpl distservice;
	
	@MockBean
	UserServiceImpl userservice;
	
	@MockBean
	Distributor dsbtr;
	
	
	public static Gson gson = new Gson();
	
	@Test
	public void getAllDistributor() throws Exception {
		Distributor dist1 = new Distributor();
		Distributor dist2 = new Distributor();
		List<Distributor> distributor = new ArrayList<>();
		
		distributor.add(dist1);
		distributor.add(dist2);
		
		Mockito.when(distservice.getAllDistributors()).thenReturn(distributor);
		
		ResultActions ra = mocMvc.perform(get("/distributor"));
		ra.andExpect(status().isOk());
	}
	
	
	@Test
	void getById() throws Exception {
		Distributor distdetail = new Distributor();
		Mockito.when(distservice.getDistributor(1)).thenReturn(distdetail);
		
		mocMvc.perform(get("/distributor/1")).andExpect(status().isOk());
	}
	
	@Test
	void updateProduct() throws Exception {
		Distributor distdetail = new Distributor(1,"BJs", "New Jersey", "4567891234");
		
		Mockito.when(distservice.updateDistributor(distdetail)).thenReturn(distdetail);
		mocMvc.perform(put("/distributor/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(distdetail))).andExpect(status().isOk());
	}
	
	@Test
	void deleteProduct() throws Exception {
		Distributor distdetail = new Distributor(1,"BJs", "New Jersey", "4567891234");
		
		Mockito.when(distservice.deleteDistributor(1)).thenReturn(true);
		mocMvc.perform(delete("/distributor/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(distdetail))).andExpect(status().isOk());
	}
	
	@Test
	void addUserDistributor() throws Exception {
         User user = new User( "Distributor","distuser","distpass", 1);
		
		
		Mockito.when(userservice.addUser(user)).thenReturn(user);
		mocMvc.perform(post("/distributor").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user))).andExpect(status().isOk());
		
		Distributor distributor = new Distributor(1,"BJs", "New Jersey", "4567891234");
		
		
		Mockito.when(distservice.addDistributor(distributor)).thenReturn(distributor);
		mocMvc.perform(post("/distributor").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(distributor))).andExpect(status().isOk());
		
	}
	

	

}
