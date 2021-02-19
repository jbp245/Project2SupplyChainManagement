package com.revature.servicetests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.beans.DistributionInvoice;
import com.revature.repositories.DistributionInvoiceRepo;
import com.revature.services.DistributionInvoiceServiceImpl;
import com.revature.services.DistributorServiceImpl;

@SpringBootTest(classes = com.revature.demo.Project2Application.class)
class DistributorInvoiceServiceTest {

	@Autowired
	DistributionInvoiceServiceImpl service;
	
	@MockBean
	DistributionInvoiceRepo repo;
	
	@Test
	public void getProductsTest() {
		List<DistributionInvoice> invoices = new ArrayList<>();
		Date date = new Date(System.currentTimeMillis());
		DistributionInvoice invoice1 = new DistributionInvoice(1, date, 2, 30, 1, 5);
		invoices.add(invoice1);
		DistributionInvoice invoice2 = new DistributionInvoice(2, date, 2, 30, 1, 5);
		invoices.add(invoice2);
		DistributionInvoice invoice3 = new DistributionInvoice(3, date, 2, 30, 1, 5);
		invoices.add(invoice3);
		
		
		when(repo.findAll()).thenReturn(invoices);
		
		Assert.assertEquals(3, service.getAllDistributionInvoices().size());
	}
	
	@Test
	public void getProductByIdTest() {
		List<DistributionInvoice> invoices = new ArrayList<>();
		Date date = new Date(System.currentTimeMillis());
		DistributionInvoice invoice1 = new DistributionInvoice(1, date, 2, 30, 1, 5);
		invoices.add(invoice1);
		DistributionInvoice invoice2 = new DistributionInvoice(2, date, 2, 30, 1, 5);
		invoices.add(invoice2);
		DistributionInvoice invoice3 = new DistributionInvoice(3, date, 2, 30, 1, 5);
		invoices.add(invoice3);
		
		
		when(repo.findById(3)).thenReturn(Optional.of(invoice3));
		
		Assert.assertEquals(invoice3, service.getDistributionInvoice(3));
	}
	
	@Test
	public void addProductTest() {
		Date date = new Date(System.currentTimeMillis());
		DistributionInvoice invoice1 = new DistributionInvoice(1, date, 2, 30, 1, 5);
		
		when(repo.save(invoice1)).thenReturn(invoice1);
		
		Assert.assertEquals(invoice1, service.addDistributionInvoice(invoice1));
	}
	
	@Test
	public void updateProductTest() {
		Date date = new Date(System.currentTimeMillis());
		DistributionInvoice invoice1 = new DistributionInvoice(1, date, 2, 30, 1, 5);
		
		when(repo.save(invoice1)).thenReturn(invoice1);
		
		Assert.assertEquals(invoice1, service.updateDistributionInvoice(invoice1));
	}
	
	@Test
	public void deleteProductTest() {
		Date date = new Date(System.currentTimeMillis());
		DistributionInvoice invoice1 = new DistributionInvoice(1, date, 2, 30, 1, 5);
		Optional<DistributionInvoice> invoice = Optional.of(invoice1);
		
		when(repo.findById(invoice1.getId())).thenReturn(invoice);
		Mockito.doNothing().when(repo).delete(invoice1);
		Assert.assertTrue(service.deleteDistributionInvoice(invoice1.getId()));
	}
	
	@Test
	public void getFalseWhenErrorIsThrownDeleting() {
		Date date = new Date(System.currentTimeMillis());
		DistributionInvoice invoice1 = new DistributionInvoice(1, date, 2, 30, 1, 5);
		Optional<DistributionInvoice> invoice = Optional.of(invoice1);
		
		when(repo.findById(invoice1.getId())).thenReturn(invoice);
		Mockito.doThrow(IllegalArgumentException.class).when(repo).delete(invoice1);
		
		Assert.assertFalse(service.deleteDistributionInvoice(invoice1.getId()));
	}

}
