package com.warehousemanagement.wms.dto;

import java.util.List;

public class AdministratorDTO extends UserDTO{
    private List<WorkDaysDTO> workedDays;
    public AdministratorDTO(Integer id, String nickname, String avatar,
                            Integer phone, String email,
                            String address, String name,
                            String surname, String status,
                            List<WorkDaysDTO> workedDays) {
        super(id, nickname, avatar, phone, email, address, name, surname, status);
        this.workedDays = workedDays;
    }

    public List<WorkDaysDTO> getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(List<WorkDaysDTO> workedDays) {
        this.workedDays = workedDays;
    }
}
