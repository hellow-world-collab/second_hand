package com.cupk.Controller;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;

    private Integer orderid;
    private Integer productid;
    private String falge;
}
