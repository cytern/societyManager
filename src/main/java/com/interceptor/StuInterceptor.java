package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * 拦截器，区别是否为管理员
 */
public class StuInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
                 try {
                     HttpSession session = request.getSession();
                     String sessionId = session.getId();
                     String token1 = (String) session.getAttribute("token");
                     if (token1.length()>5){
                         return true;
                 }else {
                         request.setAttribute("msg","登录过期");
                         request.getRequestDispatcher("/").forward(request,response);
                         return false;
                     }


            } catch (Exception e){
                     request.setAttribute("msg","请先登录");
                     request.getRequestDispatcher("/").forward(request,response);
                     return false;
                 }
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
