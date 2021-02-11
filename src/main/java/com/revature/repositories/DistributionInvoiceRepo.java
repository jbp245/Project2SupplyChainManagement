package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.DistributionInvoice;

@Repository
public interface DistributionInvoiceRepo extends CrudRepository<DistributionInvoice, Integer>{

}
