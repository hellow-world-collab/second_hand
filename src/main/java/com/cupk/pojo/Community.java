package com.cupk.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Community {
    private Integer id;
    private String name;
    private String title;
    private String introduce;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date time;
    private String imgurl;
    private String description;
    private String username;
    private String remark;
    private String state;
}
