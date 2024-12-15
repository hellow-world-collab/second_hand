package com.cupk.Controller;

import com.cupk.pojo.User;
import com.cupk.pojo.useraddress;
import com.cupk.Service.UserAddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller//表明为一个控制器
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;
    private User user1=new User();
    //查询所有地址
    @GetMapping("/useraddresses")
    public ModelAndView findAllUserAddresses(@RequestParam(value="start",defaultValue = "1") int start,
                                              @RequestParam(value="size",defaultValue = "10") int size){
        PageHelper.startPage(start,size,"id asc");
        List<useraddress> useraddresses=userAddressService.findAllUserAddresses();
        PageInfo<useraddress> page=new PageInfo<>(useraddresses);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page",page);
        mv.setViewName("useraddresslist");
        return mv;
    }

    //根据ID查询所有地址
    @GetMapping("/useraddressesU")
    public ModelAndView findUserAddressesById(@RequestParam(value="start",defaultValue = "1") int start,
                                              @RequestParam(value="size",defaultValue = "10") int size
    ,HttpServletRequest request){
        HttpSession session=request.getSession();
        user1=(User)session.getAttribute("loginUser");
        System.out.println(user1);
        List<useraddress> useraddressesbyid=userAddressService.findUserAddressesByUserId(user1.getId());
        PageHelper.startPage(start,size,"id asc");
        PageInfo<useraddress> page=new PageInfo<>(useraddressesbyid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page",page);
        mv.setViewName("useraddressesbyid");
        return mv;
    }


    @GetMapping("/deleteUserAddress/{id}")//删除地址
    public ModelAndView deleteUserAddress(@PathVariable("id") int id) {//执行删除地址 修改数据库
        userAddressService.deleteUserAddress(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/useraddresses");//删除地址成功后跳转至列表
        return mv;
    }

    @GetMapping("/deleteUserAddressU/{id}")//删除地址
    public ModelAndView deleteUserAddressU(@PathVariable("id") int id) {//执行删除地址 修改数据库
        userAddressService.deleteUserAddress(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/useraddressesU");//删除地址成功后跳转至列表
        return mv;
    }


    @RequestMapping("/searchUserAddressesByStr")//模糊查询
    public ModelAndView searchUserAddressesByStr(@RequestParam(value = "searchStr") String searchStr, Model model
            ,@RequestParam(value="start",defaultValue = "1") int start,
                                            @RequestParam(value="size",defaultValue = "10") int size)
    {
        PageHelper.startPage(start,size,"id asc");
        List<useraddress> useraddresses = userAddressService.searchUserAddressesByStr(searchStr);
        PageInfo<useraddress> page=new PageInfo<>(useraddresses);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page",page);
        mv.setViewName("useraddresslist");
        return mv;
    }

    //批量删除地址
    @GetMapping("/deleteUserAddresses")
    public ModelAndView deleteUserAddresses(int[] id){
        userAddressService.deleteUserAddresses(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:useraddresses");//删除成功后
        return mv;
    }

    //编辑
    @GetMapping("/useraddress/{id}")//根据指定编号查找地址
    public ModelAndView findUserAddressById(@PathVariable Integer id){
//        HttpSession session=request.getSession();
//        user1=(User)session.getAttribute("loginUser");
//        System.out.println(user1);
        useraddress useradd = userAddressService.findUserAddressById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("useradd", useradd);
        mv.setViewName("useraddressdetail");
        return mv;
    }

    @PostMapping("/updateuseraddress")
    public ModelAndView updateUserAddress(useraddress useradd) {//执行修改图书 修改数据库
        System.out.println(useradd);
        userAddressService.updateUserAddress(useradd);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/useraddresses");//修改地址成功后跳转至列表
        return mv;
    }

    @GetMapping("/toupdateuseraddress/{id}")//跳转到修改地址页面
    public ModelAndView toupdateUserAddress(@PathVariable("id") int id) {
        useraddress useradd = userAddressService.findUserAddressById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("useradd", useradd);
        mv.setViewName("updateuseraddress");
        return mv;
    }

    @PostMapping("/updateuseraddressU")
    public ModelAndView updateUserAddressU(useraddress useradd) {//执行修改图书 修改数据库
        System.out.println(useradd);
        userAddressService.updateUserAddress(useradd);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/useraddressesU");//修改地址成功后跳转至列表
        return mv;
    }

    @GetMapping("/toupdateuseraddressU/{id}")//跳转到修改地址页面
    public ModelAndView toupdateUserAddressU(@PathVariable("id") int id) {
        useraddress useradd = userAddressService.findUserAddressById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("useradd", useradd);
        mv.setViewName("updateuseraddressU");
        return mv;
    }

    @GetMapping("/toaddaddress")//跳转到添加图书页面
    public ModelAndView toAddAddress() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addaddress");
        return mv;
    }

    @PostMapping("/addaddress")
    public ModelAndView addAddress(useraddress address) {//执行添加地址 修改数据库
        System.out.println(address);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        userAddressService.addAddress(address);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/useraddressesU");//添加地址成功后跳转至列表
        return mv;
    }
}

