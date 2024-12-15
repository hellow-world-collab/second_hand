package com.cupk.Controller;

import com.cupk.pojo.User;
import com.cupk.pojo.rental_item;
import com.cupk.Service.RentalItemService;
import com.cupk.utils.AliOSSUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Slf4j
@Controller//表明为一个控制器
public class RentalItemController {
    @Autowired
    private RentalItemService rentalItemService;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    private User user1=new User();
    //查询所有商品
    @GetMapping("/rentalitemsByAdmin")
    public ModelAndView findAllRentalItems(@RequestParam(value = "start", defaultValue = "1") int start,
                                           @RequestParam(value = "size", defaultValue = "11") int size) {
        PageHelper.startPage(start, size, "id asc");
        List<rental_item> rentalitems = rentalItemService.findAllRentalItems();
        PageInfo<rental_item> page = new PageInfo<>(rentalitems);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page", page);
        mv.setViewName("rentalitemlist");
        return mv;
    }


    //根据种类查询商品
    @RequestMapping("/findRentalItemsByType")
    public ModelAndView findRentalItemsByType(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "start", defaultValue = "1") int start,
            @RequestParam(value = "size", defaultValue = "11") int size, HttpServletRequest request) {
        PageHelper.startPage(start, size, "id asc");
        HttpSession session=request.getSession();
        user1=(User)session.getAttribute("loginUser");
        List<rental_item> rentalitems;
        if (category == null || category.equals("") || category.equals("全部")) {
            System.out.println(category);
            rentalitems = rentalItemService.findAllRentalItems();
        } else {
            rentalitems = rentalItemService.findRentalItemsByType(category);
        }
        PageInfo<rental_item> page = new PageInfo<>(rentalitems);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page", page);
        mv.setViewName("rentalindex");
        return mv;
    }


    @GetMapping("/deleteRentalItem/{id}")//删除物品
    public ModelAndView deleteRentalItem(@PathVariable("id") int id) {//执行删除物品 修改数据库
        rentalItemService.deleteRentalItem(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/rentalitemsByAdmin");//删除物品成功后跳转至列表
        return mv;
    }

    @RequestMapping("/searchRentalItemsByStr")//模糊查询物品
    public ModelAndView searchRentalItemsByStr(@RequestParam(value = "searchStr") String searchStr, Model model
            , @RequestParam(value = "start", defaultValue = "1") int start,
                                               @RequestParam(value = "size", defaultValue = "11") int size) {
        PageHelper.startPage(start, size, "id asc");
        List<rental_item> rentalitems = rentalItemService.searchRentalItemsByStr(searchStr);
        PageInfo<rental_item> page = new PageInfo<>(rentalitems);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page", page);
        mv.setViewName("rentalitemlist");
        return mv;
    }

    @RequestMapping("/searchRentalItemsByStrByUser")//用户模糊查询物品
    public ModelAndView searchRentalItemsByStrByUser(@RequestParam(value = "searchStr") String searchStr, Model model
            , @RequestParam(value = "start", defaultValue = "1") int start,
                                               @RequestParam(value = "size", defaultValue = "11") int size) {
        PageHelper.startPage(start, size, "id asc");
        List<rental_item> rentalitems = rentalItemService.searchRentalItemsByStr(searchStr);
        PageInfo<rental_item> page = new PageInfo<>(rentalitems);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page", page);
        mv.setViewName("rentalindex");
        return mv;
    }

    //批量删除二手物品
    @GetMapping("/deleteRentalItems")
    public ModelAndView deleteRentalItems(int[] id) {
        rentalItemService.deleteRentalItems(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:rentalitemsByAdmin");//删除成功后
        return mv;
    }

    //管理员编辑
    @GetMapping("/rentalitem/{id}")//根据指定编号查找图书
    public ModelAndView findRentalItemById(@PathVariable("id") int id) {
        rental_item item = rentalItemService.findRentalItemById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", item);
        mv.setViewName("rentalitemdetail");
        return mv;
    }

    @PostMapping("/updateRentalItem")
    public ModelAndView updateRentalItem(rental_item item,MultipartFile imges) {//执行修改图书 修改数据库
        rentalItemService.updateRentalItem(item);
        ModelAndView mv = new ModelAndView();
        try {
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                item.setImgurl(url);
                System.out.println(item.getImgurl());
                rentalItemService.updateRentalItem(item);
                System.out.println(item);
            }
            else{
                rentalItemService.updateProductimgnull(item);
            }
        }
        catch (Exception e) {
            mv.setViewName("redirect:/updateRentalItem");
        }
        mv.setViewName("redirect:/rentalitemsByAdmin");//修改图书成功后跳转至列表
        return mv;
    }

    @GetMapping("/toupdaterentalitem/{id}")//跳转到修改商品页面
    public ModelAndView toUpdateRentalItem(@PathVariable("id") int id) {
        rental_item item = rentalItemService.findRentalItemById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", item);
        mv.setViewName("updaterentalitem");
        return mv;
    }

    @GetMapping("/rentalitembyuser/{id}")//用户查找二手商品
    public ModelAndView rentalitembyuser(@PathVariable("id") int id) {
        rental_item item = rentalItemService.rentalitembyuser(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", item);
        mv.setViewName("rentalitembyuser");
        return mv;
    }


}

