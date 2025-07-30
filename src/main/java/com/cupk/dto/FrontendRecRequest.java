package com.cupk.dto;

// 这个DTO用于接收来自前端的请求参数
public class FrontendRecRequest {
    private Integer product_id;
    private Integer n;

    // Getters and Setters
    public Integer getProduct_id() { return product_id; }
    public void setProduct_id(Integer product_id) { this.product_id = product_id; }
    public Integer getN() { return n; }
    public void setN(Integer n) { this.n = n; }
}