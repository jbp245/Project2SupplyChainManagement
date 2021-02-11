package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Distributor;

@Repository
public interface DistributorRepository extends CrudRepository<Distributor, Integer>{

}
