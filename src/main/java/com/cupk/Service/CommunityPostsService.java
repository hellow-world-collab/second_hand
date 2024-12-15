package com.cupk.Service;

import com.cupk.pojo.CommunityPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommunityPostsService {
    public List<CommunityPost> findAllCommunityPosts();
    public CommunityPost findCommunityPostById(Integer id);

    public void updateCommunityPost(CommunityPost communityPost);
    public void deleteCommunityPost(int id);

    public List<CommunityPost>searchCommunityPostsByStr(String searchStr);
    public void deleteCommunityPosts(int[] ids);
    public void addUserPost(CommunityPost communityPost);
    public void updateUserPost(CommunityPost communityPost);

    public int countStateZ();
    public int countStateG();
    public List<CommunityPost> findAllCommunityPostByUId(Integer id);
    public void updateUserPostimgnull(CommunityPost communityPost);
}
