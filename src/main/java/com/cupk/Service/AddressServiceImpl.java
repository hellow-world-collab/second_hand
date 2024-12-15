package com.cupk.Service;

import com.cupk.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressmapper;
    @Override
    public void addAddressByU(String address, Integer userid, String username, String userphone) {
        addressmapper.addAddressByU(address,userid,username,userphone);
    }
}
