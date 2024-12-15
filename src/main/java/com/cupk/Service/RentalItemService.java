package com.cupk.Service;

import com.cupk.pojo.rental_item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//MyBatis框架,标记一个接口为MyBatis的Mapper接口，
       // 这样MyBatis就知道这个接口中的方法应该映射到哪些SQL语句。
public interface RentalItemService {
    public List<rental_item> findAllRentalItems();//查询全部二手物品
    public void deleteRentalItem(Integer id);//删除二手物品
    public List<rental_item>searchRentalItemsByStr(String searchStr);//全局模糊查询二手物品
    public void deleteRentalItems(int[] ids);//批量删除二手物品
    public rental_item findRentalItemById(Integer id);//根据id查询二手物品
    public void updateRentalItem(rental_item item);//修改二手物品
    public List<rental_item>findRentalItemsByType(String category);//根据种类查询商品
    public rental_item rentalitembyuser(Integer id);//用户查看物品详细
    public void updateProductimgnull(rental_item item);
    public void updateRentalItemmark(Integer id);//修改二手物品
}
