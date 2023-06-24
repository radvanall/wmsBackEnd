package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.ProductObjectCriteriaDTO;
import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.repository.CategoryRepository;

import com.warehousemanagement.wms.repository.ProviderRepository;
import com.warehousemanagement.wms.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class ProductObjectCriteriaDTOService {
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubcategoryRepository subcategoryRepository;
    public ProductObjectCriteriaDTO getCriteria(){
        List<Provider> providers=providerRepository.getProviderNameAndId();
        List<Category> categories=categoryRepository.getCategoryNameAndId();
        List<Subcategory> subcategories=subcategoryRepository.getSubcategoryNameAndId();
        ProductObjectCriteriaDTO productObjectCriteriaDTO= new ProductObjectCriteriaDTO(
                providers,categories,subcategories, Arrays.asList("bucați", "kg"));
        return productObjectCriteriaDTO;

    }
}
