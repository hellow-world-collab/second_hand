package com.cupk.Controller;

import com.cupk.pojo.Community;
import com.cupk.Service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    // 显示所有社区动态
    @GetMapping("/community")
    public String showAllCommunities(Model model) {
        List<Community> communities = communityService.findAllCommunities();
        model.addAttribute("communities", communities);
        return "community"; // 返回视图名称
    }

    // 按类型查询社区动态
    @GetMapping("/findProductByType")
    public String findCommunityByType(@RequestParam("type") String type, Model model) {
        List<Community> communities = communityService.findCommunityByType(type);
        model.addAttribute("communities", communities);
        return "community"; // 返回视图名称
    }

    // 显示社区详情
    @GetMapping("/community/{id}")
    public String showCommunityDetail(@PathVariable("id") Integer id, Model model) {
        Community community = communityService.findCommunityById(id);

            model.addAttribute("community", community);
            return "communitydetail"; // 返回详情页视图名称


    }


}
