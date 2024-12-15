package com.cupk.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Commodity_Message {
    private int commodity_id;
    private String commodity_imgurl;
    private String commodity_introduce;
    private double commodity_price;
    private String commodity_send_address;
    private String commodity_sale_user;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date commodity_pub_time;

}
