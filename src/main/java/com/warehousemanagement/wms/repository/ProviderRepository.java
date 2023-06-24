package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    @Query("SELECT providerName From Provider ")
    List<String> getProviderName();
    @Query("SELECT new com.warehousemanagement.wms.model.Provider(id,providerName) From Provider ")
    List<Provider> getProviderNameAndId();
}
