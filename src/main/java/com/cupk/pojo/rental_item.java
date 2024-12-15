package com.cupk.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class rental_item {
    private Integer id;
    private String name;
    private Double price_one_day;
    private double deposit;
    private String introduce;
    private String address;
    private String imgurl;
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date time;
    private String state;
    private String type;
    private Integer user_id;
    private String user_name;
    private String remark;
    private String title;
}
