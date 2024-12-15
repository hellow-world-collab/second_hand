package com.cupk.pojo;
import lombok.Data;

@Data
public class want_buy {
    private Integer id;
    private String title;
    private String content;
    private Integer user_id;
    private String address;
    private String state;
    private String type;
}
