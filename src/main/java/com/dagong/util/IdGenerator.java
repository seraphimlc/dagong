package com.dagong.util;

import java.util.UUID;

/**
 * Created by liuchang on 16/1/17.
 */
public class IdGenerator {
    public String generate(String type){
        return type+"_"+UUID.randomUUID().toString();
    }
}
