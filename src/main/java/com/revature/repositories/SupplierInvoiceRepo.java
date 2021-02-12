package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.SupplierInvoice;

@Repository
public interface SupplierInvoiceRepo extends CrudRepository<SupplierInvoice, Integer>{

}
