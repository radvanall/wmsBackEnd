package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Modifying
    @Transactional
    @Query(value="Insert into work.order (product_id,customer_id,quantity) VALUES (:productId,:customerId,:quantity)", nativeQuery = true)
    void addOrders(Integer productId, Integer customerId, Integer quantity);
}
