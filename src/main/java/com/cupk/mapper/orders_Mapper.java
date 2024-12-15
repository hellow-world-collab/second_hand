package com.cupk.mapper;

import com.cupk.pojo.CommodityDetail;
import com.cupk.pojo.My_Sale_orders;
import com.cupk.pojo.orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface orders_Mapper {
    List<orders> select_Myorders(int user_id);
    List<orders> select_Myorders_rental(int user_id);
    List<My_Sale_orders> select_saleorders(int user_id);
    List<My_Sale_orders> select_saleorders_rental(int user_id);
    CommodityDetail select_commoditydetail(int commodity_id);
    public void deleteorder(Integer id);//删除订单





    public Integer insert(@Param("number")String number,@Param("userid")Integer userid,@Param("saleid")Integer saleid,@Param("useraddressid")Integer useraddressid,@Param("falge")String falge );
    public Integer selectidBynumber(@Param("number") String number);//查找订单id
    public Integer  insertordersitem(@Param("order_id")Integer order_id,@Param("product_id")Integer product_id);
    public Integer updateordersitem(Integer order_id);//更新ordersitem状态为已支付
}
