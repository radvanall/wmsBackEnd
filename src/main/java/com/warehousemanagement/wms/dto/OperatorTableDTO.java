package com.warehousemanagement.wms.dto;

public class OperatorTableDTO {
    private Integer id;
    private String img;
    private String nickname;
    private String name;
    private String surname;
    private String email;
    private Integer tel;
    private String status;

    public OperatorTableDTO() {
    }

    public OperatorTableDTO(Integer id, String img, String nickname, String name, String surname, String email, Integer tel, String status) {
        this.id = id;
        this.img = img;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tel = tel;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
