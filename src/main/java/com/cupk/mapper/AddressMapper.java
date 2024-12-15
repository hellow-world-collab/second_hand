package com.cupk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddressMapper {
    public void addAddressByU(@Param("address")String address, @Param("userid")Integer userid,@Param("username") String username,@Param("userphone")String userphone );
}
