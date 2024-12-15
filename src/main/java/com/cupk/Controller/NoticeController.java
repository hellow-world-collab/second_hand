package com.cupk.Controller;


import com.cupk.Service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    //管理端进行更新
    @RequestMapping("/notice")
    public ModelAndView notice() {
        ModelAndView mv = new ModelAndView();
        String text=noticeService.SelectNotice();
        mv.addObject("text",text);
        mv.setViewName("notice");
        return mv;
    }
    @PostMapping("/UpdateNotice")
    public ModelAndView updateNotice(String text) {
        ModelAndView mv = new ModelAndView();
        noticeService.UpdateNotice(text);
        mv.setViewName("redirect:/notice");
        return mv;
    }
}
