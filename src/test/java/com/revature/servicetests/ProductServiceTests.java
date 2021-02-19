package com.revature.servicetests;

import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Product;
import com.revature.repositories.ProductRepository;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;

@SpringBootTest(classes = com.revature.demo.Project2Application.class)
public class ProductServiceTests {

	@Autowired
	private ProductServiceImpl service;
	
	@MockBean
	private ProductRepository repo;
	
	@Test
	public void getProductsTest() {
		List<Product> products = new ArrayList<>();
		Product product1 = new Product(1, "corn", 5, 20, "raw");
		products.add(product1);
		Product product2 = new Product(3, "beans", 5, 20, "raw");
		products.add(product2);
		Product product3 = new Product(4, "cereal", 5, 20, "finished");
		products.add(product3);
		
		
		when(repo.findAll()).thenReturn(products);
		
		Assert.assertEquals(3, service.getAllProducts().size());
	}
	
	@Test
	public void getProductByIdTest() {
		List<Product> products = new ArrayList<>();
		Product product1 = new Product(1, "corn", 5, 20, "raw");
		products.add(product1);
		Product product2 = new Product(3, "beans", 5, 20, "raw");
		products.add(product2);
		Product product3 = new Product(4, "cereal", 5, 20, "finished");
		products.add(product3);
		
		
		when(repo.findById(3)).thenReturn(Optional.of(product2));
		
		Product returned_product = service.getProduct(3);
		
		Assert.assertEquals(product2.getId(), returned_product.getId());
		Assert.assertEquals(product2.getProduct_name(), returned_product.getProduct_name());
	}
	
	@Test
	public void getAllRawProducts() {
		List<Product> products = new ArrayList<>();
		Product product1 = new Product(1, "corn", 5, 20, "raw");
		products.add(product1);
		Product product2 = new Product(3, "beans", 5, 20, "raw");
		products.add(product2);
		Product product3 = new Product(4, "cereal", 5, 20, "finished");
		products.add(product3);
		
		
		when(repo.findAll()).thenReturn(products);
		Assert.assertEquals(2, service.getAllRawProducts().size());
	}
	
	@Test
	public void addProductTest() {
		Product product1 = new Product(1, "corn", 5, 20, "raw");
		
		when(repo.save(product1)).thenReturn(product1);
		
		Assert.assertEquals(product1, service.addProduct(product1));
	}
	
	@Test
	public void updateProductTest() {
		Product product1 = new Product(1, "corn", 5, 20, "raw");
		when(repo.save(product1)).thenReturn(product1);
		
		Assert.assertEquals(product1, service.updateProduct(product1));
	}
	
	@Test
	public void deleteProductTest() {
		Product product1 = new Product(1, "corn", 5, 20, "raw");
		Optional<Product> product = Optional.of(product1);
		
		when(repo.findById(product1.getId())).thenReturn(product);
		Mockito.doNothing().when(repo).delete(product1);
		Assert.assertTrue(service.deleteProduct(product1.getId()));
	}
	
	@Test
	public void getFalseWhenErrorIsThrownDeleting() {
		Product product1 = new Product(1, "corn", 5, 20, "raw");
		Optional<Product> product = Optional.of(product1);
		
		when(repo.findById(product1.getId())).thenReturn(product);
		Mockito.doThrow(IllegalArgumentException.class).when(repo).delete(product1);
		
		Assert.assertFalse(service.deleteProduct(product1.getId()));
	}
	
}
