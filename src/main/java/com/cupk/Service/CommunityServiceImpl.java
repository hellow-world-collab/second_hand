package com.cupk.Service;

import com.cupk.mapper.CommunityMapper;
import com.cupk.pojo.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public List<Community> findAllCommunities() {
        return communityMapper.findAllCommunities();
    }

    @Override
    public Community findCommunityById(Integer id) {
        return communityMapper.findCommunityById(id);
    }

    @Override
    public List<Community> findCommunityByType(String type) {
        return communityMapper.findCommunityByType(type);
    }


}
