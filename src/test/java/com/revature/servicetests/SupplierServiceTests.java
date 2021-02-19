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

import com.revature.beans.Product;
import com.revature.beans.Supplier;
import com.revature.repositories.SupplierRepository;
import com.revature.services.SupplierServiceImpl;

@SpringBootTest(classes = com.revature.demo.Project2Application.class)
public class SupplierServiceTests {
	
	@Autowired
	private SupplierServiceImpl service;
	
	@MockBean
	private SupplierRepository repo;

	@Test
	public void addSupplier() {
		
		Supplier supplier = new Supplier(1, "supplier1", "address", "phone", 88);
		when(repo.save(supplier)).thenReturn(supplier);
		Assert.assertEquals(supplier, service.addSupplier(supplier));
			
	}
	
	@Test
	public void getSupplier() {
		
		Supplier supplier = new Supplier(1, "supplier1", "address", "phone", 88);
		when(repo.findById(1)).thenReturn(Optional.of(supplier));
		Supplier returnedSupplier = service.getSupplier(1);
		Assert.assertEquals(supplier.getId(), returnedSupplier.getId());
	}
	
	@Test
	public void getAllSuppliers(){

		List<Supplier> suppliers = new ArrayList<>();
		Supplier supplier1 = new Supplier(1, "supplier1", "address", "phone", 88);
		suppliers.add(supplier1);
		Supplier supplier2 = new Supplier(2, "supplier2", "address", "phone", 88);
		suppliers.add(supplier2);
		when(repo.findAll()).thenReturn(suppliers);
		Assert.assertEquals(2, service.getAllSuppliers().size());
	}
	
	@Test
	public void updateSupplier() {

		Supplier supplier1 = new Supplier(1, "supplier1", "address", "phone", 88);
		when(repo.save(supplier1)).thenReturn(supplier1);
		Assert.assertEquals(supplier1, service.updateSupplier(supplier1));
	}
	
	@Test
	public void deleteSupplier() {
		
		Supplier supplier1 = new Supplier(1, "supplier1", "address", "phone", 88);
		Optional<Supplier> supplier = Optional.of(supplier1);
		
		when(repo.findById(supplier1.getId())).thenReturn(supplier);
		Mockito.doNothing().when(repo).delete(supplier1);
		Assert.assertTrue(service.deleteSupplier(supplier1.getId()));
		
	}

}
