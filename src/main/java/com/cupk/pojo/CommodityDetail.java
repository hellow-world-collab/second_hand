package com.cupk.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CommodityDetail {
    private int commodity_id;
    private String commodity_name;
    private String commodity_imgurl;
    private String commodity_introduce;
    private double commodity_price;
    private String commodity_send_address;
    private String commodity_saler;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date commodity_pub_time;
}
