package com.cupk.Service;

import com.cupk.mapper.UserAddressMapper;
import com.cupk.pojo.useraddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//引入Spring框架中定义的@Service注解。
// Spring可以自动检测和配置标记了@Service的类，而不需要你在XML配置文件中显式地声明它们。
@Primary//导入（import）语句，它用于引入Spring框架中定义的@Primary注解。
//bean被标记为@Primary，那么Spring将优先注入这个bean
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired//当Spring容器创建一个bean时，它可以自动地发现并注入这个bean所依赖的其他bean。
    private UserAddressMapper userAddressMapper;

    @Override
    //查找全部地址
    public List<useraddress> findAllUserAddresses() {
        return userAddressMapper.findAllUserAddresses();
    }

    @Override
    public void addAddress(useraddress address) {
        userAddressMapper.addAddress(address);
    }

    @Override
    //删除地址
    public void deleteUserAddress(Integer id) {
        userAddressMapper.deleteUserAddress(id);
    }

    //全局模糊搜索地址
    @Override
    public List<useraddress> searchUserAddressesByStr(String searchStr) {
        return userAddressMapper.searchUserAddressesByStr(searchStr);
    }

    //批量删除地址
    @Override
    public void deleteUserAddresses(int[] ids) {
        userAddressMapper.deleteUserAddresses(ids);
    }

    //根据用户ID查询地址
    @Override
    public List<useraddress> findUserAddressesByUserId(Integer id) {
        return userAddressMapper.findUserAddressesByUserId(id);
    }

    //编辑二手物品
    @Override
    public useraddress findUserAddressById(Integer id) {
        return userAddressMapper.findUserAddressById(id);
    }

    @Override
    public void updateUserAddress(useraddress useradd) {
        userAddressMapper.updateUserAddress(useradd);
    }
}
