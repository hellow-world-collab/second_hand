package com.cupk.mapper;

import com.cupk.pojo.CommunityPost;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Mapper
public interface CommunityPostsMapper {
    public List<CommunityPost> findAllCommunityPosts();
    public CommunityPost findCommunityPostById(Integer id);
    public void updateCommunityPost(CommunityPost communityPost);
    public void updateUserPost(CommunityPost communityPost);
    public void deleteCommunityPost(int id);
    public List<CommunityPost>searchCommunityPostsByStr(String searchStr);
    public void deleteCommunityPosts(int[] ids);
    public void addUserPost(CommunityPost communityPost);
    public int countStateZ();
    public int countStateG();
    public List<CommunityPost> findAllCommunityPostByUId(Integer id);
    public void updateUserPostimgnull(CommunityPost communityPost);

}
