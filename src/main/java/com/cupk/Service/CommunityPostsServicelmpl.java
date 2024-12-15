package com.cupk.Service;

import com.cupk.mapper.CommunityPostsMapper;
import com.cupk.pojo.CommunityPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CommunityPostsServicelmpl implements CommunityPostsService{


    @Autowired
    private CommunityPostsMapper communityPostsMapper;

    @Override
    public List<CommunityPost> findAllCommunityPosts(){return communityPostsMapper.findAllCommunityPosts();}

    @Override
    public CommunityPost findCommunityPostById(Integer id) {
        return communityPostsMapper.findCommunityPostById(id);
    }

    @Override
    public void updateCommunityPost(CommunityPost communityPost) {
        communityPostsMapper.updateCommunityPost(communityPost);
    }
    @Override
    public void updateUserPost(CommunityPost communityPost) {
        communityPostsMapper.updateUserPost(communityPost);
    }

    @Override
    public void updateUserPostimgnull(CommunityPost communityPost) {
        communityPostsMapper.updateUserPostimgnull(communityPost);
    }
//
    @Override
    public int countStateZ() {
        return communityPostsMapper.countStateZ();
    }
    @Override
    public int countStateG() {
        return communityPostsMapper.countStateG();
    }
//
    @Override
    public void deleteCommunityPost(int id) {
        communityPostsMapper.deleteCommunityPost(id);
    }

    @Override
    public List<CommunityPost> searchCommunityPostsByStr(String searchStr) {
        return communityPostsMapper.searchCommunityPostsByStr(searchStr);
    }

    @Override
    public void deleteCommunityPosts(int[] ids) {
        communityPostsMapper.deleteCommunityPosts(ids);
    }

    @Override
    public void addUserPost(CommunityPost communityPost) {
         communityPostsMapper.addUserPost(communityPost);
    }
    @Override
    public List<CommunityPost> findAllCommunityPostByUId(Integer id) {
        return communityPostsMapper.findAllCommunityPostByUId(id);
    }
}
