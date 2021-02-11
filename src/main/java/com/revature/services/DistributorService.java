package com.revature.services;

import java.util.List;

import com.revature.beans.Distributor;


public interface DistributorService {

	public Distributor addDistributor(Distributor d);
	public Distributor getDistributor(int id);
	public List<Distributor> getAllDistributors();	
	public Distributor updateDistributor(Distributor update);
	public boolean deleteDistributor(int id);
}
