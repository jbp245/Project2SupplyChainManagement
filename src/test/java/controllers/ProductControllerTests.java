package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
	
	@Test
	public void getAllProduct() throws Exception {
		mocMvc.perform(get("/product")).andExpect(status().isOk());
	}
	
	@Test
	void getById() throws Exception {
		mocMvc.perform(get("/product/1")).andExpect(status().isOk());
	}
	
	@Test
	void getRawProducts() throws Exception {
		mocMvc.perform(get("/rawproduct")).andExpect(status().isOk());
	}
	
	@Test
	void makeNewProduct() {
		
	}

}
