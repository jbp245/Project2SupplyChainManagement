package com.revature.servicetests;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.beans.Product;
import com.revature.beans.SupplierInvoice;
import com.revature.repositories.SupplierInvoiceRepo;
import com.revature.services.SupplierInvoiceServiceImpl;

@SpringBootTest(classes = com.revature.demo.Project2Application.class)
public class SupplierInvoiceServiceTests {
	
	@Autowired
	private SupplierInvoiceServiceImpl service;
	
	@MockBean
	private SupplierInvoiceRepo repo;

	@Test
	public void getSuppInvoice() {

		List<SupplierInvoice> invoices = new ArrayList<>();
		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, new Date(0));
		invoices.add(invoice1);
		SupplierInvoice invoice2 = new SupplierInvoice(2, 3, 4, 5, 6, 7.6, new Date(1));
		invoices.add(invoice2);
		when(repo.findById(2)).thenReturn(Optional.of(invoice2));
		
		SupplierInvoice returned_invoice = service.getSuppInvoice(2);
		Assert.assertEquals(invoice2.getId(), returned_invoice.getId());
	}
	@Test
	public void getAllSuppInvoice() {

		List<SupplierInvoice> invoices = new ArrayList<>();
		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, new Date(0));
		invoices.add(invoice1);
		SupplierInvoice invoice2 = new SupplierInvoice(2, 3, 4, 5, 6, 7.6, new Date(1));
		invoices.add(invoice2);
		
		when(repo.findAll()).thenReturn(invoices);
		Assert.assertEquals(2, service.getAllSuppInvoice().size());
	}
	@Test
	public void addSupplierInvoice() {

		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, new Date(0));
		when(repo.save(invoice1)).thenReturn(invoice1);
		Assert.assertEquals(invoice1, service.addSupplierInvoice(invoice1));
	}
	@Test
	public void updateSupplierInvoice() {

		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, new Date(0));
		when(repo.save(invoice1)).thenReturn(invoice1);
		Assert.assertEquals(invoice1, service.updateSupplierInvoice(invoice1));
	}
	@Test
	public void deleteSupplierInvoice() {
		
		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, new Date(0));
		Optional<SupplierInvoice> invoice = Optional.of(invoice1);
		when(repo.findById(invoice1.getId())).thenReturn(invoice);
		Mockito.doNothing().when(repo).delete(invoice1);
		Assert.assertTrue(service.deleteSupplierInvoice(invoice1.getId()));
	}

}
