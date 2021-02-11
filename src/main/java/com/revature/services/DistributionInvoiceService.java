package com.revature.services;

import java.util.List;

import com.revature.beans.DistributionInvoice;

public interface DistributionInvoiceService {

	public DistributionInvoice addDistributionInvoice(DistributionInvoice d);
	public DistributionInvoice getDistributionInvoice(int id);
	public List<DistributionInvoice> getAllDistributionInvoices();	
	public DistributionInvoice updateDistributionInvoice(DistributionInvoice update);
	public boolean deleteDistributionInvoice(int id);
	
}
