package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.ProductObjectCriteriaDTO;
import com.warehousemanagement.wms.services.ProductObjectCriteriaDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productobjectcriteria")
public class ProductObjectCriteriaDTOController {
  @Autowired
  private ProductObjectCriteriaDTOService productObjectCriteriaDTOService;
  @RequestMapping(method= RequestMethod.GET,value ="/get")
    public ProductObjectCriteriaDTO getProductCriteria() {return productObjectCriteriaDTOService.getCriteria();}
}
