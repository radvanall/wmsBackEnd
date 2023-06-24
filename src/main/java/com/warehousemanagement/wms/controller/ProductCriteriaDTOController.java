package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.ProductCriteriaDTO;
import com.warehousemanagement.wms.services.ProductCriteriaDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/productcriteria")
public class ProductCriteriaDTOController {
    @Autowired
    private ProductCriteriaDTOService productCriteriaDTOService;
    @RequestMapping(method= RequestMethod.GET,value = "/get")
    public List<ProductCriteriaDTO> getAllProductCriteria(){
        return productCriteriaDTOService.getProductCriteria();
    }
}
