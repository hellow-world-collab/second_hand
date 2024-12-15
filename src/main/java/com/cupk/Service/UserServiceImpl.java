package com.cupk.Service;

import com.cupk.mapper.UserMapper;
import com.cupk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUser(User user) {
        return userMapper.findByUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public User findUserByid(Integer id) {
        return userMapper.findUserByid(id);
    }

    @Override
    public User findByAdmin(User user) {
        return userMapper.findByAdmin(user);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public void deleteUsers(int[] id) {
        userMapper.deleteUsers(id);
    }

    @Override
    public List<User> searchUsersByStr(String searchStr) {
        return userMapper.searchUsersByStr(searchStr);
    }

    @Override
    public void addUsertoAdmin(User user) {
        userMapper.addUsertoAdmin(user);
    }

    @Override
    public List<String> findAdressByUid(Integer id) {
        return userMapper.findAdressByUid(id);
    }

    @Override
    public Integer findidByaddres(String address, Integer ud) {
        return userMapper.findidByaddres(address, ud);
    }
}
