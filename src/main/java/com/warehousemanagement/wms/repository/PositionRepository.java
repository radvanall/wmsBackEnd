package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.dto.ProductDTO;
import com.warehousemanagement.wms.dto.ProductTableDTO;
import com.warehousemanagement.wms.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {

//    @Query(value="select p.id as id ,p.image as img, p.name as name,c.category_name as categorie,s.subcategory_name as subcategorie," +
//            "pr.provider_name as producator,p.unity as unitate  from work.position p inner join work.provider pr on p.provider_id=pr.id inner join " +
//            "work.subcategory s on p.subcategory_id=s.id inner join work.category c on p.category_id=c.id",nativeQuery=true)

    @Modifying
    @Transactional
    @Query( value="INSERT INTO  work.position(name,description,image,unity,subcategory_id,category_id,provider_id,active)" +
            " VALUES (:name,:description,:image,:unity,:subcategory_id,:category_id,:provider_id,true)",nativeQuery=true)
    void insertPosition(@Param("name") String name,@Param("description") String description,
                        @Param("image") String image,@Param("unity") String unity,
                        @Param("subcategory_id") Integer subcategory_id,@Param("category_id") Integer category_id,
                        @Param("provider_id")Integer provider_id);
    @Query(nativeQuery=true)
    List<ProductTableDTO>getTableProductsDTOList();

//    @Query(value="select p.id as id ,p.image as img, p.name as name,c.category_name as categorie,s.subcategory_name as subcategorie, " +
//            " pr.provider_name as producator,p.unity as unitate,p.description as description  from work.position p inner join work.provider pr on p.provider_id=pr.id inner join \n" +
//            " work.subcategory s on p.subcategory_id=s.id inner join work.category c on p.category_id=c.id where p.id=:id",nativeQuery=true)
    @Query(nativeQuery=true)
    ProductDTO getProductById(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value="UPDATE work.position SET name=:name,description=:description,image=:image, " +
            "unity=:unity,subcategory_id=:subcategory_id,category_id=:category_id,provider_id=:provider_id" +
            " WHERE id=:id",nativeQuery=true)
    void updatePosition(@Param("id") Integer id,@Param("name") String name,@Param("description") String description,
                        @Param("image") String image,@Param("unity") String unity,
                        @Param("subcategory_id") Integer subcategory_id,@Param("category_id") Integer category_id,
                        @Param("provider_id")Integer provider_id);
    @Modifying
    @Transactional
     @Query("Update Position set active=false where id=:id")
    void disablePosition(@Param("id")Integer id);
}
