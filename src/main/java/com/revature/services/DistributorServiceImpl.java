package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Distributor;
import com.revature.repositories.DistributorRepository;

@Service
public class DistributorServiceImpl implements DistributorService {
	
	@Autowired
	DistributorRepository dr;

	@Override
	public Distributor addDistributor(Distributor d) {
		return dr.save(d);
	}

	@Override
	public Distributor getDistributor(int id) {
		return dr.findById(id).get();
	}

	@Override
	public List<Distributor> getAllDistributors() {
		return (List<Distributor>) dr.findAll();
	}

	@Override
	public Distributor updateDistributor(Distributor update) {
		return dr.save(update);
	}

	@Override
	public boolean deleteDistributor(int id) {
		try {
			dr.delete(dr.findById(id).get());
			return true;
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
