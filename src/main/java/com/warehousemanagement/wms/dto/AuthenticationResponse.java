package com.warehousemanagement.wms.dto;

public class AuthenticationResponse {
    private final String jwt;
    private final String userName;
    private final String avatar;
    private final Integer id;
    private final String authority;

    public AuthenticationResponse(String jwt,String userName,String avatar,Integer id,String authority) {
        this.jwt = jwt;
        this.userName=userName;
        this.avatar=avatar;
        this.id=id;
        this.authority=authority;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }
}
