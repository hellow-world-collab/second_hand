package com.cupk.pojo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
//    private String role;
    private String phone;
    private String email;
    private String imgurl;
    private String address;
    private List<useraddress> addresslist;
    public void setImgurl(String url) {
        imgurl = url;
    }
}
