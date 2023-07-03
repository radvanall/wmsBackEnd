package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    @Query("SELECT providerName From Provider ")
    List<String> getProviderName();
    @Query("SELECT new com.warehousemanagement.wms.model.Provider(id,providerName) From Provider ")
    List<Provider> getProviderNameAndId();

    @Modifying
    @Transactional
    @Query("Update Provider set active=false where id=:id")
    void disableProvider(@Param("id")Integer id);

    List<Provider> findByActiveTrue();
}
