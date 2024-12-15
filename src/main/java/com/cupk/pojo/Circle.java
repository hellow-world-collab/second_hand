package com.cupk.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Circle {
    private int id;
    private String title;
    private String circleImgurl;
    private int userId;
    private User user;
    private String description;
    private List<Comment> comments;

    public void setCircleImgurl(String circleImgurl) {
        this.circleImgurl = circleImgurl;
    }

    public String getCircleImgurl() {
        return circleImgurl;
    }
}