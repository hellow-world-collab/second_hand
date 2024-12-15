package com.cupk.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private String imgurl;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private Integer user_id;
    private String user_name;
    private String title;
    private String introduce;
    private String address;
    private String remark;
    private String state;
public void setImgurl(String url) {
    imgurl = url;
}
}
