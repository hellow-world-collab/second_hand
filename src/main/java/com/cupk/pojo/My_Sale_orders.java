package com.cupk.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class My_Sale_orders {
    private int order_id;
    private String order_number;
    private double order_price;
    private String order_state;
    //    private String number;
    private Product product;
    private User buy_user;
    private User sale_user;
}
