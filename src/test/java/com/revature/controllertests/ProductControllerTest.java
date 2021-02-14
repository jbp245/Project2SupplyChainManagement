package com.revature.controllertests;
/**
 * 
 */

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.beans.Product;
import com.revature.controllers.ProductController;
import com.revature.services.ProductServiceImpl;
 
/**
 * @author james
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProductServiceImpl service;
	
	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
	    
	    Product test = new Product("test_product", 1, 1);
	    System.out.println(test.toString());
	    
	    List<Product> allProducts = Arrays.asList(test);
	    System.out.println(allProducts.toString());
	    
	    given(service.getAllProducts()).willReturn(allProducts);
	    	
	    mvc.perform(get("/product")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$", hasSize(1)))
	      .andExpect(jsonPath("$[0].product_name", is(test.getProduct_name())));
	}
}

