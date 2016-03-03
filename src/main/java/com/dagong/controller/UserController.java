package com.dagong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuchang on 16/3/3.
 */
@RestController
@RequestMapping("/apply")
public class UserController {

    public String initFromWeChat(){
        return null;
    }
    public String sendValidateCode(String userId,String phoneNumber){
        return null;
    }
    public String register(String name,String gender,String cardId,String phoneNumber,String validateCode){
        return null;
    }
}
