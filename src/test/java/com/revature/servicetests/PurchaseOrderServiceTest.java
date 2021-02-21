/**
 * 
 */
package com.revature.servicetests;

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
import com.revature.beans.Product;
import com.revature.beans.PurchaseOrder;
import com.revature.beans.SupplierInvoice;
import com.revature.repositories.PurchaseOrderRepo;
import com.revature.services.DistributionInvoiceServiceImpl;
import com.revature.services.DistributorServiceImpl;
import com.revature.services.ProductServiceImpl;
import com.revature.services.PurchaseOrderServiceImpl;
import com.revature.services.SupplierInvoiceServiceImpl;

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
	
	@MockBean
	ProductServiceImpl productService;
	
	@MockBean
	SupplierInvoiceServiceImpl supplierService;
	
	@MockBean
	DistributionInvoiceServiceImpl distributorService;
	
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

		Date date = new Date(System.currentTimeMillis());
		SupplierInvoice si = new SupplierInvoice();
		DistributionInvoice di = new DistributionInvoice();
		PurchaseOrder purch_ord = new PurchaseOrder();
		purch_ords.add(purch_ord);
		PurchaseOrder purch_ord2 = new PurchaseOrder();
		purch_ords.add(purch_ord2);
		PurchaseOrder purch_ord3 = new PurchaseOrder("order_placed",date, date, date, "distributor", di, si);
		purch_ords.add(purch_ord3);
		
		when(repo.findById(3)).thenReturn(Optional.of(purch_ord3));
		
		Assert.assertEquals(purch_ord3, service.get(3));
	}
	
	@Test
	public void addPurchaseOrderTest() {
		PurchaseOrder purch_ord = new PurchaseOrder();
		
		when(repo.save(purch_ord)).thenReturn(purch_ord);
		
		Assert.assertEquals(purch_ord, service.add(purch_ord));
	}
	
	@Test
	public void updatePurchaseOrderTest() {
		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",null, null, null, "distributor", null, null);
		
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
	
	@Test
	public void getSupplierPurchaseOrderTest() {
		List<PurchaseOrder> purch_ords = new ArrayList<>();

		Date date = new Date(System.currentTimeMillis());
		SupplierInvoice si = new SupplierInvoice();
		DistributionInvoice di = new DistributionInvoice();
		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",date, date, date, "supplier", di, si);
		purch_ords.add(purch_ord);
		PurchaseOrder purch_ord2 = new PurchaseOrder("order_placed",date, date, date, "supplier", di, si);
		purch_ords.add(purch_ord2);
		PurchaseOrder purch_ord3 = new PurchaseOrder("order_placed",date, date, date, "distributor", di, si);
		purch_ords.add(purch_ord3);
		
		when(repo.findAll()).thenReturn(purch_ords);
		
		Assert.assertEquals(2, service.getSupplierPurchaseOrders().size());
	}
	
	@Test
	public void increaseInventoryWhenSuppOrderReceivedTest() {
		SupplierInvoice si = new SupplierInvoice();
		si.setProduct_id(1);
		si.setOrder_quantity(5);
		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",null, null, null, "distributor", null, si);
		Product product = new Product(1, "test", 22, 10, "raw");
		
		Product updatedProduct = new Product(1, "test", 22, 15, "raw");
		
		when(productService.getProduct(1)).thenReturn(product);
		when(productService.updateProduct(product)).thenReturn(updatedProduct);
		
		Assert.assertEquals(updatedProduct, service.increaseInventoryWhenSuppOrderReceived(purch_ord));
	}
//	
//	@Test
//	public void orderNeededRawGoodsTest() {
//		Date date = new Date(System.currentTimeMillis());
//		SupplierInvoice invoice = new SupplierInvoice();
//		invoice.setProduct_id(1);
//		invoice.setOrder_quantity(5);
//		invoice.setTotal_cost(110);
//		invoice.setDate_issued(date);
//		Product product = new Product(1, "test", 22, 10, "raw");
//		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",date, null, null, "supplier", null, invoice);
//		System.out.println(purch_ord);
//		
//		when(supplierService.addSupplierInvoice(invoice)).thenReturn(invoice);
//		when(distributorService.getDistributionInvoice(0)).thenReturn(null);
//		when(productService.getProduct(1)).thenReturn(product);
//		when(repo.save(purch_ord)).thenReturn(purch_ord);
//		
//		
//		Assert.assertEquals(purch_ord, service.orderNeededRawGoods(invoice));
//	}

}