package com.mvc.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.controller.common.SecureAccess;
import com.mvc.entity.Member;
import com.mvc.entity.UserInfo;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberLevelInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        if(handler instanceof HandlerMethod == false) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;

        SecureAccess sa = handlerMethod.getMethodAnnotation(SecureAccess.class);

        if( sa == null ) {
            return true;
        }

        HttpSession session = request.getSession();

        if( session == null ) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        UserInfo loginMember = (UserInfo)session.getAttribute("info");

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(sa.value());

        Boolean result = exp.getValue(loginMember, Boolean.class);

        if( result ) {
            return true;
        } else {
            return false;
        }
    }
}