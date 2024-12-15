package com.cupk.Service;

import com.cupk.mapper.RentalItemMapper;
import com.cupk.pojo.rental_item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//引入Spring框架中定义的@Service注解。
// Spring可以自动检测和配置标记了@Service的类，而不需要你在XML配置文件中显式地声明它们。
@Primary//导入（import）语句，它用于引入Spring框架中定义的@Primary注解。
//bean被标记为@Primary，那么Spring将优先注入这个bean
public class RentalItemServiceImpl implements RentalItemService{
    @Autowired//当Spring容器创建一个bean时，它可以自动地发现并注入这个bean所依赖的其他bean。
    private RentalItemMapper rentalItemMapper;

    @Override
    //查找全部二手物品
    public List<rental_item> findAllRentalItems() {
        return rentalItemMapper.findAllRentalItems();
    }

    @Override
    //删除二手物品
    public void deleteRentalItem(Integer id) {
        rentalItemMapper.deleteRentalItem(id);
    }

    //全局模糊搜索二手物品
    @Override
    public List<rental_item> searchRentalItemsByStr(String searchStr) {
        return rentalItemMapper.searchRentalItemsByStr(searchStr);
    }

    //批量删除二手物品
    @Override
    public void deleteRentalItems(int[] ids) {
        rentalItemMapper.deleteRentalItems(ids);
    }
    //编辑二手物品
    @Override
    public rental_item findRentalItemById(Integer id) {

        return rentalItemMapper.findRentalItemById(id);
    }

    @Override
    public void updateRentalItem(rental_item item) {
        rentalItemMapper.updateRentalItem(item);
    }

    @Override
    public List<rental_item> findRentalItemsByType(String category) {
        return rentalItemMapper.findRentalItemsByType(category);
    }

    @Override
    public rental_item rentalitembyuser(Integer id) {
        return rentalItemMapper.rentalitembyuser(id);
    }

    @Override
    public void updateProductimgnull(rental_item item) {
       rentalItemMapper.updateProductimgnull(item);
    }

    @Override
    public void updateRentalItemmark(Integer id) {
        rentalItemMapper.updateRentalItemmark(id);
    }

}

