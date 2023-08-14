package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.dto.CustomersSpendingByProduct;
import com.warehousemanagement.wms.model.Customer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findByActiveTrue();

    @Query("SELECT NEW com.warehousemanagement.wms.dto.CustomersSpendingByProduct(p.id, p.name,p.image, SUM(s.sellingPrice * o.quantity)) " +
            "FROM Customer c " +
            "INNER JOIN c.invoices i " +
            "INNER JOIN i.order o " +
            "INNER JOIN o.stock s " +
            "INNER JOIN s.position p " +
            "WHERE c.id = :id " +
            "GROUP BY p.id, p.name " +
            "ORDER BY SUM(s.sellingPrice * o.quantity) DESC")
    List<CustomersSpendingByProduct> getCustomerSpendingByProduct(@Param("id")Integer id, PageRequest pageable);
}
