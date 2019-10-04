package com.mvc.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                LOGGER.debug("It is preHandle");
                
                HttpSession session = request.getSession(); 

                if( session != null ) {
                    Object loginInfo = session.getAttribute("info");
                    
                    if( loginInfo != null ) {
                        return true;
                    }
                }

                response.sendRedirect(request.getContextPath() + "/login");
                
                return false;
    }
}