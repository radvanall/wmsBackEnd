package com.warehousemanagement.wms.dto;

import java.util.List;

public class SingleOperatorDTO extends UserDTO {
    private List<WorkDaysDTO> workedDays;
    private List<OperatorInvoiceDTO> invoices;

    public SingleOperatorDTO() {
    }

    public SingleOperatorDTO(Integer id,
                             String nickname, String avatar,
                             Integer phone, String email,String address,
                             String name, String surname, String status,
                             List<WorkDaysDTO> workedDays,
                             List<OperatorInvoiceDTO> invoices) {

        super(id,nickname,avatar,phone,email,address,name,surname,status);
        this.workedDays = workedDays;
        this.invoices = invoices;
    }


    public List<WorkDaysDTO> getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(List<WorkDaysDTO> workedDays) {
        this.workedDays = workedDays;
    }

    public List<OperatorInvoiceDTO> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<OperatorInvoiceDTO> invoices) {
        this.invoices =invoices;
    }

}
