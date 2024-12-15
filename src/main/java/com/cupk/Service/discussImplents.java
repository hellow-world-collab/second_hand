package com.cupk.Service;

import com.cupk.mapper.discussMapper;
import com.cupk.pojo.discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class discussImplents implements discussService {
    @Autowired
    private discussMapper discussmapper;
    @Override
    public List<discuss> getDiscuss(int product_id) {
        return discussmapper.getDiscuss(product_id);
    }

    @Override
    public List<discuss> getReply(int product_id) {
        return discussmapper.getReply(product_id);
    }

    @Override
    public void addDiscussHost(int user_id,String discuss_text,int product_id) {
    discussmapper.addDiscussHost(user_id,discuss_text,product_id);
    }

    @Override
    public void addReply(int user_id, String reply_text, int parent_id, int reply_user_id) {
        discussmapper.addReply(user_id,reply_text,parent_id,reply_user_id);
    }
}
