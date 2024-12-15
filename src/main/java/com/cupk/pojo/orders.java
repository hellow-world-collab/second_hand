package com.cupk.pojo;

import lombok.Data;

import java.util.List;

@Data
public class orders {
    private int order_id;
    private String order_number;
    private double order_price;
    private String order_state;
    private String falge;

    private Product product;
    private User buy_user;
    private User sale_user;



}
