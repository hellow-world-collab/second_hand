package com.cupk.Service;

import com.cupk.mapper.orders_Mapper;
import com.cupk.pojo.CommodityDetail;
import com.cupk.pojo.My_Sale_orders;
import com.cupk.pojo.orders;
//import com.cupk.service.orders_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class orders_Service_Implents implements orders_Service {
    @Autowired
    private orders_Mapper myorders;
    @Override
    public List<orders> select_Myorders(int user_id) {
        return myorders.select_Myorders(user_id);
    }

    @Override
    public List<My_Sale_orders> select_saleorders(int user_id) {
        return myorders.select_saleorders(user_id);
    }

    @Override
    public CommodityDetail select_commoditydetail(int commodity_id) {
        return myorders.select_commoditydetail(commodity_id);
    }

    @Override
    public Integer insert(String number, Integer userid, Integer saleid, Integer useraddressid,String falge) {
        return myorders.insert(number, userid, saleid, useraddressid,falge);
    }

    @Override
    public Integer selectidBynumber(String number) {
        return myorders.selectidBynumber(number);
    }
    @Override
    public Integer insertordersitem(Integer order_id, Integer product_id) {
        return myorders.insertordersitem(order_id, product_id);
    }

    @Override
    public Integer updateordersitem(Integer order_id) {
        return myorders.updateordersitem(order_id);
    }

    @Override
    public void deleteorder(Integer id) {
        myorders.deleteorder(id);
    }

    @Override
    public List<orders> select_Myorders_rental(int user_id) {
        return myorders.select_Myorders_rental(user_id);
    }

    @Override
    public List<My_Sale_orders> select_saleorders_rental(int user_id) {
        return myorders.select_saleorders_rental(user_id);
    }
}
