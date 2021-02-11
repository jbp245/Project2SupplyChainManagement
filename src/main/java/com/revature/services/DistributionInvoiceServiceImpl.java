package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.DistributionInvoice;
import com.revature.repositories.DistributionInvoiceRepo;

@Service
public class DistributionInvoiceServiceImpl implements DistributionInvoiceService {

	@Autowired
	private DistributionInvoiceRepo dir;
	
	@Override
	public DistributionInvoice addDistributionInvoice(DistributionInvoice d) {
		return dir.save(d);
	}

	@Override
	public DistributionInvoice getDistributionInvoice(int id) {
		return dir.findById(id).get();
	}

	@Override
	public List<DistributionInvoice> getAllDistributionInvoices() {
		return (List<DistributionInvoice>) dir.findAll();
	}

	@Override
	public DistributionInvoice updateDistributionInvoice(DistributionInvoice update) {
		return dir.save(update);
	}

	@Override
	public boolean deleteDistributionInvoice(int id) {
		try {
			dir.delete(dir.findById(id).get());
			return true;
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
