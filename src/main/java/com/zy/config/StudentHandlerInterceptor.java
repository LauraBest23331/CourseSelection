package com.zy.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object login_student = request.getSession().getAttribute("login_student");
        if (login_student == null) {
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/Login").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
