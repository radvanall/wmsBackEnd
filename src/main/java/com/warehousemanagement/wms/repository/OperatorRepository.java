package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator,Integer> {
}
