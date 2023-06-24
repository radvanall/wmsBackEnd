package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.InvoiceStockDTO;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceReceptionService {
    @Autowired
    private InvoiceReceptionRepository invoiceReceptionRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private ProviderRepository providerRepository;

//    public void setInvoiceReception(List<InvoiceReception> invoiceReceptionsList) {
//        invoiceReceptionRepository.saveAll(invoiceReceptionsList);
//    }
        public void setInvoiceReception(List<InvoiceStockDTO> invoiceStockDTOList,Integer providerId,Integer adminId) {
            System.out.println("invoiceList="+invoiceStockDTOList.toString());
            System.out.println("providerId="+providerId);
            System.out.println("adminId="+adminId);
            List<Stock>stocks=new ArrayList<>();
            for(InvoiceStockDTO invoiceStock:invoiceStockDTOList){
                Position position=positionRepository.findById(invoiceStock.getProductId()).get();
                stocks.add(new Stock(invoiceStock.getStockQuantity(),invoiceStock.getSellingPrice(),invoiceStock.getBuyingPrice(),position));

            }
            System.out.println("invoiceList2="+stocks.toString());
            Administrator administrator =administratorRepository.findById(adminId).get();
            Provider provider=providerRepository.findById(providerId).get();


            InvoiceReception invoiceReception=new InvoiceReception(stocks,administrator,provider);
            stockRepository.saveAll(stocks);
            invoiceReceptionRepository.save(invoiceReception);
    }

    public InvoiceReception getInvoiceReception(Integer id) {return invoiceReceptionRepository.findById(id).get();
    }

    public List<InvoiceReception> getInvoiceReceptions() {
        return invoiceReceptionRepository.findAll();
    }

    public void updateInvoiceReception(InvoiceReception invoiceReception, Integer id) {
        InvoiceReception getInvoiceReception=invoiceReceptionRepository.findById(id).get();
        getInvoiceReception.copyInvoiceReception(invoiceReception);
        invoiceReceptionRepository.save(getInvoiceReception);
    }

    public void deleteInvoiceReception(Integer id) {
        invoiceReceptionRepository.deleteById(id);
    }
}
