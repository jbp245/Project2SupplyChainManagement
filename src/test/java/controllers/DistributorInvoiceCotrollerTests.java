package controllers;

import static org.assertj.core.api.Assertions.in;
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
import com.revature.controllers.DistributionInvoiceController;
import com.revature.services.DistributionInvoiceServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.demo.Project2Application.class)
class DistributorInvoiceCotrollerTests {

	@Autowired
	MockMvc mocMvc;
	
	@Autowired
	DistributionInvoiceController controller;
	
	@MockBean
	DistributionInvoiceServiceImpl service;
	
	public static Gson gson = new Gson();
	
	@Test
	public void getAllDistributionInvoices() throws Exception {
		DistributionInvoice d1 = new DistributionInvoice();
		DistributionInvoice d2 = new DistributionInvoice();
		List<DistributionInvoice> invoices = new ArrayList<>();
		
		invoices.add(d1);
		invoices.add(d2);
		
		Mockito.when(service.getAllDistributionInvoices()).thenReturn(invoices);
		
//		mocMvc.perform(get("/product")).andExpect(status().isOk());
		ResultActions ra = mocMvc.perform(get("/distributionInvoice"));
		ra.andExpect(status().isOk());
	}
	
	@Test
	void getById() throws Exception {
		DistributionInvoice d1 = new DistributionInvoice();
		Mockito.when(service.getDistributionInvoice(1)).thenReturn(d1);
		
		mocMvc.perform(get("/distributionInvoice/1")).andExpect(status().isOk());
	}
	
	
	@Test
	void makeNewDistributionInvoice() throws Exception {
		DistributionInvoice invoice1 = new DistributionInvoice(1, null, 2, 30, 1, 5);
		
		
		Mockito.when(service.addDistributionInvoice(invoice1)).thenReturn(invoice1);
		mocMvc.perform(post("/distributionInvoice").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(invoice1))).andExpect(status().isOk());
		
	}
	
	@Test
	void updateProduct() throws Exception {
		DistributionInvoice invoice1 = new DistributionInvoice(1, null, 2, 30, 1, 5);
		
		Mockito.when(service.updateDistributionInvoice(invoice1)).thenReturn(invoice1);
		mocMvc.perform(put("/distributionInvoice/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(invoice1))).andExpect(status().isOk());
	}
	
	@Test
	void deleteProduct() throws Exception {
		Date date = new Date(System.currentTimeMillis());
		DistributionInvoice invoice1 = new DistributionInvoice(1, date, 2, 30, 1, 5);
		
		Mockito.when(service.deleteDistributionInvoice(1)).thenReturn(true);
		mocMvc.perform(delete("/distributionInvoice/1").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(invoice1))).andExpect(status().isOk());
	}

}
