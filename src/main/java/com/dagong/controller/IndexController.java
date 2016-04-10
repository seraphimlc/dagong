package com.dagong.controller;

import com.dagong.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuchang on 16/3/16.
 */
@Controller
public class IndexController {
    @Resource
    UserService userService;
    @RequestMapping("/")
    public String index(HttpServletResponse response){
        Cookie cookie = new Cookie("user",userService.generateCookieForUser("1") );
        cookie.setPath("/");
        response.addCookie(cookie);
        return "index";
    }


    @RequestMapping("/send")
    public String index1(HttpServletRequest request){
      Cookie[] cookies=  request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("cookie = " + cookie);
        }
        return "index";
    }

}
