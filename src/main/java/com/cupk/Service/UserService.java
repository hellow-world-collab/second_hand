package com.cupk.Service;

import com.cupk.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserService {
    public User findByUser(User user);
    public List<User> findAllUsers();
    public User findUserByid(Integer id);
    public User findByAdmin(User user);
    public Integer addUser(User user);//返回受影响行数
    public Integer updateUser(User user);
    public Integer deleteUser(Integer id);
    public void deleteUsers(int[] id);//批量删除用户
    public List<User> searchUsersByStr(String searchStr);
    public void addUsertoAdmin(User user);//把一个用户添加为管理员
    public List<String>findAdressByUid(Integer id);//通过用户id查找用户的地址

    public Integer findidByaddres(String address,Integer ud);

}
