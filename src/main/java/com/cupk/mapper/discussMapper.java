package com.cupk.mapper;

import com.cupk.pojo.discuss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface discussMapper {
    List<discuss> getDiscuss(int product_id);
    List<discuss> getReply(int product_id);
    void addDiscussHost(int user_id,String discuss_text,int product_id);
    void addReply(int user_id,String reply_text,int parent_id,int reply_user_id);
}
