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

import com.revature.beans.Distributor;
import com.revature.beans.Product;
import com.revature.repositories.DistributorRepository;
import com.revature.services.DistributorServiceImpl;

@SpringBootTest(classes = com.revature.demo.Project2Application.class)
class DistributorServiceTests {
	
	@Autowired
	private DistributorServiceImpl distservice;
	
	@MockBean
	private DistributorRepository distrepo;

	@Test
	public void getDistributorstest() {
		List<Distributor> distdetails = new ArrayList<>();
		Distributor distdetail1 = new Distributor(1, "Costco","New Jersey", "4567891234");
		distdetails.add(distdetail1);
		Distributor distdetail2 = new Distributor(2, "BJs","Philadelphia", "7891234567");
		distdetails.add(distdetail2);
		Distributor distdetail3 = new Distributor(3, "Walmart","Burlington","8761234567");
		distdetails.add(distdetail3);
		
		
		when(distrepo.findAll()).thenReturn(distdetails);
		
		Assert.assertEquals(3, distservice.getAllDistributors().size());
	}
	
	@Test
	public void getDistributorByIdTest() {
		List<Distributor> distdetails = new ArrayList<>();
		Distributor distdetail1 = new Distributor(1, "Costco","New Jersey", "4567891234");
		distdetails.add(distdetail1);
		Distributor distdetail2 = new Distributor(2, "BJs","Philadelphia", "7891234567");
		distdetails.add(distdetail2);
		Distributor distdetail3 = new Distributor(3, "Walmart","Burlington","8761234567");
		distdetails.add(distdetail3);
		
		
		when(distrepo.findById(2)).thenReturn(Optional.of(distdetail2));
		
		Distributor returned_distributor = distservice.getDistributor(2);
		
		Assert.assertEquals(distdetail2.getId(), returned_distributor.getId());
		Assert.assertEquals(distdetail2.getName(), returned_distributor.getName());
	}
	
	@Test
	public void addDistributorTest() {
		Distributor distdetail1 = new Distributor(1, "Costco","New Jersey", "4567891234");
		
		when(distrepo.save(distdetail1)).thenReturn(distdetail1);
		
		Assert.assertEquals(distdetail1, distservice.addDistributor(distdetail1));
	}
	
	@Test
	public void updateDistributorTest() {
		Distributor distdetail1 = new Distributor(1, "Costco","New Jersey", "4567891234");
		when(distrepo.save(distdetail1)).thenReturn(distdetail1);
		
		Assert.assertEquals(distdetail1, distservice.updateDistributor(distdetail1));
	}
	
	@Test
	public void deleteDistributorTest() {
		Distributor distdetail1 = new Distributor(1, "Costco","New Jersey", "4567891234");
		Optional<Distributor> distributor = Optional.of(distdetail1);
		
		when(distrepo.findById(distdetail1.getId())).thenReturn(distributor);
		Mockito.doNothing().when(distrepo).delete(distdetail1);
		Assert.assertTrue(distservice.deleteDistributor(distdetail1.getId()));
	}
	
	@Test
	public void getFalseWhenErrorIsThrownDeleting() {
		Distributor distdetail = new Distributor(1, "Costco","New Jersey", "4567891234");
		Optional<Distributor> distributor = Optional.of(distdetail);
		
		when(distrepo.findById(distdetail.getId())).thenReturn(distributor);
		Mockito.doThrow(IllegalArgumentException.class).when(distrepo).delete(distdetail);
		
		Assert.assertFalse(distservice.deleteDistributor(distdetail.getId()));
	}

}
