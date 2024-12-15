package com.cupk.mapper;

import com.cupk.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    public User findByUser(User user);
    public List<User> findAllUsers();
    public User findByAdmin(User user);
    public Integer addUser(User user);//返回受影响行数
    public Integer updateUser(User user);
    public User findUserByid(Integer id);
    public Integer deleteUser(Integer id);
    public void deleteUsers(int[] id);//批量删除用户
    public List<User> searchUsersByStr(String searchStr);
    public void addUsertoAdmin(User user);//把一个用户添加为管理员

    public Integer findidByaddres(@Param("address")String address,@Param("userid") Integer ud);
    public List<String>findAdressByUid(Integer id);//通过用户id查找用户的地址
}
