package com.dagong;

import com.dagong.service.UserService;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liuchang on 16/4/19.
 */
@WebFilter(filterName = "authFilter",urlPatterns = "*.do")
public class UserFilter implements Filter {
    @Resource
    private UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        boolean isAuthed=false;
        String userToken = null;
        String userId = null;
        String userName=null;
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if("userToken".equals(cookie.getName())){
                userToken=cookie.getValue();
            }else if("userId".equals(cookie.getName())){
                userId = cookie.getValue();
            }else if("userName".equals(cookie.getName())){
                userName=cookie.getValue();
            }

            if(StringUtils.isNotBlank(userToken)&&
                    StringUtils.isNotBlank(userId)&&
                    StringUtils.isNotBlank(userName)){
                String tempUserId = userService.getUserIdFromCookie(userToken);
                if(userId.equals(tempUserId)){
                    isAuthed=true;
                }
            }
            if(isAuthed){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                httpServletResponse.sendRedirect("/login.do");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
