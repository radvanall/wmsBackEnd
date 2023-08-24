package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.InvoiceReception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceReceptionRepository extends JpaRepository<InvoiceReception,Integer> {


    List<InvoiceReception> findAllByValidatedById(Integer id);
}
