/**
 * 
 */
package com.revature.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.DistributionInvoice;
import com.revature.beans.Product;
import com.revature.beans.PurchaseOrder;
import com.revature.beans.SupplierInvoice;
import com.revature.services.DistributionInvoiceService;
import com.revature.services.ProductService;
import com.revature.services.PurchaseOrderService;
import com.revature.services.SupplierInvoiceService;

/**
 * @author james
 *
 */
@CrossOrigin
@RestController
public class PurchaseOrderController {
	
	@Autowired
	PurchaseOrderService pos;
	
	@Autowired
	DistributionInvoiceService dis;
	
	@Autowired
	ProductService ps;
	
	@Autowired
	SupplierInvoiceService sis;
	
	@GetMapping(value = "/purchaseorder/{id}")
	public PurchaseOrder getPurchaseOrder(@PathVariable("id") int id) { return pos.get(id); }
	
	@GetMapping(value = "/purchaseorder/search")
	public PurchaseOrder getPurchaseOrderById(@RequestParam(required = true) int id) { return pos.get(id); }
	
	@GetMapping(value = "/purchaseorder", produces = "application/json")
	public List<PurchaseOrder> getAllPurchaseOrders() { return pos.getAll(); }
	
	@PostMapping(value = "/purchaseorder", consumes = "application/json", produces = "application/json")
	public PurchaseOrder addPurchaseOrder(@RequestBody PurchaseOrder a) { return pos.add(a); }
	
	
	@PostMapping(value = "/purchaseorder/distributor_order", consumes = "application/json", produces = "application/json")
	public PurchaseOrder addDistributorPurchaseOrder(@RequestBody DistributionInvoice di) {
		Date date = new Date(System.currentTimeMillis());
		di.setDate_issued(date);
		
		Product product = ps.getProduct(di.getProduct_id());
		di.setTotal_cost(di.getOrder_quantity() * product.getProduct_cost());
		
		di = dis.addDistributionInvoice(di);
		
		PurchaseOrder po = new PurchaseOrder("order_placed", date, null, null, "distributor", di, sis.getSuppInvoice(0));
		if(pos.checkInventory(product.getId(), di.getOrder_quantity()).equals("enough in stock")) {
			po.setOrder_shipped_date(date);
			System.out.println("\n\nEnough in stock");
		}
		System.out.println(po);
		return pos.add(po);
	}
	
	@PostMapping(value = "/purchaseorder/supplier_order", consumes = "application/json", produces = "application/json")
	public PurchaseOrder addDistributorPurchaseOrder(@RequestBody SupplierInvoice si) {
		Date date = new Date(System.currentTimeMillis());
		si.setDate_issued(date);
		
		Product product = ps.getProduct(si.getProduct_id());
		si.setTotal_cost(si.getOrder_quantity() * product.getProduct_cost());
		
		si = sis.addSupplierInvoice(si);
		
		PurchaseOrder po = new PurchaseOrder("order_placed", date, null, null, "supplier", dis.getDistributionInvoice(0), si);
		System.out.println(po);
		return pos.add(po);
	}
	
	
	@PutMapping(value = "/purchaseorder/{id}", consumes = "application/json")
	public PurchaseOrder updatePurchaseOrder(@PathVariable("id") int id, @RequestBody PurchaseOrder change) { change.setId(id); return pos.update(change); }
	
	//@Authorized
	@DeleteMapping(value = "/purchaseorder/{id}")
	public boolean deletePurchaseOrder(@PathVariable("id") int id) { return pos.delete(id); }

}
