package com.cupk.Service;
import com.cupk.pojo.useraddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//MyBatis框架,标记一个接口为MyBatis的Mapper接口，
       // 这样MyBatis就知道这个接口中的方法应该映射到哪些SQL语句。
public interface UserAddressService {
    public List<useraddress> findAllUserAddresses();//查询全部地址
    public void addAddress(useraddress address);//添加地址
    public void deleteUserAddress(Integer id);//删除地址
    public List<useraddress>searchUserAddressesByStr(String searchStr);//全局模糊查询地址
    public void deleteUserAddresses(int[] ids);//批量删除地址
    public List<useraddress> findUserAddressesByUserId(Integer id);//根据用户ID查询全部地址
    public useraddress findUserAddressById(Integer id);//根据id查询地址
    public void updateUserAddress(useraddress useradd);//修改地址
}
