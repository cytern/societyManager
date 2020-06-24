package com.controller;

import com.pojo.BackSociety;
import com.pojo.SNews;
import com.pojo.SUser;
import com.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.SocietyService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 页面控制器，继承自servlet，实现功能类似
 * 讲负责传输modelandview中的的user，属性，和一部分jstl属性操作，同时负责页面分发
 */
@Controller
public class PageController {
    @Autowired
    @Qualifier("societyServiceImpl")
    private SocietyService societyService;
    @Autowired
    private NewsService newsService;

    @RequestMapping({"/","index.do"})
    public String SocietyList(Model model){
        return "index";
    }

    @RequestMapping(value = "stu/nList.do")
    public String getnList(
            Model model, HttpSession session
    ){
        List<SNews> sNews = newsService.getNews();
        SUser sUser = (SUser) session.getAttribute("user");
        model.addAttribute("user",sUser);
        model.addAttribute("news",sNews);
        return "nList";
    }

    @RequestMapping(value = "stu/sList.do")
    public String getSList(
            Model model, HttpSession session
            ){
        SUser sUser = (SUser) session.getAttribute("user");
        model.addAttribute("user",sUser);
        List<BackSociety> societies = societyService.getSocieties();
        model.addAttribute("society",societies);
        return "sList";
    }

    @RequestMapping(value = "admin/nManager.do")
    public String getnManager(
            Model model,
            HttpSession session
    ){
        List<BackSociety> societies = societyService.getSocieties();
        SUser sUser = (SUser) session.getAttribute("user");
        model.addAttribute("user",sUser);
        model.addAttribute("society",societies);
        return "nManager";
    }
    @RequestMapping(value = "admin/sManager.do")
    public String getsManager(
            Model model,
            HttpSession session
    ){
        List<BackSociety> societies = societyService.getSocieties();
        SUser sUser = (SUser) session.getAttribute("user");
        model.addAttribute("user",sUser);
        model.addAttribute("society",societies);
        return "sManager";
    }


}
