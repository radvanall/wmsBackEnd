package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Integer> {
}
