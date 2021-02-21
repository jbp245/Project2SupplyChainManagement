/**
 * 
 */
package com.revature.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.DistributionInvoice;
import com.revature.beans.Product;
import com.revature.beans.PurchaseOrder;
import com.revature.beans.SupplierInvoice;
import com.revature.repositories.PurchaseOrderRepo;


/**
 * @author james
 *
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	@Autowired
	PurchaseOrderRepo por;
	
	@Autowired
	ProductService ps;
	
	@Autowired
	SupplierInvoiceService sis;
	
	@Autowired
	DistributionInvoiceService dis;

	@Override
	public PurchaseOrder get(int id) {
		return por.findById(id).get();
	}

	@Override
	public List<PurchaseOrder> getAll() {
		return (List<PurchaseOrder>) por.findAll();
	}
	
	public List<PurchaseOrder> getSupplierPurchaseOrders(){
		List<PurchaseOrder> allPurchaseOrders = (List<PurchaseOrder>) por.findAll();
		List<PurchaseOrder> allSupplierPurchaseOrders = new ArrayList<PurchaseOrder>();
		for (PurchaseOrder po : allPurchaseOrders) {
		    if(po.getOrder_type().equals("supplier")) {
		    	allSupplierPurchaseOrders.add(po);
		    }
		}
		return allSupplierPurchaseOrders;
	}

	@Override
	public PurchaseOrder add(PurchaseOrder a) {

		return por.save(a);
	}

	@Override
	public PurchaseOrder update(PurchaseOrder change) {
		if(change.getOrder_type().equals("supplier") && change.getOrder_status().equals("Order Completed")) {
			
			increaseInventoryWhenSuppOrderReceived(change);
			checkQuantityToShipOrders();
		}
		return por.save(change);
	}
	
	@Override
	public Product increaseInventoryWhenSuppOrderReceived(PurchaseOrder change) {
		
		SupplierInvoice inv = change.getSupplier_invoice();//sis.getSuppInvoice(change.getSupplier_invoice_id());
		int productId = inv.getProduct_id();
		int quantity = inv.getOrder_quantity();
		
		Product product = ps.getProduct(productId);
		product.setStock_in_warehouse(product.getStock_in_warehouse() + quantity);
		
		return ps.updateProduct(product);
	}
	

	@Override
	public boolean delete(int id) {
		try {
			por.delete(por.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PurchaseOrder orderNeededRawGoods(SupplierInvoice invoice) {
		Date date = new Date(System.currentTimeMillis());
		invoice.setDate_issued(date);
		
		Product product = ps.getProduct(invoice.getProduct_id());
		invoice.setTotal_cost(invoice.getOrder_quantity() * product.getProduct_cost());
		
		invoice = sis.addSupplierInvoice(invoice);
		
		PurchaseOrder po = new PurchaseOrder("order_placed", date, null, null, "supplier", dis.getDistributionInvoice(0), invoice);
		
		return add(po);
	}
	
	public void checkQuantityToShipOrders() {
		List<PurchaseOrder> allOrders = getAll();
		
		List<PurchaseOrder> distibutorOrders = new ArrayList<>();
		
		for(PurchaseOrder po: allOrders) {
			if(po.getDistribution_invoice().getId() != 0) {
				distibutorOrders.add(po);
			}
		}
		
		for(PurchaseOrder po: distibutorOrders) {
			DistributionInvoice di = po.getDistribution_invoice();
			if(po.getOrder_status().equals("order_placed")) {
				System.out.println("\n\n\nPurchase order waiting to ship: " + po);
				System.out.println("distirbuton Invoice waiting to ship: " + di);
				convertRawGoodsAndShip(di.getProduct_id(), di.getOrder_quantity(), po);
			}	
		}
		
		convertRawGoodsToFinishedProduct();
	}
	
	public void convertRawGoodsToFinishedProduct() {
		System.out.println("Converting raw to finished product");
		Product corn_in_warehouse = ps.getProduct(22);
		Product oats_in_warehouse = ps.getProduct(21);
		Product sugar_in_warehouse = ps.getProduct(41);
		Product honey_in_warehouse = ps.getProduct(42);
		
		List<Integer> amount_to_produce = new ArrayList<>();
		amount_to_produce.add(corn_in_warehouse.getStock_in_warehouse() / 10);
		amount_to_produce.add(oats_in_warehouse.getStock_in_warehouse() / 10);
		amount_to_produce.add(sugar_in_warehouse.getStock_in_warehouse());
		amount_to_produce.add(honey_in_warehouse.getStock_in_warehouse());
		
		System.out.println("amount to produce: " + amount_to_produce);
		
		int amount_to_make = Collections.min(amount_to_produce);
		
		System.out.println("Amount to make: " + amount_to_make);
		
		corn_in_warehouse.setStock_in_warehouse(corn_in_warehouse.getStock_in_warehouse() - (amount_to_make * 10));
		oats_in_warehouse.setStock_in_warehouse(oats_in_warehouse.getStock_in_warehouse() - (amount_to_make * 10));
		sugar_in_warehouse.setStock_in_warehouse(sugar_in_warehouse.getStock_in_warehouse() - amount_to_make);
		honey_in_warehouse.setStock_in_warehouse(honey_in_warehouse.getStock_in_warehouse() - amount_to_make);
		
		ps.updateProduct(corn_in_warehouse);
		ps.updateProduct(oats_in_warehouse);
		ps.updateProduct(sugar_in_warehouse);
		ps.updateProduct(honey_in_warehouse);
		
		Product p = ps.getProduct(23);
		p.setStock_in_warehouse(p.getStock_in_warehouse() + amount_to_make);
		
		ps.updateProduct(p);
	}
	
	public void convertRawGoodsAndShip(int product_id, int quantity_needed, PurchaseOrder po) {
		Product p = ps.getProduct(product_id);
		
		int finished_product_needed = quantity_needed - p.getStock_in_warehouse();
		
		
		if(finished_product_needed <= 0) {
			p.setStock_in_warehouse(p.getStock_in_warehouse() - quantity_needed);
			ps.updateProduct(p);
			
			Date date = new Date(System.currentTimeMillis());
			po.setOrder_shipped_date(date);
			po.setOrder_status("order_shipped");
			update(po);
			return;
		}
		
		//calculate amount of raw goods needed
		int corn_needed = 10 * finished_product_needed;
		int oats_needed = 10 * finished_product_needed;
		int sugar_needed = 1 * finished_product_needed;
		int honey_needed = 1 * finished_product_needed;
		
		Product corn_in_warehouse = ps.getProduct(22);
		Product oats_in_warehouse = ps.getProduct(21);
		Product sugar_in_warehouse = ps.getProduct(41);
		Product honey_in_warehouse = ps.getProduct(42);
		
		int corn_needed_to_order = corn_needed - corn_in_warehouse.getStock_in_warehouse(); //see how much we need to order
		int oats_needed_to_order = oats_needed - oats_in_warehouse.getStock_in_warehouse();
		int sugar_needed_to_order = sugar_needed - sugar_in_warehouse.getStock_in_warehouse();
		int honey_needed_to_order = honey_needed - honey_in_warehouse.getStock_in_warehouse();
		
		if(corn_needed_to_order <= 0 && oats_needed_to_order <= 0 && sugar_needed_to_order  <= 0 && honey_needed_to_order <= 0) {
			//convert raw materials to finished_product
			corn_in_warehouse.setStock_in_warehouse(corn_in_warehouse.getStock_in_warehouse() - corn_needed);
			oats_in_warehouse.setStock_in_warehouse(oats_in_warehouse.getStock_in_warehouse() - oats_needed);
			sugar_in_warehouse.setStock_in_warehouse(sugar_in_warehouse.getStock_in_warehouse() - sugar_needed);
			honey_in_warehouse.setStock_in_warehouse(honey_in_warehouse.getStock_in_warehouse() - honey_needed);
			
			ps.updateProduct(corn_in_warehouse);
			ps.updateProduct(oats_in_warehouse);
			ps.updateProduct(sugar_in_warehouse);
			ps.updateProduct(honey_in_warehouse);
			
			p.setStock_in_warehouse(p.getStock_in_warehouse() + finished_product_needed);
			p.setStock_in_warehouse(p.getStock_in_warehouse() - quantity_needed);
			ps.updateProduct(p);
			
			Date date = new Date(System.currentTimeMillis());
			po.setOrder_shipped_date(date);
			po.setOrder_status("order_shipped");
			update(po);
			
		}
	}

	@Override
	public String checkInventory(int product_id, int quantity_needed) {
		Product p = ps.getProduct(product_id);
		
		int finished_product_needed = quantity_needed - p.getStock_in_warehouse();
		
		
		if(finished_product_needed <= 0) {
			p.setStock_in_warehouse(p.getStock_in_warehouse() - quantity_needed);
			ps.updateProduct(p);
			return "enough in stock";
		}
		
		//calculate amount of raw goods needed
		int corn_needed = 10 * finished_product_needed;
		int oats_needed = 10 * finished_product_needed;
		int sugar_needed = 1 * finished_product_needed;
		int honey_needed = 1 * finished_product_needed;
		
		Product corn_in_warehouse = ps.getProduct(22);
		Product oats_in_warehouse = ps.getProduct(21);
		Product sugar_in_warehouse = ps.getProduct(41);
		Product honey_in_warehouse = ps.getProduct(42);
		
		int corn_needed_to_order = corn_needed - corn_in_warehouse.getStock_in_warehouse(); //see how much we need to order
		int oats_needed_to_order = oats_needed - oats_in_warehouse.getStock_in_warehouse();
		int sugar_needed_to_order = sugar_needed - sugar_in_warehouse.getStock_in_warehouse();
		int honey_needed_to_order = honey_needed - honey_in_warehouse.getStock_in_warehouse();
		
		if(corn_needed_to_order <= 0 && oats_needed_to_order <= 0 && sugar_needed_to_order  <= 0 && honey_needed_to_order <= 0) {
			//convert raw materials to finished_product
			corn_in_warehouse.setStock_in_warehouse(corn_in_warehouse.getStock_in_warehouse() - corn_needed);
			oats_in_warehouse.setStock_in_warehouse(oats_in_warehouse.getStock_in_warehouse() - oats_needed);
			sugar_in_warehouse.setStock_in_warehouse(sugar_in_warehouse.getStock_in_warehouse() - sugar_needed);
			honey_in_warehouse.setStock_in_warehouse(honey_in_warehouse.getStock_in_warehouse() - honey_needed);
			
			ps.updateProduct(corn_in_warehouse);
			ps.updateProduct(oats_in_warehouse);
			ps.updateProduct(sugar_in_warehouse);
			ps.updateProduct(honey_in_warehouse);
			
			p.setStock_in_warehouse(p.getStock_in_warehouse() + finished_product_needed);
			p.setStock_in_warehouse(p.getStock_in_warehouse() - quantity_needed);
			ps.updateProduct(p);
			return "enough in stock";
		}
		
		if(corn_needed_to_order > 0) {
			SupplierInvoice corn_invoice = new SupplierInvoice();
			corn_invoice.setProduct_id(22);
			corn_invoice.setSupplier_id(21);
			corn_invoice.setUser_id(0);
			corn_invoice.setOrder_quantity(corn_needed_to_order);
			
			System.out.println("ordering corn");
			orderNeededRawGoods(corn_invoice);
		}
		
		if(oats_needed_to_order > 0) {
			SupplierInvoice oats_invoice = new SupplierInvoice();
			oats_invoice.setProduct_id(21);
			oats_invoice.setSupplier_id(22);
			oats_invoice.setUser_id(0);
			oats_invoice.setOrder_quantity(oats_needed_to_order);
			
			System.out.println("ordering oats");
			orderNeededRawGoods(oats_invoice);
		}
		
		if(sugar_needed_to_order > 0) {
			SupplierInvoice sugar_invoice = new SupplierInvoice();
			sugar_invoice.setProduct_id(41);
			sugar_invoice.setSupplier_id(23);
			sugar_invoice.setUser_id(0);
			sugar_invoice.setOrder_quantity(sugar_needed_to_order);
			
			System.out.println("ordering sugar");
			orderNeededRawGoods(sugar_invoice);
		}
		
		if(honey_needed_to_order > 0) {
			SupplierInvoice honey_invoice = new SupplierInvoice();
			honey_invoice.setProduct_id(42);
			honey_invoice.setSupplier_id(24);
			honey_invoice.setUser_id(0);//indicates the system
			honey_invoice.setOrder_quantity(honey_needed_to_order);
			
			System.out.println("ordering honey");
			orderNeededRawGoods(honey_invoice);
		}
		
		return "waiting for raw good";
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderBySupplierId(int id) {
		return por.findByDistributionInvoice_DistributorId(id);
	}
	
	
}
