package com.cupk.Controller;

import com.cupk.Service.WantBuyService;
import com.cupk.pojo.User;
import com.cupk.pojo.want_buy;
import com.cupk.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WantBuyController {
    @Autowired
    private WantBuyService wantBuyService;

    @GetMapping("/toaddwantbuy")//跳转到添加求购页面
    public ModelAndView toaddwantbuy() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addwantbuy");
        return mv;
    }

    @RequestMapping("/addwantbuy")
    public ModelAndView addwantbuy(want_buy wb, HttpServletRequest request) {//执行添加求购 修改数据库
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        wb.setUser_id(user.getId());
        wantBuyService.addwantbuy(wb);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/findRentalItemsByType");//添加求购成功后跳转至列表
        return mv;
    }
    @RequestMapping("/wantobuy")
    public ModelAndView AllwantBuy(){
        List<want_buy> wantBuys=wantBuyService.getAllwantbuy();
        ModelAndView mv = new ModelAndView();
        mv.addObject("wantBuys",wantBuys);
        mv.setViewName("wantobuy");
        return mv;
    }
}
