package com.controller;

import com.pojo.BackSociety;
import com.pojo.SNews;
import com.pojo.SUser;
import com.pojo.Society;
import com.service.NewsService;
import com.service.SocietyService;
import com.service.UserService;
import com.util.ChangeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ajax / axios  网络服务层，负责与前端数据交互，格式为json，主要服务于管理端的 两个表格操作
 */

@RestController
public class JsonController {

    ChangeDate changeDate = new ChangeDate();
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("societyServiceImpl")
    private SocietyService societyService;
    @Autowired
    private NewsService newsService;

    @RequestMapping("everyOne/login")
    public Map<String,Object> login(@RequestParam("username")String userName,
                                    @RequestParam("password") String password,
                                    HttpSession session){
        SUser sUser =new SUser();
        try {
            sUser.setUserName(userName);
            sUser.setPassword(password);
        } catch (Exception e) {
          Map<String,Object> map = new HashMap<>();
          map.put("error","请正确填写账号名或密码");
          return map;
        }
        if (userService.login(sUser) != null){
            Map<String,Object> map = new HashMap<>();
            map.put("success","success");
            session.setAttribute("token",changeDate.getTime()+changeDate.getDate());
            session.setAttribute("userType",userService.login(sUser).getUserType());
            session.setAttribute("user",userService.login(sUser));
            return map;
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("error","账号名或密码错误");
            return map;
        }

    }

@RequestMapping("admin/getNews")
    public Map<String,Object> getNews(){
    Map<String,Object> map = new HashMap<>();
    List<SNews> sNews = newsService.getNews();
    map.put("news",sNews);
    return map;
    }
    @RequestMapping("admin/getSociety")
    public Map<String,Object> getSociety(){
        Map<String,Object> map = new HashMap<>();
        List<Society> societies = societyService.getAllS();
        map.put("news",societies);
        return map;
    }

    @PostMapping("admin/updateNews")
    public Map<String,Object> updateNews(@RequestBody SNews sNews){
        Map<String,Object> map = new HashMap<>();
        String acc = newsService.updateNews(sNews);
        if (acc.contains("成功")){
            map.put("success","success");
        }else {
            map.put("error","error");
        }
        return map;
    }
    @PostMapping("admin/updateSociety")
    public Map<String,Object> updateSociety(@RequestBody Society society){
        Map<String,Object> map = new HashMap<>();
        String acc = societyService.updateSocieties(society);
        if (acc.contains("成功")){
            map.put("success","success");
        }else {
            map.put("error","error");
        }
        return map;
    }

    @PostMapping("admin/addNews")
    public Map<String,Object> addNews(@RequestBody SNews sNews,
                                      HttpSession session){
        Map<String,Object> map = new HashMap<>();
        sNews.setcTime(changeDate.getDate());
        SUser sUser = (SUser) session.getAttribute("user");
        sNews.setStartId(sUser.getUserId());
        String acc = newsService.addNews(sNews);
        if (acc.contains("成功")){
            map.put("success","success");
        }else {
            map.put("error","error");
        }
        return map;
    }
    @PostMapping("admin/addSociety")
    public Map<String,Object> addSo(@RequestBody Society society,
                                      HttpSession session){
        Map<String,Object> map = new HashMap<>();
       society.setCTime(changeDate.getDate());

        SUser sUser = (SUser) session.getAttribute("user");

        String acc = societyService.addSocieties(society);
        if (acc.contains("成功")){
            map.put("success","success");
        }else {
            map.put("error","error");
        }
        return map;
    }

    @RequestMapping("admin/deleteNews")
    public Map<String,Object> deleteNews(@RequestBody SNews sNews,
                                      HttpSession session){
        Map<String,Object> map = new HashMap<>();

        SUser sUser = (SUser) session.getAttribute("user");
        String acc = newsService.deleteNews(sNews.getNewsId());
        if (acc.contains("成功")){
            map.put("success","success");
        }else {
            map.put("error","error");
        }
        return map;
    }

    @RequestMapping("admin/deleteSociety")
    public Map<String,Object> deleteSociety(@RequestBody Society society,
                                         HttpSession session){
        Map<String,Object> map = new HashMap<>();

        SUser sUser = (SUser) session.getAttribute("user");
        System.out.println(society);
        String acc = societyService.deleteSocieties(society.getSocietyId());
        if (acc.contains("成功")){
            map.put("success","success");
        }else {
            map.put("error","error");
        }
        return map;
    }

}
