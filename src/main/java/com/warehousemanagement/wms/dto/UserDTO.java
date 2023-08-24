package com.warehousemanagement.wms.dto;

public class UserDTO {
    private Integer id;
    private String nickname;
    private String avatar;
    private Integer phone;
    private String email;
    private String address;
    private String name;
    private String surname;
    private String status;

    public UserDTO() {
    }

    public UserDTO(Integer id, String nickname, String avatar,
                   Integer phone, String email, String address,
                   String name, String surname, String status) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
