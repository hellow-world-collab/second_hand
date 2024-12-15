package com.cupk.Service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AddressService {
    public void addAddressByU(@Param("address")String address, @Param("userid")Integer userid, @Param("username") String username, @Param("userphone")String userphone );

}
