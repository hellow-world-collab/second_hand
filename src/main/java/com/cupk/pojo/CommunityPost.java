package com.cupk.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CommunityPost {
    private int id;
    private String name;
    private Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String type;
    private String imgurl;
    private int user_id;
    private String title;
    private String introduce;
    private String state;
}
