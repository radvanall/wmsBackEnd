package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Invoice;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public void setInvoice(List<Invoice> invoicesList) {invoiceRepository.saveAll(invoicesList);
    }


    public List<Invoice> getInvoices() {return invoiceRepository.findAll();
    }

    public Invoice getInvoice(Integer id) {return invoiceRepository.findById(id).get();
    }

    public void updateInvoice(Invoice invoice, Integer id) {
        Invoice getInvoice=invoiceRepository.findById(id).get();
        getInvoice.copyInvoice(invoice);
        invoiceRepository.save(getInvoice);
    }

    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }
}
