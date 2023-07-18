package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.InvoiceReceptionDTO;
import com.warehousemanagement.wms.dto.InvoiceReceptionTableDTO;
import com.warehousemanagement.wms.dto.InvoiceStockDTO;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     public String addStock(Map<String, Integer> stock){
         Position position=positionRepository.findById(stock.get("productId")).get();
         InvoiceReception invoiceReception=invoiceReceptionRepository.findById(stock.get("invoiceId")).get();
         Stock newStock=new Stock(stock.get("stockQuantity"),Double.valueOf(stock.get("sellingPrice")),
                 Double.valueOf(stock.get("buyingPrice")),position);
         invoiceReception.getStocks().add(newStock);
         stockRepository.save(newStock);
         invoiceReceptionRepository.save(invoiceReception);
         System.out.println(position);
         return "Stocul a fost salvat";
     }
    public InvoiceReceptionDTO getInvoiceReception(Integer id) {
            InvoiceReception invoiceReception=invoiceReceptionRepository.findById(id).get();
        String validatedBy="none";
        if(invoiceReception.getValidated()) validatedBy=invoiceReception.getValidatedBy().getNickname();
            InvoiceReceptionDTO invoiceReceptionDTO=new InvoiceReceptionDTO(
                    invoiceReception.getId(),
                    invoiceReception.getValidated(),
                    invoiceReception.getDateOfCreation(),
                    invoiceReception.getDateOfValidation(),
                    validatedBy,
                    invoiceReception.getCreatedBy().getNickname(),
                    invoiceReception.getStocks().stream()
                            .mapToDouble(stock->stock.getBuyingPrice())
                            .sum(),
                    invoiceReception.getStocks().stream()
                            .mapToDouble(stock -> stock.getSellingPrice())
                            .sum(),
                    invoiceReception.getProvider().getProviderName(),
                    invoiceReception.getProvider().getId(),
                    invoiceReception.getProvider().getImage(),
                    Long.valueOf(invoiceReception.getStocks().stream().count()).intValue(),
                    Double.valueOf(invoiceReception.getStocks().stream()
                            .mapToDouble(Stock::getStockQuantity).sum()).intValue(),
                    invoiceReception.getStocks()
            );
            return invoiceReceptionDTO;
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

    public List<InvoiceReceptionTableDTO> getInvoiceReceptionsTable() {
       List<InvoiceReception> invoiceReception=invoiceReceptionRepository.findAll();
       List<InvoiceReceptionTableDTO> invoiceReceptionTableDTOS=
       invoiceReception.stream().map(invoice->{
           String validatedBy="none";
           if(invoice.getValidated()) validatedBy=invoice.getValidatedBy().getNickname();
           return new InvoiceReceptionTableDTO(invoice.getId(),invoice.getValidated(),
                   invoice.getDateOfCreation(),invoice.getDateOfValidation(),
                   validatedBy,invoice.getCreatedBy().getNickname(),
                   invoice.getStocks().stream()
                           .mapToDouble(stock -> stock.getBuyingPrice())
                           .sum(),
                   invoice.getStocks().stream()
                           .mapToDouble(stock -> stock.getSellingPrice())
                           .sum(),
                   invoice.getProvider().getProviderName());
             }).collect(Collectors.toList());
          return invoiceReceptionTableDTOS;
        }


        public String validateInvoice(Integer id) {
            InvoiceReception getInvoice=invoiceReceptionRepository.findById(id).get();
            Administrator administrator=administratorRepository.findById(1).get();
            getInvoice.setValidated(true);
            getInvoice.setValidatedBy(administrator);
            getInvoice.setDateOfValidation(new Date(System.currentTimeMillis()));
            invoiceReceptionRepository.save(getInvoice);
            return "Factura a fost validată";
        }

}
