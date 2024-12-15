package com.cupk.Controller;


import com.cupk.Service.*;
import com.cupk.pojo.*;
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

@Controller
public class UserIndexController {
    @Autowired
    private Index_Commodity_Service index_commodity_service;
    @Autowired
    private orders_Service orders_service;
    @Autowired
    private discussService discussservice;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private WantBuyService wantBuyService;

    private User user=new User();

    @GetMapping("/index")
    public String toindex(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("loginUser");
        System.out.println(user);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        String notice_text=noticeService.SelectNotice();
        System.out.println(notice_text);
        model.addAttribute("notice_text",notice_text);
        List<Index_Commodity> index_commoditys = index_commodity_service.find_All_Commoditys();
        model.addAttribute("index_commoditys", index_commoditys);
        return "index";
    }

    @PostMapping("/select_Index_Commodity_By_Type")
    public ModelAndView select_Index_Commodity_By_Type(String commodity_Type) {
//        String commodity_Type="食物";
        ModelAndView modelAndView = new ModelAndView();
        List<Index_Commodity> index_commoditys;
        if (commodity_Type.equals("全部")) {
             index_commoditys = index_commodity_service.find_All_Commoditys();
        } else {
             index_commoditys = index_commodity_service.selectCommodityByType(commodity_Type);
        }
        modelAndView.addObject("type", commodity_Type);
        modelAndView.addObject("index_commoditys", index_commoditys);
        modelAndView.setViewName("index");
        System.out.println(index_commoditys);
        return modelAndView;
//        System.out.println("-----========================================================================---------=================---------====");
    }
    @GetMapping("/commodityDetail/{id}")
    public ModelAndView commodityDetail(@PathVariable("id") int id) {
        CommodityDetail commodityDetail=orders_service.select_commoditydetail(id);
        System.out.println("------===================================------------=======---------------");
        System.out.println(commodityDetail);

        ModelAndView modelAndView = new ModelAndView();
        List<discuss> discusseslist=discussservice.getDiscuss(id);
        List<discuss> replylist=discussservice.getReply(id);
        System.out.println("6666666666666666666666666666666---------------------------------===================================-------------------------");
        System.out.println(discusseslist);
        System.out.println(discusseslist.size());
        System.out.println("=====================================================================================");
        System.out.println(replylist);
        System.out.println(replylist.size());
        System.out.println(user.getId());
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        modelAndView.addObject("this_user_id",user.getId());
        modelAndView.addObject("discussnum",discusseslist.size());
        modelAndView.addObject("discusslist", discusseslist);
        modelAndView.addObject("replynum", replylist.size());
        modelAndView.addObject("replylist", replylist);
        modelAndView.addObject("commodityDetail", commodityDetail);
        modelAndView.setViewName("commodityDetail");
        return modelAndView;
    }
    @PostMapping("/index2")
    public String index2(@RequestParam(value="searchStr")String searchStr,Model model) {
        System.out.println(searchStr);
        List<Index_Commodity> index_commoditys=index_commodity_service.find_All_Commoditys_ByStr(searchStr);
        System.out.println(index_commoditys);
        model.addAttribute("index_commoditys", index_commoditys);
        return "index";
    }




    @RequestMapping ("/adddiscussHost")
    public void adddiscussHost(int user_id,String discuss_text,int product_id){
            discussservice.addDiscussHost(user_id,discuss_text,product_id);
    }
    @RequestMapping("/addReply")
    public void addReply(int user_id,String reply_text,int parent_id,int reply_user_id){
        discussservice.addReply(user_id,reply_text,parent_id,reply_user_id);
    }



















    @RequestMapping("/myorders")
    public ModelAndView tomyorders(@RequestParam(value = "start", defaultValue = "1") int start,
                                   @RequestParam(value = "size", defaultValue = "9") int size) {
        PageHelper.startPage(start, size, "order_id asc");
        List<orders> myorders = orders_service.select_Myorders(user.getId());
        System.out.println(myorders);
        System.out.println("---------==============----------------================------------=============");
        System.out.println(myorders.size());
        PageInfo<orders> page = new PageInfo<>(myorders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page", page);
        modelAndView.setViewName("myorders");
        return modelAndView;
    }

    @RequestMapping("/myordersrental")
    public ModelAndView tomyordersrental(@RequestParam(value = "start", defaultValue = "1") int start,
                                   @RequestParam(value = "size", defaultValue = "9") int size) {
        PageHelper.startPage(start, size, "order_id asc");
        List<orders> myorders = orders_service.select_Myorders_rental(user.getId());
        System.out.println(myorders);
        System.out.println("---------==============----------------================------------=============");
        System.out.println(myorders.size());
        PageInfo<orders> page = new PageInfo<>(myorders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page", page);
        modelAndView.setViewName("myordersrental");
        return modelAndView;
    }

    @RequestMapping("/saleorders")
    public ModelAndView tosaleorders(@RequestParam(value = "start", defaultValue = "1") int start,
                                     @RequestParam(value = "size", defaultValue = "9") int size) {
        PageHelper.startPage(start, size, "order_id asc");
        List<My_Sale_orders> saleorders = orders_service.select_saleorders(user.getId());
        PageInfo<My_Sale_orders> page = new PageInfo<>(saleorders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page", page);
        modelAndView.setViewName("saleorders");
        return modelAndView;
    }

    @RequestMapping("/saleordersrental")
    public ModelAndView tosaleordersrental(@RequestParam(value = "start", defaultValue = "1") int start,
                                     @RequestParam(value = "size", defaultValue = "9") int size) {
        PageHelper.startPage(start, size, "order_id asc");
        List<My_Sale_orders> saleorders = orders_service.select_saleorders_rental(user.getId());
        PageInfo<My_Sale_orders> page = new PageInfo<>(saleorders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page", page);
        modelAndView.setViewName("saleordersrental");
        return modelAndView;
    }
    @RequestMapping("/myTobuy")
    public ModelAndView tomyTobuy(@RequestParam(value = "start", defaultValue = "1") int start,
                                     @RequestParam(value = "size", defaultValue = "9") int size,HttpServletRequest request) {
        PageHelper.startPage(start, size, "id asc");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        List<want_buy>index_commoditys=wantBuyService.selectwanbuybyU(user.getId());
        PageInfo<want_buy> page = new PageInfo<>(index_commoditys);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page", page);
        modelAndView.setViewName("myTobuy");
        return modelAndView;
    }

    @GetMapping("/deleteorder/{id}")//删除订单
    public ModelAndView deleteorder(@PathVariable("id") int id) {//执行删除订单 修改数据库
        orders_service.deleteorder(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/myorders");//删除订单成功后跳转至列表
        return mv;
    }


//    @GetMapping("/commodityDetail")
//    public ModelAndView tocommodityDetail() {
//
//        List<discuss> discusseslist=discussservice.getDiscuss();
//        System.out.println(discusseslist);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("discusslist", discusseslist);
//        modelAndView.setViewName("commodityDetail");
//        return modelAndView;
//    }

}
