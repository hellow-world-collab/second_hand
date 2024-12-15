// CircleController.java
package com.cupk.Controller;

import com.cupk.pojo.Circle;
import com.cupk.Service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CircleController {
    @Autowired
    private CircleService circleService;

    @GetMapping("/circle")
    public String getCirclePage(Model model) {
        List<Circle> circles = circleService.findAllCircles();
        model.addAttribute("circles", circles);
        return "circle";
    }

    @GetMapping("/start")
    public String start() {
        return "start"; // 返回视图名称 "start"
    }

    @GetMapping("/test/circles")
    public String testCircles(Model model) {
        List<Circle> circles = circleService.findAllCircles();
        model.addAttribute("circles", circles);
        return "test";
    }
}