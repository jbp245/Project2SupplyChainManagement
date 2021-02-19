package com.revature.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;

import com.revature.beans.Supplier;
import com.revature.services.SupplierService;

@SpringBootTest(classes = com.revature.demo.Project2Application.class)
@AutoConfigureMockMvc
public class SupplierControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SupplierService service;
	
	static private Gson gson = new Gson();
	
	@Test
	public void getSupplers() throws Exception {
		mockMvc.perform(get("/supplier"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getSupplierById() throws Exception {
		Supplier supplier = new Supplier(1, "supplier1", "address", "phone", 88);

		Mockito.when(service.getSupplier(1)).thenReturn(supplier);
		
		mockMvc.perform(get("/supplier/1"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void addSuppler() throws Exception {
		Supplier supplier = new Supplier(1, "supplier1", "address", "phone", 88);
		
		Mockito.when(service.addSupplier(supplier)).thenReturn(supplier);
		
		mockMvc.perform(post("/supplier")
		.contentType(MediaType.APPLICATION_JSON)
		.content(gson.toJson(supplier)))
		.andExpect(status().isOk());
	}

	@Test
	public void updateSuppler() throws Exception {
		
		Supplier supplier = new Supplier(1, "supplier1", "address", "phone", 88);
		
		Mockito.when(service.updateSupplier(supplier)).thenReturn(supplier);
		
		mockMvc.perform(put("/supplier/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(gson.toJson(supplier)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void deleteSuppler() throws Exception {
		
		Supplier supplier = new Supplier(1, "supplier1", "address", "phone", 88);
		
		Mockito.when(service.deleteSupplier(1)).thenReturn(true);
		
		mockMvc.perform(delete("/supplier/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(gson.toJson(supplier)))
		.andExpect(status().isOk());
	}
}
