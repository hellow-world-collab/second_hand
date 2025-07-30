package com.cupk.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

// 这个DTO用于构建发送给Python API的请求体
@JsonInclude(JsonInclude.Include.NON_NULL) // 这个注解确保null字段不会被序列化，符合Python服务的Optional特性
public class RecommendationRequest {
    private Integer user_id;
    private Integer product_id;
    private int n = 10; // 默认推荐数量

    // Getters and Setters
    public Integer getUser_id() { return user_id; }
    public void setUser_id(Integer user_id) { this.user_id = user_id; }
    public Integer getProduct_id() { return product_id; }
    public void setProduct_id(Integer product_id) { this.product_id = product_id; }
    public int getN() { return n; }
    public void setN(int n) { this.n = n; }
}