package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
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
import org.springframework.test.web.servlet.ResultMatcher;

import com.google.gson.Gson;
import com.revature.beans.Product;
import com.revature.controllers.ProductController;
import com.revature.services.ProductServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.demo.Project2Application.class)
class ProductControllerTests {

	@Autowired
	MockMvc mocMvc;
	
	@Autowired
	ProductController controller;
	
	@MockBean
	ProductServiceImpl service;
	
	public static Gson gson = new Gson();
	
	@Test
	public void getAllProduct() throws Exception {
		Product p1 = new Product();
		Product p2 = new Product();
		List<Product> products = new ArrayList<>();
		
		products.add(p1);
		products.add(p2);
		
		Mockito.when(service.getAllProducts()).thenReturn(products);
		
//		mocMvc.perform(get("/product")).andExpect(status().isOk());
		ResultActions ra = mocMvc.perform(get("/product"));
		ra.andExpect(status().isOk());
	}
	
	@Test
	void getById() throws Exception {
		Product p1 = new Product();
		Mockito.when(service.getProduct(1)).thenReturn(p1);
		
		mocMvc.perform(get("/product/1")).andExpect(status().isOk());
	}
	
	@Test
	void getRawProducts() throws Exception {
		Product p1 = new Product();
		Product p2 = new Product();
		List<Product> products = new ArrayList<>();
		
		products.add(p1);
		products.add(p2);
		
		Mockito.when(service.getAllRawProducts()).thenReturn(products);
		
		mocMvc.perform(get("/rawproduct")).andExpect(status().isOk());
	}
	
	@Test
	void makeNewProduct() throws Exception {
		Product p1 = new Product(1, "corn", 5, 20, "raw");
		
		
		Mockito.when(service.addProduct(p1)).thenReturn(p1);
		mocMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(p1))).andExpect(status().isOk());
		
	}
	
	@Test
	void updateProduct() throws Exception {
		Product p1 = new Product(1, "corn", 5, 20, "raw");
		
		Mockito.when(service.updateProduct(p1)).thenReturn(p1);
		mocMvc.perform(put("/product/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(p1))).andExpect(status().isOk());
	}
	
	@Test
	void deleteProduct() throws Exception {
		Product p1 = new Product(1, "corn", 5, 20, "raw");
		
		Mockito.when(service.deleteProduct(1)).thenReturn(true);
		mocMvc.perform(delete("/product/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(p1))).andExpect(status().isOk());
	}

}
