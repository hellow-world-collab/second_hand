package com.cupk.Service;

import com.cupk.pojo.discuss;

import java.util.List;

public interface discussService {
    List<discuss> getDiscuss(int prodyuct_id);
    List<discuss> getReply(int prodyuct_id);
    void addDiscussHost(int user_id,String discuss_text,int product_id);
    void addReply(int user_id,String reply_text,int parent_id,int reply_user_id);
}
