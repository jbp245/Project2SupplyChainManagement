package com.revature.controllertests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
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

import com.google.gson.Gson;
import com.revature.beans.DistributionInvoice;
import com.revature.beans.Product;
import com.revature.beans.PurchaseOrder;
import com.revature.beans.SupplierInvoice;
import com.revature.controllers.PurchaseOrderController;
import com.revature.services.PurchaseOrderServiceImpl;
import com.revature.services.SupplierInvoiceServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.demo.Project2Application.class)
class PurchaseOrderControllerTests {

	@Autowired
	MockMvc mocMvc;
	
	@Autowired
	PurchaseOrderController controller;
	
	@MockBean
	PurchaseOrderServiceImpl service;
	
	@MockBean
	SupplierInvoiceServiceImpl siService;
	
	public static Gson gson = new Gson();
	
	@Test
	public void getAllOrders() throws Exception {
		PurchaseOrder p1 = new PurchaseOrder();
		PurchaseOrder p2 = new PurchaseOrder();
		List<PurchaseOrder> orders = new ArrayList<>();
		
		orders.add(p1);
		orders.add(p2);
		
		Mockito.when(service.getAll()).thenReturn(orders);
		
//		mocMvc.perform(get("/product")).andExpect(status().isOk());
		ResultActions ra = mocMvc.perform(get("/purchaseorder"));
		ra.andExpect(status().isOk());
	}
	
	@Test
	public void getAllSupplierOrders() throws Exception {
		PurchaseOrder p1 = new PurchaseOrder();
		PurchaseOrder p2 = new PurchaseOrder();
		List<PurchaseOrder> orders = new ArrayList<>();
		
		orders.add(p1);
		orders.add(p2);
		
		Mockito.when(service.getSupplierPurchaseOrders()).thenReturn(orders);
		
//		mocMvc.perform(get("/product")).andExpect(status().isOk());
		ResultActions ra = mocMvc.perform(get("/purchaseorder/supplierPurchaseOrders"));
		ra.andExpect(status().isOk());
	}
	
	@Test
	public void getAllDistributorOrdersByID() throws Exception {
		PurchaseOrder p1 = new PurchaseOrder();
		PurchaseOrder p2 = new PurchaseOrder();
		List<PurchaseOrder> orders = new ArrayList<>();
		
		orders.add(p1);
		orders.add(p2);
		
		Mockito.when(service.getPurchaseOrderBySupplierId(0)).thenReturn(orders);
		
//		mocMvc.perform(get("/product")).andExpect(status().isOk());
		ResultActions ra = mocMvc.perform(get("/purchaseorder/distributor/0"));
		ra.andExpect(status().isOk());
	}
	
	@Test
	void getById() throws Exception {
		PurchaseOrder p1 = new PurchaseOrder();
		Mockito.when(service.get(1)).thenReturn(p1);
		
		mocMvc.perform(get("/purchaseorder/1")).andExpect(status().isOk());
	}
	
//	@Test
//	void getBySearch() throws Exception {
//		PurchaseOrder p1 = new PurchaseOrder();
//		Mockito.when(service.get(1)).thenReturn(p1);
//		
//		mocMvc.perform(get("/purchaseorder/search").param("id", 1).andExpect(status().isOk());
//	}
	
	@Test
	void makeNewOrder() throws Exception {
		Date date = new Date(System.currentTimeMillis());
		SupplierInvoice si = new SupplierInvoice();
		DistributionInvoice di = new DistributionInvoice();
		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",null, null, null, "supplier", di, si);
		
		
		Mockito.when(service.add(purch_ord)).thenReturn(purch_ord);
		mocMvc.perform(post("/purchaseorder").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(purch_ord))).andExpect(status().isOk());
		
	}
	
//	@Test
//	void makeNewDistributorOrder() throws Exception {
//		Date date = new Date(System.currentTimeMillis());
//		SupplierInvoice si = new SupplierInvoice();
//		DistributionInvoice di = new DistributionInvoice();
//		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",date, null, null, "supplier", di, si);
//		
//		Mockito.when(service.checkInventory(0, 0)).thenReturn("orders made");
//		Mockito.when(service.add(purch_ord)).thenReturn(purch_ord);
//		Mockito.when(siService.getSuppInvoice(0))
//		mocMvc.perform(post("purchaseorder/distributor_order").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(purch_ord))).andExpect(status().isOk());
//		
//	}
	
	@Test
	void updateProduct() throws Exception {
		SupplierInvoice si = new SupplierInvoice();
		DistributionInvoice di = new DistributionInvoice();
		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",null, null, null, "supplier", di, si);
		
		Mockito.when(service.update(purch_ord)).thenReturn(purch_ord);
		mocMvc.perform(put("/purchaseorder/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(purch_ord))).andExpect(status().isOk());
	}
	
	@Test
	void deleteProduct() throws Exception {
		SupplierInvoice si = new SupplierInvoice();
		DistributionInvoice di = new DistributionInvoice();
		PurchaseOrder purch_ord = new PurchaseOrder("order_placed",null, null, null, "supplier", di, si);
		
		Mockito.when(service.delete(1)).thenReturn(true);
		mocMvc.perform(delete("/purchaseorder/1")).andExpect(status().isOk());
	}
	
}
