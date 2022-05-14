package com.GNKBC.GNKBC.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Value("${admin.id}")
    private String adminId;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("Admin Auth Request {}", requestURI);

        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute(adminId) != null){
            if(session.getAttribute(adminId).equals(adminId)){
                log.info("authentication success - redirect");
                return true;
            }
        }
        response.sendRedirect("/admin");
        return false;

//        if(session == null || session.getAttribute(adminId) == null){
//            log.info("Unauthorized User request");
//            response.sendRedirect("/admin");
//            return false;
//        }
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
