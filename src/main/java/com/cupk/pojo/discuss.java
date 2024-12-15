package com.cupk.pojo;

import lombok.Data;

@Data
public class discuss {
    private int  index;
    private int dis_id;
    private int reply_id;
    private int user_id;
    private String user_name;
    private String discuss_text;
    private int the_userid_reply;
    private String  the_user_name;
    private int reply_user_id;
    private String  reply_user_name;
    private String reply_text;
}
