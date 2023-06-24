package com.warehousemanagement.wms.services;


import com.warehousemanagement.wms.dto.ProductCriteriaDTO;
import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.repository.CategoryRepository;
import com.warehousemanagement.wms.repository.ProviderRepository;
import com.warehousemanagement.wms.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductCriteriaDTOService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private ProviderRepository providerRepository;
    public List<ProductCriteriaDTO> getProductCriteria(){
        List<String> unity = Arrays.asList("bucați", "kg");
        List<String> category=categoryRepository.getCategoryName();
        List<String> subcategory=subcategoryRepository.getSubcategoryName();
        List<String> provider=providerRepository.getProviderName();
        List<ProductCriteriaDTO> productsCriteria=
                Arrays.asList(new ProductCriteriaDTO(1,category,"Categorie","category","toate"),
                        new ProductCriteriaDTO(2,subcategory,"Subcategorie","subcategory","toate"),
                        new ProductCriteriaDTO(3,unity,"Unitate","unity","toate"),
                        new ProductCriteriaDTO(4,provider,"Producator","manufacturer","toate"));

         return productsCriteria;


    }
}
