package com.revature.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.revature.beans.Supplier;
import com.revature.beans.SupplierInvoice;
import com.revature.services.SupplierInvoiceService;

@SpringBootTest(classes = com.revature.demo.Project2Application.class)
@AutoConfigureMockMvc
public class SupplierInvoiceControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SupplierInvoiceService service;
	
	static private Gson gson = new Gson();
	
	@Test
	public void getSupplerInvoices() throws Exception {
		mockMvc.perform(get("/supplier_invoice"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getSupplierInvoiceById() throws Exception {

		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, new Date(0));

		Mockito.when(service.getSuppInvoice(1)).thenReturn(invoice1);
		
		mockMvc.perform(get("/supplier_invoice/1"))
		.andExpect(status().isOk());
	}

	
//	@Test
//	public void addSupplierInvoice() throws Exception {
//		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, new Date(0));
//		
//		Mockito.when(service.addSupplierInvoice(invoice1)).thenReturn(invoice1);
//		
//		mockMvc.perform(post("/supplier_invoice")
//		.contentType(MediaType.APPLICATION_JSON)
//		.content(gson.toJson(invoice1)))
//		.andExpect(status().isOk());
//	}
	
//	@Test
//	public void updateSupplierInvoice() throws Exception {
//		
//		Date date = new Date(System.currentTimeMillis());
//		
//		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, date);
//		
//		Mockito.when(service.updateSupplierInvoice(invoice1)).thenReturn(invoice1);
//		
//		// com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.sql.Date` from String 
//		// One possible solution was to use @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") above the date field in the SupplierInvoice bean
//		
//		mockMvc.perform(put("/supplier_invoice/1")
//		.contentType(MediaType.APPLICATION_JSON)
//		.content(gson.toJson(invoice1)))
//		.andExpect(status().isOk());
//	}
	
	@Test
	public void deleteSupplierInvoice() throws Exception {
		
		Date date = new Date(System.currentTimeMillis());
		SupplierInvoice invoice1 = new SupplierInvoice(1, 2, 3, 4, 5, 6.6, date);
		
		Mockito.when(service.deleteSupplierInvoice(1)).thenReturn(true);
		
		// com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.sql.Date` from String 
		
		mockMvc.perform(delete("/supplier_invoice/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(gson.toJson(invoice1)))
		.andExpect(status().isOk());
	}
	
}
