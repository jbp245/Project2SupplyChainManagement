/**
 * 
 */
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

import com.revature.beans.PurchaseOrder;
import com.revature.repositories.PurchaseOrderRepo;
import com.revature.services.PurchaseOrderServiceImpl;

/**
 * @author james
 *
 */
@SpringBootTest(classes = com.revature.demo.Project2Application.class)
public class PurchaseOrderServiceTest {

	@Autowired
	PurchaseOrderServiceImpl service;
	
	@MockBean
	PurchaseOrderRepo repo;
	
	@Test
	public void getPurchaseOrders() {
		List<PurchaseOrder> purch_ords = new ArrayList<>();

		PurchaseOrder purch_ord = new PurchaseOrder();
		purch_ords.add(purch_ord);
		PurchaseOrder purch_ord2 = new PurchaseOrder();
		purch_ords.add(purch_ord2);
		PurchaseOrder purch_ord3 = new PurchaseOrder();
		purch_ords.add(purch_ord3);
		
		
		when(repo.findAll()).thenReturn(purch_ords);
		
		Assert.assertEquals(3, service.getAll().size());
	}
	
	@Test
	public void getPurchaseOrderByIdTest() {
		List<PurchaseOrder> purch_ords = new ArrayList<>();

		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",null, null, null, "distributor", null, null);
		purch_ords.add(purch_ord);
		PurchaseOrder purch_ord2 = new PurchaseOrder();
		purch_ords.add(purch_ord2);
		PurchaseOrder purch_ord3 = new PurchaseOrder();
		purch_ords.add(purch_ord3);
		
		when(repo.findById(3)).thenReturn(Optional.of(purch_ord3));
		
		Assert.assertEquals(purch_ord3, service.getPurchaseOrderBySupplierId(3));
	}
	
	@Test
	public void addPurchaseOrderTest() {
		PurchaseOrder purch_ord = new PurchaseOrder();
		
		when(repo.save(purch_ord)).thenReturn(purch_ord);
		
		Assert.assertEquals(purch_ord, service.add(purch_ord));
	}
	
	@Test
	public void updatePurchaseOrderTest() {
		PurchaseOrder purch_ord = new PurchaseOrder();
		
		when(repo.save(purch_ord)).thenReturn(purch_ord);
		
		Assert.assertEquals(purch_ord, service.update(purch_ord));
	}
	
	@Test
	public void deletePurchaseOrderTest() {
		PurchaseOrder purch_ord = new PurchaseOrder();
		Optional<PurchaseOrder> invoice = Optional.of(purch_ord);
		
		when(repo.findById(purch_ord.getId())).thenReturn(invoice);
		Mockito.doNothing().when(repo).delete(purch_ord);
		Assert.assertTrue(service.delete(purch_ord.getId()));
	}
	
	@Test
	public void getFalseWhenErrorIsThrownDeleting() {

		PurchaseOrder purch_ord = new PurchaseOrder();
		Optional<PurchaseOrder> invoice = Optional.of(purch_ord);
		
		when(repo.findById(purch_ord.getId())).thenReturn(invoice);
		Mockito.doThrow(IllegalArgumentException.class).when(repo).delete(purch_ord);
		
		Assert.assertFalse(service.delete(purch_ord.getId()));
	}

}